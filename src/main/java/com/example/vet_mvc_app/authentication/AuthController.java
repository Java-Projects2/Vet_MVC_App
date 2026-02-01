package com.example.vet_mvc_app.authentication;

import com.example.vet_mvc_app.authentication.JWT.JwtService;
import com.example.vet_mvc_app.authentication.dto.AuthResponse;
import com.example.vet_mvc_app.authentication.dto.LoginRequest;
import com.example.vet_mvc_app.users.Repository.UserRepository;
import com.example.vet_mvc_app.users.dto.CreateUserRequest;
import com.example.vet_mvc_app.users.dto.UserResponse;
import com.example.vet_mvc_app.users.entity.User;
import com.example.vet_mvc_app.users.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    /*  @PostMapping("/register")
      public ResponseEntity<String> createUser(

              @Valid
              @RequestBody
              CreateUserRequest request) {
          String response = userService.createUser(request);
          return new ResponseEntity<>(response, HttpStatus.CREATED);
      }*/
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        System.out.println("=== showRegistrationForm called ===");
        model.addAttribute("userRequest", new CreateUserRequest());
        model.addAttribute("pageTitle", "Register New User");
        return "users/register";
    }


    @PostMapping("/register")
    public String handleRegistration(
            @Valid
            @ModelAttribute("userRequest")
            CreateUserRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Register new user");
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "users/register";
        }
        String response = userService.createUser(request);
        // Add success message to flash attributes
        redirectAttributes.addFlashAttribute("successMessage", response);
        // Redirect to success page or login
        return "redirect:/users/register/success";
    }

    // Method 3: Show success page
    @GetMapping("/register/success")
    public String showSuccessPage(Model model) {
        model.addAttribute("pageTitle", "Registration Successful");
        return "users/register-success";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String token = jwtService.generateToken(user.getId(), user.getName(), request.getEmail(), user.getRole());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "message", "Login successful"
        ));
    }
}
