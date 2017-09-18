package jshop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private I18n name;
    private Set<ProductParameter> desirables;
    private ProductCategory parentCategory;
}
