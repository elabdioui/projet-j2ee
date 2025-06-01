package com.produit.produitbackend;

import entities.Role;
import entities.User;
import entities.Produit;
import entities.Categorie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import repos.RoleRepository;
import repos.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"entities"})
@ComponentScan(basePackages = {"RestController", "service", "DTO", "security", "util", "com.produit.produitbackend"})
@EnableJpaRepositories(basePackages = {"repos"})
public class ProduitBackendApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ProduitBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Produit.class, Categorie.class);

        // Créer les rôles par défaut
        createDefaultRoles();

        // Créer les utilisateurs par défaut
        createDefaultUsers();
    }

    private void createDefaultRoles() {
        if (roleRepository.findByRoleName("ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByRoleName("USER") == null) {
            Role userRole = new Role();
            userRole.setRoleName("USER");
            roleRepository.save(userRole);
        }
    }

    private void createDefaultUsers() {
        // Créer utilisateur admin
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setEnabled(true);

            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(roleRepository.findByRoleName("ADMIN"));
            adminRoles.add(roleRepository.findByRoleName("USER"));
            admin.setRoles(adminRoles);

            userRepository.save(admin);
        }

        // Créer utilisateur user1
        if (!userRepository.findByUsername("user1").isPresent()) {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("123"));
            user1.setEnabled(true);

            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleRepository.findByRoleName("USER"));
            user1.setRoles(userRoles);

            userRepository.save(user1);
        }
    }
}