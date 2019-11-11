package py.una.pol.mavensonarexample.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import py.una.pol.mavensonarexample.entity.User;
import py.una.pol.mavensonarexample.repository.UserRepository;
import py.una.pol.mavensonarexample.service.UserService;
import py.una.pol.mavensonarexample.service.impl.UserServiceImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomAuthenticationFailureHandlerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks // Se autoinecta userRepository
    private UserService userService = new UserServiceImpl();

    @BeforeEach
    void setMock(){
        when(userRepository.findById(any())).thenReturn(Optional.of(new User("username", "password", true, null, 0)));
        when(userRepository.findByUsername(any())).thenReturn(new User("username", "password", true, null, 0));
    }



    @Test
    void ok() {
        assertEquals(userService.findByUserName("username").getUsername(), "username");

    }

}
