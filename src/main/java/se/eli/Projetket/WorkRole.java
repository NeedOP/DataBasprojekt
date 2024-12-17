package se.eli.Projetket;
import java.sql.Date;

public class WorkRole {
    private int roleId;
    private String title;
    private String description;
    private double salary;
    private Date creationDate;


    public WorkRole(int roleId, String title, String description, double salary, Date creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public int getRoleId() {
        return roleId;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getSalary() {
        return salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
