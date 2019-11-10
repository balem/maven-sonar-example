package py.una.pol.mavensonarexample.components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import py.una.pol.mavensonarexample.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String HOME_PAGE = "home";

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        py.una.pol.mavensonarexample.entity.User userBean = userService.findByUserName(authUser.getUsername());
        userService.setTryLoginCount(userBean.getUsername(), 0);
        response.sendRedirect(request.getContextPath() + HOME_PAGE);
    }
}
