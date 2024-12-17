package se.eli.Projetket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAO {

    // Skapa ny arbetsroll
    public void insertWorkRole(WorkRole workRole) {
        String sql = "INSERT INTO work_role (role_id, title, description, salary, creation_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, workRole.getRoleId());
            stmt.setString(2, workRole.getTitle());
            stmt.setString(3, workRole.getDescription());
            stmt.setDouble(4, workRole.getSalary());
            stmt.setDate(5, workRole.getCreationDate());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kunde inte skapa arbetsrollen.");
        }
    }

    // Hämta alla arbetsroller
    public List<WorkRole> getAllWorkRoles() {
        List<WorkRole> roles = new ArrayList<>();
        String sql = "SELECT * FROM work_role";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WorkRole role = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // Hämta en specifik arbetsroll
    public WorkRole getWorkRoleById(int id) {
        String sql = "SELECT * FROM work_role WHERE role_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkRole(
                            rs.getInt("role_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDouble("salary"),
                            rs.getDate("creation_date")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Uppdatera arbetsroll
    public void updateWorkRole(WorkRole workRole) {
        String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workRole.getTitle());
            stmt.setString(2, workRole.getDescription());
            stmt.setDouble(3, workRole.getSalary());
            stmt.setDate(4, workRole.getCreationDate());
            stmt.setInt(5, workRole.getRoleId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ta bort arbetsroll
    public void deleteWorkRole(int id) {
        String sql = "DELETE FROM work_role WHERE role_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
