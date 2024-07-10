package com.work1.cracks.controlers;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.work1.cracks.modelos.aux.ClimateSports;
import com.work1.cracks.modelos.aux.RoleParticipants;
import com.work1.cracks.modelos.aux.StatusEvents;
import com.work1.cracks.modelos.aux.StatusParticipants;
import com.work1.cracks.repos.aux.RepoClimateSports;
import com.work1.cracks.repos.aux.RepoRoleParticipants;
import com.work1.cracks.repos.aux.RepoStatusEvents;
import com.work1.cracks.repos.aux.RepoStatusParticipants;

@Component
public class runController implements CommandLineRunner {

    @Autowired
    private RepoClimateSports repoClimate;

    @Autowired
    private RepoRoleParticipants repoRoleParticipants;

    @Autowired
    private RepoStatusParticipants repoStatusParticipants;

    @Override
    public void run(String... args) throws Exception {
        try {
            // DatosInterface tabla = new DatosInterface();
            // tabla.nombre = "RoleParticipants";
            // tabla.repo = repoRoleParticipants;
            // tabla.datos = new String[] { "a", "b", "c" };


            //Cargar

            // ArrayList<DatosInterface> todo = new ArrayList<>();
            // todo.add(tabla);
            // for (DatosInterface t : todo) {

            //     Class<?> clase = Class.forName(t.nombre);
            //     Constructor<?> constructor=clase.getConstructor(String.class);
            //     // Class<?> repo=Class.forName(t.repo);
            //     for (String s : t.datos) {
            //         Object obj = constructor.newInstance(s);
            //         t.repo.save(obj);



                    
                    
            //     }

            //     System.out.println("");
            // }

            // Cargando Status Events
            String[] lista = { "Inscripto", "Aceptado", "Rechazado", "Participado", "Faltado" };
            for (String i : lista) {
                StatusParticipants x = new StatusParticipants();
                x.setStatus(i);
                repoStatusParticipants.save(x);
            }

            // Cargando RoleParticipants
            String[] lista2 = { "Creador", "Participante", "Espectador"};
            for (String i : lista2) {
                RoleParticipants x = new RoleParticipants(i);
                repoRoleParticipants.save(x);
            }


            ClimateSports clima = new ClimateSports();
            clima.setClima("otoño");
            repoClimate.save(clima);
        } catch (Exception e) {
            System.out.println("\n\nLos datos iniciales ya estan cargados\n\n");

        }

    }

}
