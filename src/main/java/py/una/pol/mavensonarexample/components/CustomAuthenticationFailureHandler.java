package py.una.pol.mavensonarexample.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import py.una.pol.mavensonarexample.entity.User;
import py.una.pol.mavensonarexample.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Value("${userTryLoginLimit}")
    private String userTryLoginLimit;

    private static final String USER_BLOCK = "Usuario bloqueado";
    private static final String INVALID_USER_CREDENTIAL = "Usuario o contrasena incorrecta";

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception instanceof BadCredentialsException) {
            String  username = request.getParameter("username");
            if(username != null){
                User user = userService.findByUserName(username);
                Integer actualUserLoginFailed = user.getLoginTry() +1;
                if(actualUserLoginFailed >= Integer.parseInt(userTryLoginLimit)){
                    if(user.isEnabled())
                        userService.blockUser(user.getUsername());
                    userService.setTryLoginCount(user.getUsername(), actualUserLoginFailed);
                    response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", USER_BLOCK));
                }else{
                    userService.setTryLoginCount(user.getUsername(), actualUserLoginFailed);
                }
            }
            response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", INVALID_USER_CREDENTIAL));
        } else if(exception instanceof DisabledException) {
            response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", USER_BLOCK));

        } else {
            response.sendRedirect(request.getContextPath() + String.format("/login?error=%s", INVALID_USER_CREDENTIAL));
        }
    }
}
