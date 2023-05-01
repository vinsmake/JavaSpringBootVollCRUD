package voll.api.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //When we use findByLogin, Spring knows that we need the login column, so, if we are using something like "username" we must use findByUsername instead
    UserDetails findByLogin(String username);
}
