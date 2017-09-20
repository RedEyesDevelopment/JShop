package jshop.model;

import java.io.Serializable;

public interface IdentifiablePersistentObject<ID extends Serializable> {
    ID getId();
}
