package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product_parameters")
public class ProductParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_i18n_id", unique = true, nullable = false)
    private I18n name;
    @Column(name = "ordinal")
    protected int ordinal;
}
