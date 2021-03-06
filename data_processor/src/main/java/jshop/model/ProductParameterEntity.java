package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product_parameters")
public class ProductParameterEntity implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_i18n_id", unique = true, nullable = false)
    private I18nEntity name;
    @Column(name = "ordinal")
    private int ordinal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;
}
