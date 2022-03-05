package com.intacct;

import com.intacct.schema.Schema;
import com.intacct.schema.internal.SchemaHarmonia;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.DataConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, ConfigurationException, SQLException {

        DataConfiguration config = new DataConfiguration(new BaseConfiguration());
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
        PropertiesConfiguration props = new PropertiesConfiguration();
        props.read(new InputStreamReader(resourceAsStream));
        config.append(props);

        System.out.println("Hello World!");
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", config.getString("db.user"));
        connectionProperties.put("password", config.getString("db.password"));


        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(config.getString("db.url"));
        dataSource.setUser(config.getString("db.user"));
        dataSource.setPassword(config.getString("db.password"));


        Schema schema = SchemaHarmonia.getInstance(dataSource);

        boolean customer = schema.hasTable("CUSTOMERS");
        boolean city = schema.hasColumnName("CUSTOMERS", "CITY");


        System.out.println(customer);
        System.out.println(city);

    }
}
