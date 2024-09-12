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

        Perfil userProfile = new Perfil();
        userProfile.setNome("USER");

        perfilRepository.saveAll(List.of(adminProfile, userProfile));

        Usuario admin = new Usuario();
        admin.setNome("admin");
        admin.setEmail("admin@admin.com");
        admin.setSenha(passwordEncoder.encode("admin123"));
        admin.setPerfil(adminProfile);

        Usuario user = new Usuario();
        user.setNome("user");
        user.setEmail("user@user.com");
        user.setSenha(passwordEncoder.encode("user123"));
        user.setPerfil(userProfile);

        usuarioRepository.saveAll(List.of(admin, user));
    }
}
