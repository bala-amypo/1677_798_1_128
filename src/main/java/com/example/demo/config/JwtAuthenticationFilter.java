// package com.example.demo.config;

// import jakarta.servlet.*;
// import jakarta.servlet.http.*;
// import java.io.IOException;

// public class JwtAuthenticationFilter extends GenericFilter {

//     private final JwtTokenProvider provider;

//     public JwtAuthenticationFilter(JwtTokenProvider provider) {
//         this.provider = provider;
//     }

//     @Override
//     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest request = (HttpServletRequest) req;
//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);
//             provider.getUsername(token); // validation only
//         }

//         chain.doFilter(req, res);
//     }
// }
