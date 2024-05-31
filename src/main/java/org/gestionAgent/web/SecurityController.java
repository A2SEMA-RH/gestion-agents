package org.gestionAgent.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class SecurityController {
//    ce que spring utilise pour faire l'authentification

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;

    }
    @PostMapping("/login")
    public Map<String , String>login(String username, String password ){
//        pour authentifier un utilisateur
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//        pour generer le jwt

//        pour genter le jwt on a besion de la date system pour rentre le token unique
        Instant instant = Instant.now();
//        recuperation des roles de l'utilisateur authentifier
//        pour recuperer chaque role de l'utilisateur sous forme d'une collection separer par  un espace
        String scope =  authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                date de generation du token
                .issuedAt(instant)
//                la date d'expiration
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
//                le username il faut aussi avoir le nom de l'utilisateur dans le token
                .subject(username)
//                ajouter des roles| scope c'est juste ne nom de la variable pour stoker les role de l'utlisateur authentifier
                .claim("scope",scope)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );
        String jwt= jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("access-token", jwt);


    }
}
