package application.config;

import application.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsImpl")
    private UserDetailsImpl userDetailsService;

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Дайте просто умереть");
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home","/api/register").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("http://localhost:8080/#/login")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        /*http.core()
                .authorizeRequests().antMatchers("/register","/logout").permitAll()
                .anyRequest().fullyAuthenticated().and()
                .formLogin()
                .loginPage("http://localhost:8080/login").loginProcessingUrl("api/login.action")
                .failureForwardUrl("http://localhost:8080/login?error=true")
                .successForwardUrl("http://localhost:8080/main")
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")).and()
                // configuring the session on the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods().allowedHeaders();
            }
        };
    }
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:8180"));
        configuration.setAllowedMethods(Arrays.asList(  RequestMethod.GET.name(),
                    RequestMethod.POST.name(),
                    RequestMethod.OPTIONS.name(),
                    RequestMethod.DELETE.name(),
                    RequestMethod.PUT.name()));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token", "x-requested-with", "x-xsrf-token"));
        configuration.setAllowedHeaders(Arrays.asList("X-Auth-Token","x-auth-token", "x-requested-with", "x-xsrf-token"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

}
