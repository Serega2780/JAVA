package springhibernatemysql.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/*
@Data

@Entity
@Table(name = "roles")

 */
@Data
@Entity
@Table(name = "roles", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "ROLE_UK", columnNames = "role")})
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public Role(int id, String role) {
        setId(id);
        setRole(role);
    }

    @Override
    public String getAuthority() {
        return this.getRole();
    }
}
