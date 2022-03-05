package com.intacct.schema;

import java.sql.SQLException;

public interface Schema {

    boolean hasTable(String tableName) throws SQLException;

    boolean hasColumnName(String tableName, String columnName) throws SQLException;
}