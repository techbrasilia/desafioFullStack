package com.desafiofullstack;

import com.desafiofullstack.model.Perfil;
import com.desafiofullstack.model.Usuario;
import com.desafiofullstack.repository.PerfilRepository;
import com.desafiofullstack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Perfil adminProfile = new Perfil();
        adminProfile.setNome("ADMIN");
        Optional<Perfil> admPf = perfilRepository.findByNome(adminProfile.getNome());
        if (admPf.isEmpty()) {
            perfilRepository.save(adminProfile);
        }
        Perfil userProfile = new Perfil();
        userProfile.setNome("USER");

        Optional<Perfil> userPf = perfilRepository.findByNome(userProfile.getNome());
        if (userPf.isEmpty()) {
            perfilRepository.save(userProfile);
        }

        Usuario admin = new Usuario();
        admin.setNome("admin");
        admin.setEmail("admin@admin.com");
        admin.setSenha(passwordEncoder.encode("admin123"));

        if (admPf.isPresent()) {
            adminProfile = admPf.get();
        }
        admin.setPerfil(adminProfile);

        Optional<Usuario> uAdm = usuarioRepository.findByEmail(admin.getEmail());
        if (uAdm.isEmpty()) {
            usuarioRepository.save(admin);
        }

        Usuario user = new Usuario();
        user.setNome("user");
        user.setEmail("user@user.com");
        user.setSenha(passwordEncoder.encode("user123"));

        if (userPf.isPresent()) {
            userProfile = userPf.get();
        }
        user.setPerfil(userProfile);

        Optional<Usuario> us = usuarioRepository.findByEmail(user.getEmail());
        if (us.isEmpty()) {
            usuarioRepository.save(user);
        }

    }
}
