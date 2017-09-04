package jshop.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class AbstractStorage<T, ID extends Serializable> {
    private JpaRepository repository;
    private List<TelemetryService> telemetries;

    public AbstractStorage(JpaRepository repository, List<TelemetryService> telemetries) {
        this.repository = repository;
        this.telemetries = telemetries;
    }

    public List findAllSortBy(Sort sort) {
        try {
            return repository.findAll(sort);
        } catch (Exception e){
            sendExceptionTelemetry(e);
        }
    }

    public List saveAll(Collection<T> entities) {
        for (T t: entities){
            preSaveEntityPreparation(t);
        }
        return repository.save(entities);
    }

    public void flush() {
        repository.flush();
    }

    public T getOne(ID id) {
        return (T) repository.getOne(id);
    }

    public Page findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T save(T entity) {
        preSaveEntityPreparation(entity);
        return (T) repository.save(entity);
    }

    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public long count() {
        return repository.count();
    }

    public void delete(ID id) {
        preDeleteEntityByIdPreparation(id);
        repository.delete(id);
    }

    public void delete(T entity) {
        preDeleteEntityPreparation(entity);
        repository.delete(entity);
    }

    public void deleteCollection(Collection<T> entities) {
        for (T t:entities){
            preDeleteEntityPreparation(t);
        }
        repository.delete(entities);
    }

    public void preSaveEntityPreparation(T entity){
    }

    public void preDeleteEntityPreparation(T entity){
    }

    public void preDeleteEntityByIdPreparation(ID id){
    }

    private void sendExceptionTelemetry(Exception e){
        if (!telemetries.isEmpty()){
            for (TelemetryService telemetry: telemetries){
                telemetry.doTelemetry(e);
            }
        }
    }
}
