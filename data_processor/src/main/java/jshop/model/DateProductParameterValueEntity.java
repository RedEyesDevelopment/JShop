package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "date_product_parameter_value")
public class DateProductParameterValueEntity extends AbstractProductParameterValue implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date data;
}
