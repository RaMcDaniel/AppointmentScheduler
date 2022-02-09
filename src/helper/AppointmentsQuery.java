package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentsQuery {
    public static int getNumAppointments() throws SQLException {
        String sql = "SELECT count(*) FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int numAppointments = 0;
        while(rs.next()){
            numAppointments = rs.getInt(1);
        }
        return numAppointments;
    }
}
