package py.una.pol.mavensonarexample.service;

import py.una.pol.mavensonarexample.entity.User;

public interface UserService {
    User findByUserName(String username);

    void blockUser(String username);

    void setTryLoginCount(String userName, Integer count);
}
