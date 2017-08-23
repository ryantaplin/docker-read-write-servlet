package server.database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO implements DatabaseColumn - not quiet what I wanted. Can this be done?
public enum StaffColumn {
    ID,
    TITLE,
    FIRSTNAME,
    SURNAME;

    public String getValueAsString(ResultSet resultSet) {
        int columnNumber = getColumnNumber(resultSet);
        try {
            if (columnNumber >= 0)
                return resultSet.getString(columnNumber);
        } catch (SQLException e) {
            // what error - leave for now printing stack trace.
            e.printStackTrace();
        }
        return "NO VALUE FOUND";
    }

    private int getColumnNumber(ResultSet resultSet) {
        String columnName = String.valueOf(this);
        try {
            return resultSet.findColumn(columnName);
        } catch (SQLException e) {
            System.out.println("The column: " + columnName +  " does not exist in this resultSet.");
        }
        return -1;
    }
}
