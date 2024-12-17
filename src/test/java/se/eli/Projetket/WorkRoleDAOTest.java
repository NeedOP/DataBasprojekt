package se.eli.Projetket;


import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkRoleDAOTest {

    private static Connection connection;
    private static WorkRoleDAO workRoleDAO;

    @BeforeAll
    public static void setup() throws SQLException {
        try {
            JDBCUtil.loadProperties("test.properties");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection = JDBCUtil.getConnection();
        workRoleDAO = new WorkRoleDAO();

        String createTableSQL = "CREATE TABLE work_role (" +
                "role_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(50) NOT NULL, " +
                "description VARCHAR(50) NOT NULL, " +
                "salary DOUBLE NOT NULL, " +
                "creation_date DATE NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @Test
    public void testInsertAndFetchAllWorkRoles() throws SQLException {
        WorkRole workRole = new WorkRole(1, "Utvecklare", "Utvecklar program", 60000, new Date(System.currentTimeMillis()));
        workRoleDAO.insertWorkRole(workRole);

        List<WorkRole> roles = workRoleDAO.getAllWorkRoles();

        assertEquals(1, roles.size());
        assertEquals("Utvecklare", roles.get(0).getTitle());
        assertEquals("Utvecklar program", roles.get(0).getDescription());
        assertEquals(60000, roles.get(0).getSalary());
    }

    @AfterAll
    public static void teardown() throws SQLException {
        JDBCUtil.close(connection);
    }
}
