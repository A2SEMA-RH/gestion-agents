package org.gestionAgent.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity

//pour genrer  les autorisations
//avant de faire ca     @PreAuthorize("hasAuthority('SCOPE_USER')") dans les controller
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
//    recuperation de la d'une variable declarer dans application.propertises
//    ici on secupere le secret


    @Value("${jwt.secret}")
    private String secretKey ;


//    c'est une fonction pour creer les utilisateur dans la memoire de l'appication et non dans la base de donne c'est juste pour tester
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        PasswordEncoder passwordEncoder =passwordEncoder();
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("12345")).authorities("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("12345")).authorities("USER","ADMIN").build()

        );


    }
//    pour pour ancoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                pour dire que on ne gere la session cote serveur
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                pour desactiver la protection csrf par defaut de spring security
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**").permitAll()
                )
//                pour dire que toute les requetes necessitent une authantification
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
//                le type de l'authantification | pas trop faire que chaque requette necessitent une authentifiaction
                //.httpBasic(Customizer.withDefaults())

//                authentification avec le jwt ce qui nous amenene a creer deux methode pour la signature token

                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
                .build();

    }

    @Bean
    public JwtEncoder jwtEncoder(){
        //String secretKey = "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338";
        return  new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        //String secretKey = "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338";
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();

    }
//    cette fonction permet de de faire l'authentification
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        on specifie les parametre :

//        ici l'encodeuur de nous allons autiliser |ici a deja creer notre encodeur donc on lui passe en  parametre
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }


//    cette methode des pour regler le probleme de cors entre le backend et de fontend
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration  corsConfiguration = new CorsConfiguration();
//        les origine qui sont autoriser |ici tous les origines
        corsConfiguration.addAllowedOrigin("*");
//        authoriser toutes les methodes
        corsConfiguration.addAllowedMethod("*");
//        j'accepte les headers envoyer
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

    }


}
