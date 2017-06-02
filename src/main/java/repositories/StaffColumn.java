package repositories;

public enum StaffColumn {
    ID,
    TITLE,
    FIRSTNAME,
    SURNAME;

    public static Boolean exists(String column) {
        for (StaffColumn c : StaffColumn.values()) {
            if (column.equals(c.toString())) return true;
        }
        return false;
    }
}
