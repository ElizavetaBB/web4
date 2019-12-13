package lab4.demo.filter;

import lab4.demo.config.CustomAuthenticationFailureHandler;
import lab4.demo.config.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public JWTAuthenticationFilter() {
        this.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        this.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
    }
}
