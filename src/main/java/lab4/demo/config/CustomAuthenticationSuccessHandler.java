package lab4.demo.config;

import lab4.demo.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession httpSession=request.getSession();
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        httpSession.setAttribute("username",user.getUsername());
        logger.error(authentication.getAuthorities().toString());
        httpSession.setAttribute("authorities",authentication.getAuthorities());
        response.setStatus(HttpServletResponse.SC_OK);
        logger.error("The end of the SuccessHandler");
    }

}