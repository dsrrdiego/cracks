package com.work1.cracks.controlers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.work1.cracks.modelos.Events;
import com.work1.cracks.modelos.Goals;
import com.work1.cracks.modelos.Sports;
import com.work1.cracks.modelos.User;
import com.work1.cracks.modelos.aux.ClimateSports;
import com.work1.cracks.modelos.aux.DifficultySports;
import com.work1.cracks.modelos.aux.RoleParticipants;
import com.work1.cracks.modelos.aux.StatusEvents;
import com.work1.cracks.modelos.aux.StatusParticipants;
import com.work1.cracks.modelos.aux.TypeNotification;
import com.work1.cracks.repos.RepoEvents;
import com.work1.cracks.repos.RepoSports;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.repos.aux.RepoCategoryEvents;
import com.work1.cracks.repos.aux.RepoCategoryGoals;
import com.work1.cracks.repos.aux.RepoClimateSports;
import com.work1.cracks.repos.aux.RepoCommunityGoals;
import com.work1.cracks.repos.aux.RepoDifficultySports;
import com.work1.cracks.repos.aux.RepoGoals;
import com.work1.cracks.repos.aux.RepoNameUserScore;
import com.work1.cracks.repos.aux.RepoRoleParticipants;
import com.work1.cracks.repos.aux.RepoStatusEvents;
import com.work1.cracks.repos.aux.RepoStatusNotification;
import com.work1.cracks.repos.aux.RepoStatusParticipants;
import com.work1.cracks.repos.aux.RepoTypeNotification;

import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Component
public class runController implements CommandLineRunner {

    @Autowired
    RepoDifficultySports repoDifficultySports;

    @Autowired
    private RepoClimateSports repoClimate;

    @Autowired
    private RepoStatusParticipants repoStatusParticipants;

    @Autowired
    private RepoRoleParticipants repoRoleParticipants;

    @Autowired
    private RepoStatusEvents repoStatusEvents;

    @Autowired
    private RepoCategoryEvents repoCategoryEvents;

    @Autowired
    private RepoStatusNotification repoStatusNotification;

    @Autowired
    private RepoTypeNotification repoTypeNotification;

    @Autowired
    private RepoNameUserScore repoNameUserScore;

    @Autowired
    private RepoCategoryGoals repoCategoryGoals;

    @Autowired
    private RepoCommunityGoals repoCommunityGoals;

    @Override
    public void run(String... args) throws Exception {

        String[] dificultades = { "Facil", "Dificil" };
        cargar("DifficultySports", repoDifficultySports, dificultades);

        String[] climas = { "Verano", "Otoño", "Invierno", "Primavera" };
        cargar("ClimateSports", repoClimate, climas);

        String[] status = { "Inscripto", "Aceptado", "Rechazado", "Partipado", "Faltado" };
        cargar("StatusParticipants", repoStatusParticipants, status);

        String[] role = { "Creador", "Participante", "Espectador" };
        cargar("RoleParticipants", repoRoleParticipants, role);

        String[] statusE = { "Registrado", "En Curso", "Finalizado" };
        cargar("StatusEvents", repoStatusEvents, statusE);

        String[] categorys = { "Cultural", "Deportivo", "Social" };
        cargar("CategoryEvents", repoCategoryEvents, categorys);

        String[] notif = { "Recibida", "Leida", "No Leida", "Archivada" };
        cargar("StatusNotification", repoStatusNotification, notif);

        String[] typeNot = { "Recordatorio", "Aceptación", "Calificación", "General", "Tips", "Faqs" };
        cargar("TypeNotification", repoTypeNotification, typeNot);

        String[] nameUserS = { "Puntualidad", "Participacion", "Compañerismo" };
        cargar("NameUserScore", repoNameUserScore, nameUserS);

        String[] categoryG = { "Categoria1", "Categoria2" };
        cargar("CategoryGoals", repoCategoryGoals, categoryG);

        String[] communityGoals = { "Personal", "Social", "Grupo1" };
        cargar("CommunityGoals", repoCommunityGoals, communityGoals);

        cargaABorrar();

    }

    public <T, R extends JpaRepository<T, ?>> void cargar(String tabla, R repo, String[] lista)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            T t = (T) Class.forName("com.work1.cracks.modelos.aux." + tabla);
            Constructor<?> constructor = ((Class) t).getConstructor(String.class);
            for (String i : lista) {
                T obj = (T) constructor.newInstance(i);
                repo.save(obj);
            }
        } catch (Exception e) {
            System.out.println("\nLos datos iniciales ya estan cargados para " + tabla);
        }
    }

    @Autowired
    private RepoEvents re;

    @Autowired
    private RepoUser ru;

    @Autowired
    private RepoGoals rg;

    @Autowired
    private RepoSports rs;

    private void cargaABorrar() {
        try{
        Events e = new Events();
        e.setTitle("Juntarnos a correr");
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 15, 0);
        e.setDateInit(fecha);
        re.save(e);

        Events e2 = new Events();
        e2.setTitle("Nadar");
        e2.setDateInit(fecha);
        re.save(e2);

        // User u = new User();
        // u.setName("Pepe");
        // ru.save(u);

        Goals g=new Goals();
        g.setTitle("Adelgazar");
        rg.save(g);

        Goals g2=new Goals();
        g2.setTitle("Engordar");
        rg.save(g2);

        Sports s=new Sports();
        s.setTitle("futbol");
        rs.save(s);

        Sports s2=new Sports();
        s2.setTitle("Rugby");
        rs.save(s2);
        }catch (Exception e){}


    }
}
