package database.sql;

public class Query {

    public static String queryAllFromTable(String table) {
        return String.format("SELECT * FROM %s", table);
    }
}
