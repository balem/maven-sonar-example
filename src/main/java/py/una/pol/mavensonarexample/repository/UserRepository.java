package py.una.pol.mavensonarexample.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import py.una.pol.mavensonarexample.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.enabled = false where u.username = :username")
    void blockUserById(@Param("username") String username);

    @Modifying
    @Query("update User u set u.loginTry = :count where u.username = :username")
    void setTryLoginCount(@Param("username") String username, @Param("count") Integer count);

}
