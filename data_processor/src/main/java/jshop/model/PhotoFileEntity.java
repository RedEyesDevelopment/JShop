package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="page_meta")
public class PhotoFileEntity implements IdentifiablePersistentObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name="directory")
    private String directory;
    @Column(name="filename")
    private String filename;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "alternative_i18n_id", unique = true, nullable = false)
    private I18nEntity alternative;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;
    @Column(name="uploaded")
    private Date uploadedDate;
}
