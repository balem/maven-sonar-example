package py.una.pol.mavensonarexample.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.una.pol.mavensonarexample.entity.UserRole;
import py.una.pol.mavensonarexample.repository.UserRepository;
import py.una.pol.mavensonarexample.service.UserService;


import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        py.una.pol.mavensonarexample.entity.User user = userRepository.findByUsername(username);
        Set<UserRole> roles = new HashSet<>();
        roles.addAll(user.getUserRole());
        List<GrantedAuthority> authorities = buildGrantedAutorities(roles);
        return buildUser(user, authorities);
    }

    private User buildUser(py.una.pol.mavensonarexample.entity.User entity, List<GrantedAuthority> grantedAuthorityList){
        return new User(entity.getUsername(), entity.getPassword(),entity.isEnabled(), true, true, true,grantedAuthorityList);
    }

    private List<GrantedAuthority> buildGrantedAutorities(Set<UserRole> roles){
        Set<GrantedAuthority> auths = new HashSet<>();
        for (UserRole role: roles) {
            auths.add(new SimpleGrantedAuthority(role.getRole()));

        }
        return new ArrayList<>(auths);
    }

    @Override
    public py.una.pol.mavensonarexample.entity.User findByUserName(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public void blockUser(String username) {
        try {
            userRepository.blockUserById(username);
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void setTryLoginCount(String username, Integer count){
        try {
            userRepository.setTryLoginCount(username, count);
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
