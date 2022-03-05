package com.intacct;

import java.sql.SQLException;

public interface Harmonia {

    boolean hasTable(String tableName) throws SQLException;

    boolean hasColumnName(String tableName, String columnName) throws SQLException;
}