package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    public Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        executer(String.format("create table if not exists %s(%s);", tableName, "id serial primary key"), tableName, true);
        System.out.printf("Database '%s' created!%n", tableName);
    }

    public void dropTable(String tableName) throws Exception {
        executer(String.format("drop table %s;", tableName), tableName, false);
        System.out.printf("Database '%s' dropped!%n", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        executer(String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type), tableName, true);
        System.out.printf("Column '%s' with type '%s' is added into table '%s'!%n", columnName, type, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        executer(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName), tableName, true);
        System.out.printf("Column '%s' is dropped from table '%s'!%n", columnName, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        executer(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName), tableName, true);
        System.out.printf("Column '%s' renamed to '%s' in table '%s'!%n", columnName, newColumnName, tableName);
    }

    public void executer(String sql, String tableName, boolean flag) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                if (flag) {
                    System.out.println(getTableScheme(connection, tableName));
                }
            }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        String tableName = "simple_table";
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "Column1", "serial");
            tableEditor.renameColumn(tableName, "Column1", "newColumn");
            tableEditor.dropColumn(tableName, "newColumn");
            tableEditor.dropTable(tableName);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}