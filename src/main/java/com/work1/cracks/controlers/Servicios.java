package com.work1.cracks.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.servicios.ConsultaGeneral;

@RestController
public class Servicios {
    
    @Autowired
    private ConsultaGeneral consultaGeneral;
    @PostMapping("/consulta")
    public List<Object> consulta(@RequestBody String a) {
        return consultaGeneral.consultar(a);
    }
}
