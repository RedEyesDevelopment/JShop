package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "code")
    private String code;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_string_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private StringProductParameterValue[] stringParameters;
    @JoinTable(name = "products_to_int_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private IntProductParameterValue[] intParameters;
    @JoinTable(name = "products_to_date_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private DateProductParameterValue[] dateParameters;
    @JoinTable(name = "products_to_float_parameters", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
    private FloatProductParameterValue[] floatParameters;
    private UserEntity author;
    @Column(name = "activated")
    private boolean activatedForSale;
    @Column(name = "storage")
    private long storageCount;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_photos", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private PhotoFile[] photos;
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "pagemeta_id", unique = true, nullable = false)
    private PageMeta metadata;
}
