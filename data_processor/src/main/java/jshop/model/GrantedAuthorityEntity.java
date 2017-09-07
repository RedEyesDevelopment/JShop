package jshop.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = "users")
@Entity
@Table(name = "granted_authority")
public class GrantedAuthorityEntity implements IdentifiableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="authority")
    private String authority;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_to_authorities", joinColumns = @JoinColumn(name = "auth_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users;
}
