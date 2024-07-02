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
import com.work1.cracks.modelos.User;
import com.work1.cracks.modelos.Session;
import com.work1.cracks.repos.RepoSession;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.servicios.ConsultaGeneral;

@RestController
public class AuthController {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoSession repoSession;

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody DtoRegistro reg){
        
        String nombre=reg.getName();
        if (repoUser.existsByName(nombre)){
            return new ResponseEntity<String>("El usuario ya existe",HttpStatus.CONFLICT);
        }

        User u=new User();
        u.setName(nombre);
        repoUser.save(u);
        
        Session session=new Session(u,reg.getPasswrd());
        repoSession.save(session);

        return new ResponseEntity<String>("Registro exitoso para El usuario: "+nombre,HttpStatus.OK);
    }
  

    
}
