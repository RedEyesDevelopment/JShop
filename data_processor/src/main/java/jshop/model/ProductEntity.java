package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "code")
    private String code;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_i18n_id", unique = true, nullable = false)
    private I18nEntity name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategoryEntity category;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_string_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<StringProductParameterValueEntity> stringParameters;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_int_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<IntProductParameterValueEntity> intParameters;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_date_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<DateProductParameterValueEntity> dateParameters;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_float_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private Set<FloatProductParameterValueEntity> floatParameters;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @Column(name = "activated")
    private boolean activatedForSale;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "storage")
    private long storageCount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "products_to_photos", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private Set<PhotoFileEntity> photos;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pagemeta_id", unique = true, nullable = false)
    private PageMetaEntity metadata;
}
