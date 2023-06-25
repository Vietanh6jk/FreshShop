package iuh.dhktpm15.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomUserDetailsService customUserDetailsService;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home/**","/dangky","/search",
                                "/image/**",
                                "/css/**",
                                "/js/**").permitAll()
                        .requestMatchers("/quanly/**").hasAuthority("ADMIN")
                        .requestMatchers("/loai/**").hasAuthority("ADMIN")
                        .requestMatchers("/gio/**").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("std")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> {
                    logout.permitAll();
                })

                .httpBasic(Customizer.withDefaults())

                .exceptionHandling()
                .accessDeniedPage("/403");

        return http.build();

    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//       auth.
//               userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
////        UserDetails ud_teo = User.builder()
////                .username("teo")
////                .password("$2a$10$iVIV6KTf2GdAAo9FpLVpsubzVP8undhidTtCyftx.OnfBn5yn2eZW")
////                .roles("ADMIN_ROLE")
////                .build();
////        UserDetails ud_ty = User.builder()
////                .username("ty")
////                .password("$2a$10$hFhz/f3e0GI5y4baqAvdX.BlEiMivXXcOU9kKNgyUDCBi1f7hbmFa")
////                .roles("USER_ROLE")
////                .build();
////        auth.inMemoryAuthentication()
////                .withUser(ud_teo)
////                .withUser(ud_ty);
//    }




//    @Bean
//    public UserDetailsService userDetailsService() {
////        UserDetails ud_teo = User.builder()
////                .username("teo")
////                .password(passwordEncoder().encode("12345"))
////                .roles("USER")
////                .build();
//
//        return new CustomUserDetailsService();
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
}