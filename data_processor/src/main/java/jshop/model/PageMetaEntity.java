package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="page_meta")
public class PageMetaEntity implements IdentifiablePersistentObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "name_i18n_id", unique = true, nullable = false)
    private I18nEntity title;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "keywords_i18n_id", unique = true, nullable = true)
    private I18nEntity keywords;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_i18n_id", unique = true, nullable = true)
    private I18nEntity description;
}
