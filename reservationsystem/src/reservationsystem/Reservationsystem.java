
package reservationsystem;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Reservationsystem extends JFrame {
    private Connection con;
    private PreparedStatement pst;

    public Reservationsystem() {
        getContentPane().setBackground(new Color(0, 128, 0));
        setSize(500,300);
      setLocation(530,250);
      setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservationsystem", "root", "123456");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

   public void makeReservation(String name, String trainNumber, String classType, String dateOfJourney, String from, String to) {
    try {
        // Retrieve the train name based on the train number (you may need to query your database)
        String trainName = getTrainName(trainNumber);

        // Perform the reservation and insert the details into the reservations table
        String sql = "INSERT INTO reservations (name, trainNumber, trainName, classType, dateOfJourney, fromLocation, toLocation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, trainNumber);
        pst.setString(3, trainName); // Assign the train name
        pst.setString(4, classType);
        pst.setString(5, dateOfJourney);
        pst.setString(6, from);
        pst.setString(7, to);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Reservation successful.");
 
       
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    
}

private String getTrainName(String trainNumber) {
    // You may implement logic here to fetch the train name from your database or a predefined list
    // For simplicity, let's assume you have a predefined list based on train numbers
    // Replace this with your actual implementation
    if ("12345".equals(trainNumber)) {
        return "Express Train A";
    } else if ("67890".equals(trainNumber)) {
        return "Local Train B";
    } else {
        return "Unknown Train";
    }
}


    public void cancelReservation(String pnr) {
        try {
            String sql = "DELETE FROM reservations WHERE pnr=?";
             
            pst = con.prepareStatement(sql);
            pst.setString(1, pnr);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cancellation successful.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void main(String[] args) {
        // Create an instance of ReservationSystem
         
        // Create instances of UserLogin and ReservationSystem
        UserLogin userLogin = new UserLogin();
        Reservationsystem reservationSystem = new Reservationsystem();
         

        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Make Reservation\n2. Cancel Reservation\n3. Exit");

            if (choice == null) {
                break; // Exit if the user closes the dialog
            }

            switch (choice) {
                case "1":
                    // Check if the user is authenticated
                    String username = JOptionPane.showInputDialog("Enter Username:");
                    String password = JOptionPane.showInputDialog("Enter Password:");
                    boolean authenticated = userLogin.authenticateUser(username, password);

                    if (authenticated) {
                        String name = JOptionPane.showInputDialog("Enter name:");
                String trainNumber = JOptionPane.showInputDialog("Enter Train Number:");
                String classType = JOptionPane.showInputDialog("Enter Class Type:");
                String dateOfJourney = JOptionPane.showInputDialog("Enter Date of Journey:");
                String from = JOptionPane.showInputDialog("Enter From (Place):");
                String to = JOptionPane.showInputDialog("Enter To Destination:");
                reservationSystem.makeReservation(name, trainNumber, classType, dateOfJourney, from, to);
                
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials.");
                    }
                    break;
                case "2":
                    String cancelPNR = JOptionPane.showInputDialog("Enter PNR to Cancel:");
                    reservationSystem.cancelReservation(cancelPNR);
                    break;
                case "3":
                    System.exit(0); // Exit the program
                    
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
                    break;
            }
        }
    }
}