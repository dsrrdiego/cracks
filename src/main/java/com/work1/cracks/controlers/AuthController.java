package com.work1.cracks.controlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.work1.cracks.auth.JwtAuthenticationFilter;
import com.work1.cracks.auth.JwtTokenProvider;
import com.work1.cracks.dtos.DtoRegistro;
import com.work1.cracks.interfaces.TypeLogin;
import com.work1.cracks.modelos.User;
import com.work1.cracks.modelos.Cities;
import com.work1.cracks.modelos.Session;
import com.work1.cracks.modelos.Sports;
import com.work1.cracks.repos.RepoCities;
import com.work1.cracks.repos.RepoSession;
import com.work1.cracks.repos.RepoSports;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.servicios.ConsultaGeneral;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Tag(name = "Controlador general de la API", description = "Encargado de todo lo referente a subir y bajar Albums de audio")
public class AuthController {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoSession repoSession;

    @Autowired
    private RepoCities repoCities;

    @PostMapping("/login2")
    public ResponseEntity<String> login2(@RequestBody byte[] clave){
        try {
            Path path = Paths.get("password.enc");
            Files.write(path, clave);
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "pkeyutl", "-decrypt", "-inkey", "private.pem", "-in", "password.enc", "-passin",
                    "pass:diego");
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.lines().collect(Collectors.joining("\n"));
                return ResponseEntity.ok("Contraseña descifrada: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo");
        }
    }

    

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody DtoRegistro reg) {

        String nombre = reg.getName();
        if (repoUser.existsByName(nombre)) {
            return new ResponseEntity<String>("El usuario ya existe", HttpStatus.CONFLICT);
        }

        Cities c = new Cities();
        c.setCode("7500");
        c.setName("3arr");
        repoCities.save(c);

        User u = new User();
        u.setName(nombre);
        u.setCity(c);
        repoUser.save(u);

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String psw = crypt.encode(reg.getPasswrd());
        Session session = new Session(u, psw);
        session.setTypeLogin(TypeLogin.MANUAL);
        repoSession.save(session);

        return new ResponseEntity<String>("Registro exitoso para El usuario: " + nombre, HttpStatus.OK);
    }

    @Autowired
    private JwtTokenProvider generarToken;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DtoRegistro credenciales) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(credenciales.getName(), credenciales.getPasswrd()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        // JwtTokenProvider generar=new JwtTokenProvider();
        String token = generarToken.generateToken(auth);
        return new ResponseEntity<String>(token, HttpStatus.OK);

    }

    @Autowired
    JwtAuthenticationFilter jwtFiltro;

    @GetMapping("/hola")
    public String hola(HttpServletRequest request) {

        String token = jwtFiltro.getTokenFromRequest(request);

        String a = generarToken.getUsername(token);

        return "Entraste" + a;
    }

}
