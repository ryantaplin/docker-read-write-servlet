package repositories;

public enum PortInColumn {
    ID,
    MSISDN,
    DATE,
    SERVICE;

    public static Boolean exists(String column) {
        for (PortInColumn c : PortInColumn.values()) {
            if (column.equals(c.toString())) return true;
        }
        return false;
    }
}
