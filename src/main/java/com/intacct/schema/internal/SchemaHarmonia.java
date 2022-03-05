package com.intacct.schema.internal;

import com.intacct.schema.Schema;
import com.intacct.schema.model.ColumnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaHarmonia implements Schema {
    private final Logger logger = LoggerFactory.getLogger(com.intacct.schema.Schema.class);
    private final DataSource dataSource;

    private SchemaHarmonia(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Schema getInstance(DataSource dataSource) {
        return new SchemaHarmonia(dataSource);
    }

    @Override
    public boolean hasTable(String tableName) throws SQLException {

        String tableNameUpper = tableName.toUpperCase();

        Connection connection = dataSource.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(connection.getCatalog(), null, tableNameUpper, new String[]{"TABLE"});
        while (tables.next()) {
            String name = tables.getString("TABLE_NAME");
            String schema = tables.getString("TABLE_SCHEM");
            logger.info("found table {} in schema {}", name, schema);
            return true;
        }
        logger.info("not found table {}", tableName);
        return false;
    }

    @Override
    public boolean hasColumnName(String tableName, String columnName) throws SQLException {

        String tableNameUpper = tableName.toUpperCase();
        String columnNameUpper = columnName.toUpperCase();

        Connection connection = dataSource.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet columns = databaseMetaData.getColumns(null, null, tableNameUpper, columnNameUpper);

        while (columns.next()) {
            //should cache this for quick look up
            ColumnInfo columnInfo = ColumnInfo.builder()
                    .columnName(columns.getString(ColumnInfo.ColumnLabel.COLUMN_NAME.getValue()))
                    .columnSize(Integer.parseInt(columns.getString(ColumnInfo.ColumnLabel.COLUMN_SIZE.getValue())))
                    .dataType(columns.getString(ColumnInfo.ColumnLabel.DATA_TYPE.getValue()))
                    .isNullable(Boolean.parseBoolean(columns.getString(ColumnInfo.ColumnLabel.IS_NULLABLE.getValue())))
                    .isAutoIncrement(Boolean.parseBoolean(columns.getString(ColumnInfo.ColumnLabel.IS_AUTOINCREMENT.getValue())))
                    .build();
            logger.info("found column {} in table {}", columnName, tableName);
            return true;

        }
        logger.info("not found column {} in table {}", columnName, tableName);
        return false;
    }
}

