package com.mgalician.productos.controllers;

import static com.mgalician.productos.utils.ConstantesUtil.LOGIN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.productos.components.JwtComponent;
import com.mgalician.productos.models.dtos.CredencialesDto;
import com.mgalician.productos.models.dtos.JWTDto;
import com.mgalician.productos.models.dtos.RespuestaGenericaDto;
import com.mgalician.productos.services.UserDetailsServiceImpl;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtComponent jwtComponent;

    @PostMapping(LOGIN)
    public ResponseEntity<RespuestaGenericaDto> authenticate(@RequestBody CredencialesDto credencialesDto)
            throws Exception {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credencialesDto.getUsername(),
                credencialesDto.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(credencialesDto.getUsername());
        JWTDto jwtDto = new JWTDto();
        jwtDto.setJwt("Bearer " + jwtComponent.generateToken(userDetails));
        respuestaGenericaDto.setDatos(jwtDto);
        return ResponseEntity.ok(respuestaGenericaDto);
    }
}