package com.intacct.config.util;

import lombok.Builder;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.SQLException;

/**
 * a convenient builder to create the DataSource based on the provided connecton information
 */
public class SchemaDataSource {
    @Builder(builderMethodName = "builder")
    public static OracleDataSource newOracleDataSource(String url, String userName, String password) throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(userName);
        dataSource.setPassword(password);

        return dataSource;
    }
}
