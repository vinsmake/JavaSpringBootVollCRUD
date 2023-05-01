package voll.api.domain.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


//with this we say to spring that it needs to be used before starting any other modules
@Configuration
//here we say to spring that this is a web app and we need to use his security
@EnableWebSecurity
public class SecurityConfiguration {
    //NOTE:  a bean is simply an object that is instantiated, assembled and managed by a Spring IoC container. It is an instance of a class that is defined in the Spring IoC container and can be initialized, injected with dependencies and destroyed by the container. The @Bean annotation is used to define a method that returns a bean instance.
    //In this case, we use Bean to tell spring that this is in his context
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //csrf evitates an identity suplantation. We have it disabled because we're useing token authentication
        return httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
