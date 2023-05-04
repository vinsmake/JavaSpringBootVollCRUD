package voll.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import voll.api.domain.infra.security.DatosJWToken;
import voll.api.domain.infra.security.TokenService;
import voll.api.domain.users.DatosAutenticacionUsuario;
import voll.api.domain.users.Usuario;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    
    @Autowired
    private AuthenticationManager authentionManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) throws Exception{
        Authentication authToken = new UsernamePasswordAuthenticationToken(
            datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave()
            );
        var usuarioAutenticado = authentionManager.authenticate(authToken);
        //a principal is an user that have been already authentified, we are casting the answer as a user
        var JWToken = tokenService.generarToken((Usuario)usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(JWToken));

    }
}
