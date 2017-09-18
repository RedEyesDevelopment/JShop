package jshop.model;

import javax.persistence.*;

@Entity
@Table(name = "i18n")
public class I18n {
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
