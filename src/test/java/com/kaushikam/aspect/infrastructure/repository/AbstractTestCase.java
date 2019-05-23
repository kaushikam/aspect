package com.kaushikam.aspect.infrastructure.repository;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTestCase {

    protected IDatabaseTester databaseTester;

    public AbstractTestCase() {
        Properties applicationProperties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            applicationProperties.load(inputStream);
            databaseTester = new JdbcDatabaseTester(
                    applicationProperties.getProperty("spring.datasource.driver-class-name"),
                    applicationProperties.getProperty("spring.datasource.url"),
                    applicationProperties.getProperty("spring.datasource.username"),
                    applicationProperties.getProperty("spring.datasource.password")
            );
        } catch (IOException | ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    protected IDataSet getDataSet(String fileName) throws Exception {
        return new FlatXmlDataSetBuilder().build(
                new File(getClass().getClassLoader().getResource(fileName).getFile())
        );
    }
}
