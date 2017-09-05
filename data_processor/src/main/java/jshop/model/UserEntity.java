package jshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity(name="users")
public class UserEntity implements IdentifiableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users-to-authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private List<GrantedAuthorityEntity> authorities;
    @Column(name="regdate")
    private Date regdate;
    @Column(name="enabled")
    private boolean enabled;
    @Column(name="comment")
    private String comment;
}
