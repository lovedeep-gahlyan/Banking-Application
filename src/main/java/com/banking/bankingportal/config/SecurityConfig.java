//package com.banking.bankingportal.config;
//
//import java.util.Collections;
//
<<<<<<< HEAD
//
//
=======
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
<<<<<<< HEAD
//
//
=======
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8
//import jakarta.servlet.http.HttpServletRequest;
//
//@Configuration
//public class SecurityConfig {
//
<<<<<<< HEAD
//
//
//     @Bean
//        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//          http.securityContext().requireExplicitSave(false)
=======
//	  @Bean
//	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		  http.securityContext().requireExplicitSave(false)
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8
//          .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//          .cors().configurationSource(new CorsConfigurationSource() {
//      @Override
//      public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//          CorsConfiguration config = new CorsConfiguration();
//          config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//          config.setAllowedMethods(Collections.singletonList("*"));
//          config.setAllowCredentials(true);
//          config.setAllowedHeaders(Collections.singletonList("*"));
//          config.setMaxAge(3600L);
//          return config;
//      }
//      
//          }).and().csrf().disable()
//          .authorizeHttpRequests()
<<<<<<< HEAD
//              .requestMatchers("/admin/generate-offers").hasRole("ADMIN")
=======
//          	.requestMatchers("/admin/generate-offers").hasRole("ADMIN")
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8
//                  .requestMatchers("/user","/admin").authenticated()
//                  .requestMatchers("/offers","/contact","/register").permitAll()
//          .and().formLogin()
//          .and().httpBasic();
//  return http.build();
//      
<<<<<<< HEAD
//      }
//
//
//
//       @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//}
=======
//	  }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//}
>>>>>>> 492a17beefa5eba56e6c204d40d14fac83c124c8
