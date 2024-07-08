package com.work1.cracks.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.work1.cracks.modelos.aux.ClimateSports;
import com.work1.cracks.repos.aux.RepoClimateSports;

@Component
public class runController implements CommandLineRunner {

    @Autowired
    private RepoClimateSports repoClimate;

    @Override
    public void run(String... args) throws Exception {
        ClimateSports clima = new ClimateSports();
        clima.setClima("otoño");
        try {
            repoClimate.save(clima);
        } catch (Exception e) {
            System.out.println("\n\nLos datos iniciales ya estan cargados\n\n");

        }

    }

}
