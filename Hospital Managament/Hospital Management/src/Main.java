import java.sql.Connection;
import java.sql.DriverManager;
public class Main {
    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection("jdbc:/mysql://localhost:3006/mysql", "root", "Raj@1212");
            System.out.println(con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }}