package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "int_product_parameter")
public class IntProductParameterValueEntity extends AbstractProductParameterValue implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="data")
    private int data;
}
