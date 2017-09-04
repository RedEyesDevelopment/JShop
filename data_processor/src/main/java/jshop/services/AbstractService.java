package jshop.services;

import jshop.storage.Cache;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public abstract class AbstractService<T, ID extends Serializable> {
    private JpaRepository<T, ID> cache;
    private List<TelemetryService> telemetries;

    public List findAllSortBy(Sort sort) throws Exception {
        try {
            return cache.findAll(sort);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public List saveAll(Collection<T> entities) throws Exception {
        try {
            for (T t : entities) {
                preSaveEntityPreparation(t);
            }
            return cache.save(entities);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public T getOne(ID id) throws Exception {
        try {
            return (T) cache.getOne(id);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public Page findAllPageable(Pageable pageable) throws Exception {
        try {
            return cache.findAll(pageable);
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

    public void delete(T entity) throws Exception {
        try {
            preDeleteEntityPreparation(entity);
            cache.delete(entity);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public void deleteCollection(Collection<T> entities) throws Exception {
        try {
            for (T t : entities) {
                preDeleteEntityPreparation(t);
            }
            cache.delete(entities);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    protected void preSaveEntityPreparation(T entity) {
    }

    protected void preDeleteEntityPreparation(T entity) {
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
