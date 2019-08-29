package springhibernatemysql.domain;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data


@Entity
@Table(name = "users", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_UK", columnNames = "name")})
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "password")
    @NotNull
    private String password;
    /*
        @Column(name = "role")
        private String role = "user";
    */
    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Transient
    private Collection<? extends Role> grantedAuthorities;

    /*
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof User)) return false;
          User user = (User) o;
          return getName().equals(user.getName()) &&
                  password.equals(user.password);
      }

      @Override
      public int hashCode() {
          return Objects.hash(getName(), password);
      }

      public User() {
      }
  */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
/*
    public User(String name, String password, String email, String country) {
        super();
        this.name = name;
        this.password = password;
  //      this.role = role;
        this.email = email;
        this.country = country;
    }

 */
/*
    public User(int id, String name, String role, String email, String country) {
        super();
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.country = country;
    }

    public User(int id, String name, String password, String role, String email, String country) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 */
}
