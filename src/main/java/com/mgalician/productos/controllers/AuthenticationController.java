package com.mgalician.productos.controllers;

import com.mgalician.productos.models.dtos.CredencialesDto;
import com.mgalician.productos.models.dtos.JWTDto;
import com.mgalician.productos.models.dtos.RespuestaGenericaDto;
import com.mgalician.productos.services.UserDetailsServiceImpl;
import com.mgalician.productos.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<RespuestaGenericaDto> authenticate(@RequestBody CredencialesDto credencialesDto)
            throws Exception {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credencialesDto.getUsername(),
                credencialesDto.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(credencialesDto.getUsername());
        JWTDto jwtDto = new JWTDto();
        jwtDto.setJwt("Bearer " + jwtUtil.generateToken(userDetails));
        respuestaGenericaDto.setDatos(jwtDto);
        return ResponseEntity.ok(respuestaGenericaDto);
    }
}