package com.work1.cracks.controlers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.work1.cracks.modelos.aux.ClimateSports;
import com.work1.cracks.modelos.aux.DifficultySports;
import com.work1.cracks.modelos.aux.RoleParticipants;
import com.work1.cracks.modelos.aux.StatusEvents;
import com.work1.cracks.modelos.aux.StatusParticipants;
import com.work1.cracks.repos.aux.RepoClimateSports;
import com.work1.cracks.repos.aux.RepoDifficultySports;
import com.work1.cracks.repos.aux.RepoRoleParticipants;
import com.work1.cracks.repos.aux.RepoStatusEvents;
import com.work1.cracks.repos.aux.RepoStatusParticipants;

import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Component
public class runController implements CommandLineRunner {

    @Autowired
    private RepoClimateSports repoClimate;

    @Autowired
    private RepoRoleParticipants repoRoleParticipants;

    @Autowired
    private RepoStatusParticipants repoStatusParticipants;

    @Autowired
    RepoDifficultySports repoDifficultySports;

    // @Autowired
    // private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        
            String[] dificultades={"Facil","Dificil"};
            carga("DifficultySports",repoDifficultySports,dificultades);

            String[] climas={"Autun","Veeran"};
            carga("ClimateSports",repoClimate,climas);


            
    }

    // @Autowired 
    // private RepoGenerico repoGenerico;

    
    
    public <T,R extends JpaRepository<T,?>> void carga(String tabla, R repo ,String[] lista) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        try {
            T t=(T) Class.forName("com.work1.cracks.modelos.aux."+tabla);
            Constructor<?> constructor = ((Class) t).getConstructor(String.class);
            for (String i : lista) {
                T obj = (T) constructor.newInstance(i);
                repo.save(obj);
            }
        } catch (Exception e) {
            System.out.println("\n\n"+e+"Los datos iniciales ya estan cargados para "+tabla+"\n\n");
        }
    }
}

   // ArrayList<DatosInterface> todo = new ArrayList<>();

            // DatosInterface tablaRoleParticipants = new DatosInterface();
            // tablaRoleParticipants.nombre = "RoleParticipants";
            // tablaRoleParticipants.repo = "repoRoleParticipants";
            // tablaRoleParticipants.datos = new String[] { "a", "b", "c" };
            // todo.add(tablaRoleParticipants);

            // DificultySports
            // DatosInterface tablaDifi = new DatosInterface();
            // tablaDifi.nombre = rutaModelos+"DifficultySports";
            // tablaDifi.repo = repoDifficultySports;
            // tablaDifi.datos = new String[] { "a", "b", "c" };
            // todo.add(tablaDifi);


            // Cargar

            // for (DatosInterface tabla : todo) {

            //     Class<?> clase = Class.forName(tabla.nombre);
            //     Constructor<?> constructor = clase.getConstructor(String.class);

            //     // Object bean = applicationContext.getBean(tabla.repo);
            //     // Class<?> repo = bean.getClass();
            //     for (String s : tabla.datos) {
            //         Object obj = constructor.newInstance(s);
            //         // Object objCast = clase.cast(obj);
            //         // Method method = repo.getMethod("save",obj.getClass());
            //         // method.invoke(bean,obj);
            //         ((tabla.repo)tabla.repo).save(obj);
            //     }
            // }

            // // Cargando Status Events
            // String[] lista = { "Inscripto", "Aceptado", "Rechazado", "Participado", "Faltado" };
            // for (String i : lista) {
            //     StatusParticipants x = new StatusParticipants();
            //     x.setStatus(i);
            //     repoStatusParticipants.save(x);
            // }

            // // Cargando RoleParticipants
            // String[] lista2 = { "Creador", "Participante", "Espectador" };
            // for (String i : lista2) {
            //     RoleParticipants x = new RoleParticipants(i);
            //     repoRoleParticipants.save(x);
            // }

            // ClimateSports clima = new ClimateSports();
            // clima.setClima("otoño");
            // repoClimate.save(clima);