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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.work1.cracks.auth.JwtAuthenticationFilter;
import com.work1.cracks.auth.JwtTokenProvider;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Tag(name = "Controlador de Autenticación", description = "Encargado de todo lo referente Registro y Login")
public class AuthController {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoSession repoSession;

    @Autowired
    private RepoCities repoCities;

    public String desEncriptar(MultipartFile clave) {
        try {
            Path path = Paths.get("password.enc");
            byte[] claveB = clave.getBytes();
            Files.write(path, claveB);
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "pkeyutl", "-decrypt", "-inkey", "private.pem", "-in", "password.enc", "-passin",
                    "pass:diego");
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.lines().collect(Collectors.joining("\n"));
                return ("Contraseña descifrada: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;// ResponseEntity.status(500).body("Error al procesar el archivo");
        }
    }

    @Operation(summary = "Registro de Usuario", description = "Punto de entrada para Registrar un Usuario Nuevo")
    @PostMapping(value = "/registro", consumes = "multipart/form-data")
    public ResponseEntity<String> registro(@RequestParam("name") String name,
            @RequestParam("psw") MultipartFile clave) {


        if (repoUser.existsByName(name)) {
            return new ResponseEntity<String>("El usuario ya existe", HttpStatus.CONFLICT);
        }

        Cities c = new Cities();
        c.setCode("7500");
        c.setName("3arr");
        repoCities.save(c);

        User u = new User();
        u.setName(name);
        u.setCity(c);
        repoUser.save(u);

        String psw = desEncriptar(clave);
        System.out.println("desencriptado" + psw);

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        psw = crypt.encode(psw);
        Session session = new Session(u, psw);
        session.setTypeLogin(TypeLogin.MANUAL);
        repoSession.save(session);

        return new ResponseEntity<String>("Registro exitoso para El usuario: " + name, HttpStatus.OK);
    }

    @Autowired
    private JwtTokenProvider generarToken;

    @Autowired
    private AuthenticationManager authManager;


    @Operation(summary = "Logueo de Usuario", description = "Punto de entrada para el Usuario, aqui recibirá un Token para tener acceso a la API")
    @PostMapping(value = "/login", consumes = "multipart/form-data")
    public ResponseEntity<String> login(@RequestParam("name") String name, @RequestParam("psw") MultipartFile clave) {
        String psw = desEncriptar(clave);
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, psw));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = generarToken.generateToken(auth);
        return new ResponseEntity<String>(token, HttpStatus.OK);

    }

    @Autowired
    JwtAuthenticationFilter jwtFiltro;

    @Operation(summary = "Punto de prueba de acceso ", description = "Ingresar con Token")
    @GetMapping("/hola")
    public String hola(HttpServletRequest request) {

        String token = jwtFiltro.getTokenFromRequest(request);

        String a = generarToken.getUsername(token);

        return "Hola " + a;
    }

}
