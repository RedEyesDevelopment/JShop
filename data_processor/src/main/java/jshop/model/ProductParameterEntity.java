package jshop.model;

import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public abstract class ProductParameterEntity {
    @ManyToOne
    protected ProductParameter parameter;
}
