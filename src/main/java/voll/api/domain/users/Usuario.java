package voll.api.domain.users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table (name = "usuarios")
@Entity(name = "Usuario")
//lombok generates getters when compiles
@Getter
//creates a constructor without arguments(default)
@NoArgsConstructor
//creates a constructor with all the arguments
@AllArgsConstructor
//to use ID to compare doctors
@EqualsAndHashCode (of = "id")
public class Usuario implements UserDetails{

    //this selects the id as the main key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String clave;



    //autogeneraten UserDetails methods
    //everything is to let the user login
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return clave;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return login;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }



    //Auto generaten UserDetails
}
