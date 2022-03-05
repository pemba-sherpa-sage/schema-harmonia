package com.intacct.config;

import com.intacct.config.util.SchemaDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.DataConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DataSourceConfiguration {

    public enum Source {
        CONFIG_PROPERTIES
    }

    public static DataSource getDataSource(String jdbcUrl, String userName, String password) throws SQLException {
        return SchemaDataSource.builder()
                .url(jdbcUrl)
                .userName(userName)
                .password(password)
                .build();
    }


    public static DataSource getDataSource(DataSourceConfiguration.Source configSource) throws SQLException, ConfigurationException, IOException {

        if (configSource == Source.CONFIG_PROPERTIES) {
            DataConfiguration config = new DataConfiguration(new BaseConfiguration());
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
            PropertiesConfiguration props = new PropertiesConfiguration();
            props.read(new InputStreamReader(resourceAsStream));
            config.append(props);

            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setURL(config.getString("db.url"));
            dataSource.setUser(config.getString("db.user"));
            dataSource.setPassword(config.getString("db.password"));

            return dataSource;
        }

        throw new UnsupportedOperationException("only Source.CONFIG is supported");

    }

}
