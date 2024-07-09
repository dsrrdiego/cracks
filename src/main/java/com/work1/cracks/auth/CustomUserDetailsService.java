package com.work1.cracks.auth;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.work1.cracks.modelos.Session;
import com.work1.cracks.modelos.User;
import com.work1.cracks.repos.RepoSession;
import com.work1.cracks.repos.RepoUser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private RepoSession repoSession;
    private RepoUser repoUser;

    @Override
    public UserDetails loadUserByUsername(String nombre){//} throws UsernameNotFoundException {

        User user = repoUser.findByName(nombre);
        Session session=repoSession.getPasswrdById(user.getId());
        String psw=session.getPasswrd();
                // .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        // Set<GrantedAuthority> authorities = user.getRoles().stream()
        //         .map((role) -> new SimpleGrantedAuthority(role.getName()))
        //         .collect(Collectors.toSet());

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Añadir manualmente las autoridades deseadas
        if (nombre.equals("diego")){

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }else{
                
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(
                nombre,
                psw,
                authorities
        );
    }
}