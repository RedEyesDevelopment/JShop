package jshop.database;

import jshop.repositories.UserDao;
import jshop.services.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;


import static org.junit.Assert.*;

@ContextConfiguration(classes = {UserServiceImpl.class, JpaTransactionManager.class })
@EnableJpaRepositories( basePackageClasses = UserDao.class )
public class UserRepositoryTests extends AbstractDatabaseTest {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    @Rollback(true)
    public void getAllUsers() {
        System.out.println(SEPARATOR);
        try {
            System.out.println("User count: "+userServiceImpl.count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}