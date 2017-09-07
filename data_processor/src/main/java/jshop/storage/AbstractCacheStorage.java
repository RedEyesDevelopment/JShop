package jshop.storage;

import jshop.model.IdentifiableEntity;
import jshop.repositories.IdentifiableRepository;
import jshop.services.AbstractService;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public abstract class AbstractCacheStorage<T extends IdentifiableEntity, ID extends Serializable> {
    protected Cache cache;
    protected JpaRepository<T, ID> jpaRepository;

//    @Transactional
    public Optional<T> getOne(ID id) {
        if (isAlive()) {
            Element element = cache.get(id);
            if (element != null && !element.isExpired()) {
                return Optional.of((T) element.getObjectValue());
            }
            cache.acquireReadLockOnKey(id);
            cache.acquireWriteLockOnKey(id);
            T fromDb = jpaRepository.getOne(id);
            cache.put(new Element(id, fromDb));
            cache.releaseWriteLockOnKey(id);
            cache.releaseReadLockOnKey(id);
            return Optional.ofNullable(fromDb);
        } else {
//            log.error("ERROR! Cache " + cache.getName() + " has status " + cache.getStatus() + ". Fetched entity from database.");
            return Optional.ofNullable(jpaRepository.getOne(id));
        }
    }

//    @Transactional
    public Page<T> findAll(Pageable pageable) {
        IdentifiableRepository identifiableRepository = (IdentifiableRepository) jpaRepository;
        List<ID> ids = identifiableRepository.getSortedIds(pageable);
        List<T> result = new ArrayList<>();
        for (ID currVal : ids) {
            Optional currObj = getOne(currVal);
            if (currObj.isPresent()){
                result.add((T) currObj.get());
            }
        }
        Page<T> page;
        if (result.isEmpty()){
            page = new PageImpl<T>(Collections.EMPTY_LIST);
        } else page = new PageImpl<T>(result);
        return page;
    }

//    @Transactional
    public T save(T entity) {
        T newT = jpaRepository.save(entity);
        if (cache.isKeyInCache(newT.getId())) {
            cache.acquireReadLockOnKey(newT.getId());
            cache.acquireWriteLockOnKey(newT.getId());
            cache.put(new Element(newT.getId(), newT));
            cache.releaseWriteLockOnKey(newT.getId());
            cache.releaseReadLockOnKey(newT.getId());
        } else {
            cache.put(new Element(newT.getId(), newT));
        }
        return newT;
    }

    public boolean exists(ID id) {
        return jpaRepository.exists(id);
    }

    public long count() {
        return jpaRepository.count();
    }

//    @Transactional
    public void delete(ID id) {
        jpaRepository.delete(id);
        cache.remove(id);
    }

    private boolean isAlive() {
        return cache.getStatus().equals(Status.STATUS_ALIVE);
    }
}
