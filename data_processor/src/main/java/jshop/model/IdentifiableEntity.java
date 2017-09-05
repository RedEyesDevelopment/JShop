package jshop.model;

import java.io.Serializable;

public interface IdentifiableEntity<ID extends Serializable> {
    ID getId();
}
