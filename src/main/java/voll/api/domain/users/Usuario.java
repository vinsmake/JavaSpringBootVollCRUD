package voll.api.domain.users;

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
public class Usuario {

    //this selects the id as the main key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String clave;
}
