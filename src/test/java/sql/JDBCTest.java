package sql;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {

    @BeforeClass
    public static void init() throws Exception{
        JDBCUtils jdbcUtils = new JDBCUtils("h2-properties.xml");
        Connection connection = null;
        try {
            connection = jdbcUtils.getConnection("sa", "");
            JDBCUtils.initializeTables(connection, "~/test", "h2");
        } catch (Exception ex) {

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    public void testJDBCConnection() throws Exception {
        JDBCUtils jdbcUtils = new JDBCUtils("h2-properties.xml");
        Connection connection = jdbcUtils.getConnection("sa", "");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SUPPLIERS");

        while (rs.next()) {
            String supName = rs.getString("SUP_NAME");
            String street = rs.getString("STREET");
            String zip = rs.getString("ZIP");
            System.out.println(supName + "," + street + "," + zip);
        }
    }
}
