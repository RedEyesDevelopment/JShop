package jshop.services;

import jshop.model.IdentifiableEntity;
import jshop.storage.AbstractCacheStorage;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public abstract class AbstractService<T extends IdentifiableEntity, ID extends Serializable> {
    protected AbstractCacheStorage<T, ID> cache;
    private List<TelemetryService> telemetries;

    public List<T> saveAll(Collection<T> entities) throws Exception {
        try {
            List<T> result = new ArrayList<>();
            for (T t : entities) {
                preSaveEntityPreparation(t);
                result.add(cache.save(t));
            }
            return result;
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public T getOne(ID id) throws Exception {
        try {
            return cache.getOne(id).orElse(null);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public Page findPageable(String entityName, Pageable pageable) throws Exception {
        try {
            return cache.findAll(entityName, pageable);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public T save(T entity) throws Exception {
        try {
            preSaveEntityPreparation(entity);
            return (T) cache.save(entity);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public boolean exists(ID id) throws Exception {
        try {
            return cache.exists(id);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public long count() throws Exception {
        try {
            return cache.count();
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public void delete(ID id) throws Exception {
        try {
            preDeleteEntityByIdPreparation(id);
            cache.delete(id);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public void deleteCollection(Collection<ID> ids) throws Exception {
        try {
            for (ID id : ids) {
                preDeleteEntityByIdPreparation(id);
                cache.delete(id);
            }
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    protected void preSaveEntityPreparation(T entity) {
    }


    protected void preDeleteEntityByIdPreparation(ID id) {
    }

    private void sendExceptionTelemetry(Exception e) {
        if (!telemetries.isEmpty()) {
            for (TelemetryService telemetry : telemetries) {
                telemetry.doTelemetry(e);
            }
        }
    }
}
