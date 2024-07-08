package com.work1.cracks.controlers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@Tag(name = "Controlador general de la API", description = "Encargado de todo lo referente a subir y bajar Albums de audio")
public class AuthController {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoSession repoSession;

    @Autowired
    private RepoCities repoCities;

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody DtoRegistro reg){
        
        String nombre=reg.getName();
        if (repoUser.existsByName(nombre)){
            return new ResponseEntity<String>("El usuario ya existe",HttpStatus.CONFLICT);
        }

        Cities c=new Cities();
        c.setCode("7500");
        c.setName("3arr");
        repoCities.save(c);

        User u=new User();
        u.setName(nombre);
        u.setCity(c);
        repoUser.save(u);
        
        Session session=new Session(u,reg.getPasswrd());
        session.setTypeLogin(TypeLogin.MANUAL);
        repoSession.save(session);

        return new ResponseEntity<String>("Registro exitoso para El usuario: "+nombre,HttpStatus.OK);
    }

    @Autowired
    private RepoSports r;
    @GetMapping("/a")
    public Sports  a(){
        Sports s=new Sports();
        s.setTitle("rugby");
        s.setStatics("statcis");
        r.save(s);
        return s;

    }
    @GetMapping("/s")
    public List<Sports>  s(){
        
        return r.findAll();

    }
  

    
}
