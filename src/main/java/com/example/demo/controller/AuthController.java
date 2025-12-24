// package com.example.demo.controller;

// import com.example.demo.entity.UserAccount;
// import com.example.demo.service.AuthService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final AuthService service;

//     public AuthController(AuthService service) {
//         this.service = service;
//     }

//     @PostMapping("/register")
//     public UserAccount register(@RequestBody UserAccount user) {
//         return service.register(user);
//     }

//     @PostMapping("/login")
//     public String login(@RequestBody UserAccount user) {
//         return service.login(user.getUsername(), user.getPassword());
//     }
// }
