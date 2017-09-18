package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "float_product_parameter")
public class FloatProductParameterValue extends ProductParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="data")
    private float data;
}
