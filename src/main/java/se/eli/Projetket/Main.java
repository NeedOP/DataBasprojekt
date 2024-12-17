package se.eli.Projetket;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorkRoleDAO workRoleDAO = new WorkRoleDAO();
        Scanner scanner = new Scanner(System.in);
        try {
            JDBCUtil.loadProperties("test.properties");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.println("\nVälj operation:");
            System.out.println("1. Lägg till arbetsroll");
            System.out.println("2. Hämta alla arbetsroller");
            System.out.println("3. Hämta en arbetsroll");
            System.out.println("4. Uppdatera arbetsroll");
            System.out.println("5. Ta bort arbetsroll");
            System.out.println("6. Avsluta");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Ange ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ange titel: ");
                    String title = scanner.nextLine();
                    System.out.print("Ange beskrivning: ");
                    String description = scanner.nextLine();
                    System.out.print("Ange lön: ");
                    double salary = scanner.nextDouble();
                    Date date = new Date(System.currentTimeMillis());
                    workRoleDAO.insertWorkRole(new WorkRole(id, title, description, salary, date));
                    System.out.println("Arbetsrollen har lagts till!");
                }
                case 2 -> {
                    System.out.println("\nAlla arbetsroller:");
                    for (WorkRole role : workRoleDAO.getAllWorkRoles()) {
                        System.out.println("ID: " + role.getRoleId());
                        System.out.println("Titel: " + role.getTitle());
                        System.out.println("Beskrivning: " + role.getDescription());
                        System.out.println("Lön: " + role.getSalary());
                        System.out.println("Skapelsedatum: " + role.getCreationDate());
                        System.out.println("--------------------------");
                    }
                }
                case 3 -> {
                    System.out.print("Ange ID för arbetsroll att hämta: ");
                    int id = scanner.nextInt();
                    WorkRole role = workRoleDAO.getWorkRoleById(id);
                    if (role != null) {
                        System.out.println("\nHämtad arbetsroll:");
                        System.out.println("ID: " + role.getRoleId());
                        System.out.println("Titel: " + role.getTitle());
                        System.out.println("Beskrivning: " + role.getDescription());
                        System.out.println("Lön: " + role.getSalary());
                        System.out.println("Skapelsedatum: " + role.getCreationDate());
                    } else {
                        System.out.println("Ingen arbetsroll hittades med ID: " + id);
                    }
                }
                case 4 -> {
                    System.out.print("Ange ID för arbetsrollen att uppdatera: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ange ny titel: ");
                    String title = scanner.nextLine();
                    System.out.print("Ange ny beskrivning: ");
                    String description = scanner.nextLine();
                    System.out.print("Ange ny lön: ");
                    double salary = scanner.nextDouble();
                    Date date = new Date(System.currentTimeMillis());

                    workRoleDAO.updateWorkRole(new WorkRole(id, title, description, salary, date));
                    System.out.println("Arbetsrollen har uppdaterats!");
                }
                case 5 -> {
                    System.out.print("Ange ID för arbetsrollen att ta bort: ");
                    int id = scanner.nextInt();
                    workRoleDAO.deleteWorkRole(id);
                    System.out.println("Arbetsrollen har tagits bort!");
                }
                case 6 -> {
                    System.out.println("Programmet avslutas...");
                    System.exit(0);
                }
                default -> System.out.println("Ogiltigt val! Försök igen.");
            }
        }
    }
}
