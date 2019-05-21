package com.kaushikam.aspect.infrastructure.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.kaushikam.aspect.domain.model.User;
import com.kaushikam.aspect.domain.model.UserRepository;
import com.kaushikam.aspect.spring.config.ApplicationConfig;
import com.kaushikam.aspect.spring.config.DomainConfig;
import com.kaushikam.aspect.spring.config.PersistenceConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

@RunWith(JUnitPlatform.class)
@ExtendWith({
    SpringExtension.class, DBUnitExtension.class
})
@SpringBootTest(classes = {
        DomainConfig.class,
        PersistenceConfig.class,
        ApplicationConfig.class
})
public class UserRepositoryHibernateTest {

    @Autowired
    private UserRepository userRepository;

    public static Logger logger = LoggerFactory.getLogger("UserRepositoryHibernateTest");

    @Test
    @DisplayName("testing whether user information can be retrieved or not")
    @DataSet("datasets/user.yml")
    public void testUserFind() {
        logger.info("Testing whether user information can be retrieved or not");
        User user = userRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("Kaushik Asokan");
    }
}
