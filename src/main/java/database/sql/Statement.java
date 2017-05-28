package database.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Statement {

    private java.sql.Statement statement;
    private List<Column> columns = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();

    private String selector = "";
    private String table = "";

    public Statement(Connection connection) throws SQLException {
       statement = connection.createStatement();
    }

    public Statement select(List<Column> columns) {
        if (!selector.isEmpty()) throw new IllegalStateException("Statement already initialised with selector.");
        if (columns.isEmpty()) throw new NullPointerException("columns parameter is necessary.");

        this.selector = "SELECT";
        this.columns = columns;
        return this;
    }

    public Statement from(String table) {
        if (selector.isEmpty()) throw new IllegalStateException("Statement must first be initialised with SELECT method.");
        if (!selector.equals("SELECT")) throw new IllegalStateException("Statement can only read with 'from' with SELECT method.");
        if (table.isEmpty()) throw new NullPointerException("table parameter is necessary.");

        this.table = table;
        return this;
    }

    public Statement insert(List<Blah> toUpdate) {
        if (!selector.isEmpty()) throw new IllegalStateException("Statement already initialised with selector");
        if (toUpdate.isEmpty()) throw new NullPointerException("value parameter is necessary");

        this.selector = "INSERT";
        for(Blah item: toUpdate) {
            columns.add(item.column);
            attributes.add(item.attribute);
        }
        return this;
    }

    public Statement to(String table) {
        if ((selector.isEmpty()) || attributes.isEmpty()) throw new IllegalStateException("Statement must be first initialised with INSERT method.");
        if (!selector.equals("INSERT INTO")) throw new IllegalStateException("Statement can only read 'to' with INSERT method.");
        if (table.isEmpty()) throw new NullPointerException("table parameter is necessary");

        this.table = table;
        return this;
    }

    public ResultSet execute() throws SQLException {
        return statement.executeQuery(build());
    }

    public String build() {
        switch (selector) {
            case "INSERT":
                return buildINSERTstatement();
            case "SELECT":
                return buildSELECTstatement();
            default:
                throw new IllegalStateException("Statement is not fully initialised.");
        }
    }

    private String buildSELECTstatement() {
        return selector + " " + formatColumns(columns) + " FROM " + table;
    }

    private String buildINSERTstatement() {
        return selector + " INTO " + table + " (" + formatColumns(columns) + ") VALUES (" + formatAttributes(attributes) + ")";
    }

    private String formatAttributes(List<Attribute> attributes) {
        String out = "";
        for(Attribute attr: attributes) {
            out = out.concat(attr.value + ",");
        }
        return out;
    }

    private String formatColumns(List<Column> columns) {
        String out = "";
        for(Column col : columns) {
            out = out.concat(col.name + (col.name.equals("*") ? "" : ","));
        }
        return out;
    }
}
