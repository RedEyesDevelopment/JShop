package jshop.model;

import lombok.Data;

import javax.persistence.OneToMany;

@Data
public abstract class ProductParameterEntity {
    @OneToMany
    protected ProductParameter parameter;
}
