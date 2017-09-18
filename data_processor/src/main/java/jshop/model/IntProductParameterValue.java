package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "int_product_parameter")
public class IntProductParameterValue extends ProductParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="data")
    private int data;
}
