package com.intacct.example;

import com.intacct.Harmonia;
import com.intacct.config.DataSourceConfiguration;
import com.intacct.schema.internal.SchemaHarmonia;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example to show the schema-harmonia
 */
public class App {
    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException, ConfigurationException, SQLException {
        //example query v1
        String query_v1 = "SELECT CUSTOMER_ID, CUSTOMER_NAME, CITY FROM CUSTOMERS";

        //example query v2
        String query_v2 = "SELECT CUSTOMER_ID, CUSTOMER_NAME, CITY, ZIP_CODE FROM CUSTOMERS";

        DataSource dataSource = DataSourceConfiguration.getDataSource(DataSourceConfiguration.Source.CONFIG_PROPERTIES);
        Connection connection = dataSource.getConnection();

        Statement stmt = connection.createStatement();

        //create an instance of schema harmonia
        Harmonia schema = SchemaHarmonia.getInstance();

        ResultSet resultSet = null;

        if (schema.hasColumnName("customers", "zip_code")) {
            resultSet = stmt.executeQuery(query_v2);
        } else {
            logger.warn("column {} not found in table {}. falling to back to v1","zip_code","customers");
            resultSet = stmt.executeQuery(query_v1);
        }

        System.out.println("Query execution Complete!");

    }
}
