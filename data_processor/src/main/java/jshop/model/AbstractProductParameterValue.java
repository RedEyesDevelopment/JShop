package jshop.model;

import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public abstract class AbstractProductParameterValue {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_parameter_id")
    protected ProductParameterEntity parameter;
}
