package com.work1.cracks.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.modelos.User;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.servicios.ConsultaGeneral;

@RestController
public class AuthController {
    @Autowired
    private RepoUser repoUser;

    @GetMapping("/hola")
    public String hola(){
        return "hola";
    }

    @GetMapping("/registro")
    public String registro(@RequestBody User user){
        String nombre=user.getName();
        repoUser.save(user);
        return nombre;
    }
  

    
}
