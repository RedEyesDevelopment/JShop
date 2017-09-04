package jshop.storage;

import jshop.services.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class Cache<T, ID extends Serializable> {
    private JpaRepository<T, ID> jpaRepository;

    public Cache(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<T> findAll(Sort sort) throws Exception{
        return jpaRepository.findAll(sort);
    }

    public List<T> save(Collection<T> entities) throws Exception{
        return jpaRepository.save(entities);
    }

    public T getOne(ID id) throws Exception{
        return  jpaRepository.getOne(id);
    }

    public Page findAll(Pageable pageable) throws Exception{
        return jpaRepository.findAll(pageable);
    }

    public T save(T entity) throws Exception{
        return jpaRepository.save(entity);
    }

    public boolean exists(ID id) throws Exception{
        return jpaRepository.exists(id);
    }

    public long count() throws Exception{
        return jpaRepository.count();
    }

    public void delete(ID id) throws Exception{
        jpaRepository.delete(id);
    }

    public void delete(T entity) throws Exception{
        jpaRepository.delete(entity);
    }
    public void deleteCollection(Collection<T> entities) throws Exception{
        jpaRepository.delete(entities);
    }
}
