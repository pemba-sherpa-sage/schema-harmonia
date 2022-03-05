package com.intacct.schema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ColumnInfo {
    private String columnName;
    private int columnSize;
    private String dataType;
    private boolean isNullable;
    private boolean isAutoIncrement;

    @AllArgsConstructor
    public enum ColumnLabel
    {
        COLUMN_NAME("COLUMN_NAME"),
        COLUMN_SIZE("COLUMN_SIZE"),
        DATA_TYPE("DATA_TYPE"),
        IS_NULLABLE("IS_NULLABLE"),
        IS_AUTOINCREMENT("IS_AUTOINCREMENT");
        @Getter private String value;
    }
}
