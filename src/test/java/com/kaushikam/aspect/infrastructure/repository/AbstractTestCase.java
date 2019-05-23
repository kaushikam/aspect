package com.kaushikam.aspect.infrastructure.repository;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class AbstractTestCase {

    protected static IDatabaseTester databaseTester;
    protected static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void init() throws IOException, ClassNotFoundException {
        entityManagerFactory = Persistence.createEntityManagerFactory("user");
        entityManager = entityManagerFactory.createEntityManager();

        Properties applicationProperties = new Properties();
        applicationProperties.load(AbstractTestCase.class.getClassLoader()
                .getResourceAsStream("application.properties"));
        databaseTester = new JdbcDatabaseTester(
                applicationProperties.getProperty("spring.datasource.driver-class-name"),
                applicationProperties.getProperty("spring.datasource.url"),
                applicationProperties.getProperty("spring.datasource.username"),
                applicationProperties.getProperty("spring.datasource.password"));
    }

    @AfterAll
    public static void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    protected IDataSet getDataSet(String fileName) throws Exception {
        return new FlatXmlDataSetBuilder().build(
                new File(getClass().getClassLoader().getResource(fileName).getFile())
        );
    }
}
