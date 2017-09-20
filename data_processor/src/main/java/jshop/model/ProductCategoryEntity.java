package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="category")
public class ProductCategoryEntity implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="name_i18n_id", unique=true, nullable=false)
    private I18nEntity name;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_string_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<StringProductParameterValueEntity> stringParameters;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_int_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<IntProductParameterValueEntity> intParameters;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_date_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<DateProductParameterValueEntity> dateParameters;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_float_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<FloatProductParameterValueEntity> floatParameters;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "parent_category_id")
//    protected ProductCategoryEntity parent;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    protected Set<ProductParameterEntity> productParameters;
}
