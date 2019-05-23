package com.kaushikam.aspect.infrastructure.repository;

import com.kaushikam.aspect.domain.model.User;
import com.kaushikam.aspect.domain.model.UserRepository;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

@RunWith(JUnitPlatform.class)
public class UserRepositoryHibernateTest extends AbstractTestCase {

    private UserRepository userRepository = new UserRepositoryHibernate(entityManager);

    private static Logger logger = LoggerFactory.getLogger("UserRepositoryHibernateTest");

    @Test
    @DisplayName("testing whether user information can be retrieved or not")
    public void testUserFind() throws Exception {
        databaseTester.setDataSet(getDataSet("datasets/user.xml"));
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();

        logger.info("Testing whether user information can be retrieved or not");
        User user = userRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Kaushik Asokan");
    }
}
