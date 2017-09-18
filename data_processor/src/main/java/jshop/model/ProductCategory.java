package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="name_i18n_id", unique=true, nullable=false)
    private I18n name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_string_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private StringProductParameterValue[] stringParameters;
    @JoinTable(name = "products_to_int_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private IntProductParameterValue[] intParameters;
    @JoinTable(name = "products_to_date_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private DateProductParameterValue[] dateParameters;
    @JoinTable(name = "products_to_float_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private FloatProductParameterValue[] floatParameters;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    protected ProductParameter parent;
    @OneToMany(mappedBy = "parent",fetch = FetchType.EAGER)
    protected Set<ProductParameter> children;
}
