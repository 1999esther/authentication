package dev.esther.authentication.config;


import dev.esther.authentication.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    public final UserRepository repository;

   @Bean
   public UserDetailsService userDetailsService(){
       return username -> repository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("user not found"));

   }
}
