package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "string_product_parameter")
public class StringProductParameterValue extends ProductParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_i18n_id", unique = true, nullable = false)
    private I18n data;
}
