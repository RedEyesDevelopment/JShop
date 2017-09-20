package jshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "i18n")
public class I18nEntity implements IdentifiablePersistentObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="ru")
    private String ru;
    @Column(name="en")
    private String en;
    @Column(name="ua")
    private String ua;
}
