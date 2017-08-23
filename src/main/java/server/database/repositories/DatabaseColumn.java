package server.database.repositories;

public interface DatabaseColumn {

    Integer getColumnNumber();

    String getColumnValue(int columnNumber);

}
