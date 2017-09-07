package jshop.database;

import jshop.model.GrantedAuthorityEntity;
import jshop.model.UserEntity;
import jshop.repositories.UserDao;
import jshop.services.UserServiceImpl;
import jshop.storage.UserCache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;


import java.util.*;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {UserServiceImpl.class, JpaTransactionManager.class, UserCache.class, CacheManager.class})
@EnableJpaRepositories(basePackageClasses = UserDao.class)
public class UserRepositoryTests extends AbstractDatabaseTest {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    public void getUserCount() {
        System.out.println(SEPARATOR);
        System.out.println("User count: " + userServiceImpl.count());
    }

    @Test
    @Rollback(true)
    public void createUser() {
        System.out.println(SEPARATOR);
        UserEntity newUser = new UserEntity();
        newUser.setName("NAME");
        newUser.setEmail("test@mail.ru");
        newUser.setPassword("PASS");
        newUser.setRegdate(new Date(System.currentTimeMillis()));
        newUser.setActive(true);
        newUser.setComment("NEWCOMMENT");
        GrantedAuthorityEntity grantedAuthorityEntity = new GrantedAuthorityEntity();
        grantedAuthorityEntity.setAuthority("ROLE_TEST");
        List<UserEntity> userEntitySet = new ArrayList<>();
        userEntitySet.add(newUser);
        grantedAuthorityEntity.setUsers(userEntitySet);
        List<GrantedAuthorityEntity> authorityEntityList = new ArrayList<GrantedAuthorityEntity>();
        authorityEntityList.add(grantedAuthorityEntity);
        newUser.setAuthorities(authorityEntityList);
        System.out.println(newUser);
        userServiceImpl.save(newUser);
    }

}