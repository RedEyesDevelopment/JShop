package jshop.database;

import jshop.AbstractTest;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import jshop.repositories.UserDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by Gvozd on 06.01.2017.
 */
@ContextConfiguration(classes = {TestDAOConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public abstract class AbstractDatabaseTest extends AbstractTest {
    protected final String SEPARATOR = "**********************************************************";
}