package RestController;

import entities.Role;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import repos.RoleRepository;
import repos.UserRepository;
import util.JWTUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Username already exists!");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);

            // Assigner le rôle USER par défaut
            Role userRole = roleRepository.findByRoleName("USER");
            if (userRole == null) {
                userRole = new Role();
                userRole.setRoleName("USER");
                userRole = roleRepository.save(userRole);
            }

            List<Role> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);

            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        try {
            String refreshToken = request.get("refreshToken");
            if (refreshToken != null && !jwtUtil.isTokenExpired(refreshToken)) {
                String username = jwtUtil.extractUsername(refreshToken);
                User user = userRepository.findByUsername(username).orElse(null);

                if (user != null) {
                    List<String> roles = new ArrayList<>();
                    user.getRoles().forEach(role -> roles.add(role.getRoleName()));

                    String newAccessToken = jwtUtil.generateAccessToken(username, roles);

                    return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
                }
            }
            return ResponseEntity.badRequest().body("Invalid refresh token");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token refresh failed: " + e.getMessage());
        }
    }
}