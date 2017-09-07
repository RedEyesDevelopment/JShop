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

public abstract class AbstractService<T extends IdentifiableEntity, ID extends Serializable> {
    protected AbstractCacheStorage<T, ID> cache;
    private List<TelemetryService> telemetries = new ArrayList<TelemetryService>();

    public List<TelemetryService> getTelemetriesList() {
        return telemetries;
    }

    public void addNewTelemetryService(TelemetryService telemetry) {
        if (null!=telemetry){
            this.telemetries.add(telemetry);
        }
    }

    public List<T> saveAll(Collection<T> entities) {
            List<T> result = new ArrayList<>();
            for (T t : entities) {
                preSaveEntityPreparation(t);
                result.add(cache.save(t));
            }
            return result;
    }

    public T getOne(ID id) {
            return cache.getOne(id).orElse(null);
    }

    public Page findPageable(Pageable pageable) {
        try {
            return cache.findAll(pageable);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public T save(T entity) {
        try {
            preSaveEntityPreparation(entity);
            return (T) cache.save(entity);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public boolean exists(ID id) {
        try {
            return cache.exists(id);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public long count() {
        try {
            return cache.count();
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public void delete(ID id) {
        try {
            preDeleteEntityByIdPreparation(id);
            cache.delete(id);
        } catch (Exception e) {
            sendExceptionTelemetry(e);
            throw e;
        }
    }

    public void deleteCollection(Collection<ID> ids) {
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
