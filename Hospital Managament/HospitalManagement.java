import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HospitalManagement {
    public static void main(String[] args) {
        new LoginFrame();
    }
}

// ===== Utility: Database Connection =
class DBConnection {
    static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital", "root", "Raj@1212");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e);
            return null;
        }
    }

}

// ===== Login Frame =====
class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Hospital Login");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        int panelWidth = 300;
        int panelHeight = 180;

        JPanel loginPanel = new JPanel(null);
        loginPanel.setBackground(new Color(224, 255, 255));
        loginPanel.setBounds((getWidth() - panelWidth) / 2, (getHeight() - panelHeight) / 2, panelWidth, panelHeight);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 160, 25);
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        userText.setBackground(new Color(255, 255, 240));
        loginPanel.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 60, 80, 25);
        loginPanel.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(100, 60, 160, 25);
        passText.setFont(new Font("Arial", Font.PLAIN, 14));
        passText.setBackground(new Color(255, 255, 240));
        loginPanel.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 100, 30);
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            if (userText.getText().equals("abc") && new String(passText.getPassword()).equals("abc")) {
                new DashboardFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        add(loginPanel);
        setVisible(true);
    }
}


// ===== Dashboard Frame =====
class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Hospital Dashboard");
        setSize(1000, 1300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.green);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 250, 255));

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        JButton addPatientBtn = new JButton("Add Patient");
        addPatientBtn.setBounds(220, 130, 160, 35);
        styleButton(addPatientBtn, buttonFont, new Color(70, 130, 180));
        add(addPatientBtn);

        JButton viewPatientsBtn = new JButton("View Patients");
        viewPatientsBtn.setBounds(220, 180, 160, 35);
        styleButton(viewPatientsBtn, buttonFont, new Color(60, 179, 113));
        add(viewPatientsBtn);

        JButton addDoctorBtn = new JButton("Add Doctor");
        addDoctorBtn.setBounds(450, 130, 160, 35);
        styleButton(addDoctorBtn, buttonFont, new Color(70, 130, 180));
        add(addDoctorBtn);

        JButton viewDoctorsBtn = new JButton("View Doctors");
        viewDoctorsBtn.setBounds(450, 180, 160, 35);
        styleButton(viewDoctorsBtn, buttonFont, new Color(60, 179, 113));
        add(viewDoctorsBtn);
        JButton addNurseBtn = new JButton("Add Nurse");
        addNurseBtn.setBounds(700, 130, 160, 35);
        styleButton(addNurseBtn, buttonFont, new Color(70, 130, 180));
        add(addNurseBtn);

        JButton viewNurseBtn = new JButton("View Nurses");
        viewNurseBtn.setBounds(700, 180, 160, 35);
        styleButton(viewNurseBtn, buttonFont, new Color(60, 179, 113));
        add(viewNurseBtn);



        JButton assignRoomBtn = new JButton("Assign Room");
        assignRoomBtn.setBounds(220, 230, 160, 35);
        styleButton(assignRoomBtn, buttonFont, new Color(123, 104, 238));
        add(assignRoomBtn);
        JButton bookAppointmentBtn = new JButton("Book Appointment");
        bookAppointmentBtn.setBounds(450, 230, 160, 35);
        styleButton(bookAppointmentBtn, buttonFont, new Color(255, 140, 0));
        add(bookAppointmentBtn);

        JButton helpBtn = new JButton("Help");
        helpBtn.setBounds(450, 500, 160, 35);
        styleButton(helpBtn, buttonFont, new Color(30, 144, 255));
        add(helpBtn);

        JButton complainBtn = new JButton("Complain");
        complainBtn.setBounds(450, 400, 160, 35);
        styleButton(complainBtn, buttonFont, new Color(255, 69, 0));
        add(complainBtn);

        JButton viewComplaintsBtn = new JButton("View Complaints");
        viewComplaintsBtn.setBounds(450, 450, 160, 35);
        styleButton(viewComplaintsBtn, buttonFont, new Color(60, 179, 113));
        add(viewComplaintsBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(450, 700, 160, 35);
        styleButton(exitBtn, buttonFont, new Color(220, 20, 60));
        add(exitBtn);




        viewComplaintsBtn.addActionListener(e -> new ViewComplaintsFrame());
        addNurseBtn.addActionListener(e -> new AddNurseFrame());
        viewNurseBtn.addActionListener(e -> new ViewNurseFrame());
        bookAppointmentBtn.addActionListener(e -> new BookAppointmentFrame());
        assignRoomBtn.addActionListener(e -> new AssignRoomFrame());
        addPatientBtn.addActionListener(e -> new AddPatientsFrame());
        viewPatientsBtn.addActionListener(e -> new ViewPatientsFrame());
        addDoctorBtn.addActionListener(e -> new AddDoctorFrame());
        viewDoctorsBtn.addActionListener(e -> new ViewDoctorsFrame());
        exitBtn.addActionListener(e -> System.exit(0));
        helpBtn.addActionListener(e -> new HelpFrame());
        complainBtn.addActionListener(e -> new ComplainFrame());


        setVisible(true);
    }

    private void styleButton(JButton button, Font font, Color bgColor) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
    }
}

// ===== Add Patient Frame =====
class AddPatientsFrame extends JFrame {
    public AddPatientsFrame() {
        setTitle("Add Patient");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(250, 255, 255));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10, 20, 80, 25);
        idLabel.setFont(labelFont);
        add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(100, 20, 160, 25);
        idField.setFont(fieldFont);
        idField.setBackground(new Color(255, 255, 240));
        add(idField);


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 60, 80, 25);
        nameLabel.setFont(labelFont);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 60, 160, 25);
        nameField.setFont(fieldFont);
        nameField.setBackground(new Color(255, 255, 240));
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 100, 80, 25);
        ageLabel.setFont(labelFont);
        add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(100, 100, 160, 25);
        ageField.setFont(fieldFont);
        ageField.setBackground(new Color(255, 255, 240));
        add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 140, 80, 25);
        genderLabel.setFont(labelFont);
        add(genderLabel);

        JTextField genderField = new JTextField();
        genderField.setBounds(100, 140, 160, 25);
        genderField.setFont(fieldFont);
        genderField.setBackground(new Color(255, 255, 240));
        add(genderField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(10, 180, 80, 25);
        contactLabel.setFont(labelFont);
        add(contactLabel);

        JTextField contactField = new JTextField();
        contactField.setBounds(100, 180, 160, 25);
        contactField.setFont(fieldFont);
        contactField.setBackground(new Color(255, 255, 240));
        add(contactField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(130, 220, 100, 30);
        addBtn.setBackground(new Color(70, 130, 180));
        addBtn.setForeground(Color.WHITE);
        add(addBtn);

        addBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();
            String gender = genderField.getText();
            String contact = contactField.getText();

            if (id.isEmpty()||name.isEmpty() || ageText.isEmpty() || gender.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            try {

                int age = Integer.parseInt(ageText);
                int patientId = Integer.parseInt(id);
                Connection conn = DBConnection.connect();
                if (conn != null) {
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients (id, name, age, gender, contact) VALUES (?, ?, ?, ?,?)");
                    stmt.setInt(1,patientId);
                    stmt.setString(2, name);
                    stmt.setInt(3, age);
                    stmt.setString(4, gender);
                    stmt.setString(5, contact);
                    stmt.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Patient added successfully.");
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}

// ===== View Patients Frame =====
class ViewPatientsFrame extends JFrame {
    public ViewPatientsFrame() {
        setTitle("View Patients");
        setSize(800, 500);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Age", "Gender", "Contact","Room"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DBConnection.connect();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients");
                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("contact"),
                            rs.getString("room_number")
                    };
                    model.addRow(row);
                }
                conn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setVisible(true);
    }
}

// ===== Add Doctor Frame =====
class AddDoctorFrame extends JFrame {
    public AddDoctorFrame() {
        setTitle("Add Doctor");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(250, 255, 250));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 80, 25);
        add(idLabel);
        JTextField idField = new JTextField();
        idField.setBounds(100, 20, 160, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 60, 80, 25);
        add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 60, 160, 25);
        add(nameField);

        JLabel specLabel = new JLabel("Specialty:");
        specLabel.setBounds(10, 100, 80, 25);
        add(specLabel);
        JTextField specField = new JTextField();
        specField.setBounds(100, 100, 160, 25);
        add(specField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(10, 140, 80, 25);
        add(contactLabel);
        JTextField contactField = new JTextField();
        contactField.setBounds(100, 140, 160, 25);
        add(contactField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(130, 190, 100, 30);
        addBtn.setBackground(new Color(100, 149, 237));
        addBtn.setForeground(Color.WHITE);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String specialty = specField.getText();
                String contact = contactField.getText();

                Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO doctors (id, name, specialty, contact) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, specialty);
                stmt.setString(4, contact);
                stmt.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(this, "Doctor added successfully.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}

// ===== View Doctors Frame =====
class ViewDoctorsFrame extends JFrame {
    public ViewDoctorsFrame() {
        setTitle("View Doctors");
        setSize(800, 500);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Specialty", "Contact"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("contact")
                };
                model.addRow(row);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);
    }
}
class AddNurseFrame extends JFrame {
    public AddNurseFrame() {
        setTitle("Add Nurse");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 255, 250));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 200, 25);
        add(nameField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(30, 70, 100, 25);
        add(deptLabel);
        JTextField deptField = new JTextField();
        deptField.setBounds(130, 70, 200, 25);
        add(deptField);

        JLabel shiftLabel = new JLabel("Shift:");
        shiftLabel.setBounds(30, 110, 100, 25);
        add(shiftLabel);
        JTextField shiftField = new JTextField();
        shiftField.setBounds(130, 110, 200, 25);
        add(shiftField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 150, 100, 25);
        add(phoneLabel);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(130, 150, 200, 25);
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 190, 100, 25);
        add(emailLabel);
        JTextField emailField = new JTextField();
        emailField.setBounds(130, 190, 200, 25);
        add(emailField);

        JButton addBtn = new JButton("Add Nurse");
        addBtn.setBounds(130, 240, 120, 30);
        addBtn.setBackground(new Color(46, 139, 87));
        addBtn.setForeground(Color.WHITE);
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO nurses (name, department, shift, phone, email) VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, nameField.getText());
                stmt.setString(2, deptField.getText());
                stmt.setString(3, shiftField.getText());
                stmt.setString(4, phoneField.getText());
                stmt.setString(5, emailField.getText());
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Nurse added successfully.");
                conn.close();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
class ViewNurseFrame extends JFrame {
    public ViewNurseFrame() {
        setTitle("Nurse List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Department", "Shift", "Phone", "Email"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nurses");

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("shift"),
                        rs.getString("phone"),
                        rs.getString("email")
                };
                model.addRow(row);
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

        setVisible(true);
    }
}

// ===== Assign Room Frame =====
class AssignRoomFrame extends JFrame {
    public AssignRoomFrame() {
        setTitle("Assign Room to Patient");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 255, 250));

        JLabel patientLabel = new JLabel("Select Patient:");
        patientLabel.setBounds(30, 30, 120, 25);
        add(patientLabel);

        JComboBox<String> patientBox = new JComboBox<>();
        patientBox.setBounds(160, 30, 180, 25);
        add(patientBox);

        JLabel roomLabel = new JLabel("Select Room:");
        roomLabel.setBounds(30, 80, 120, 25);
        add(roomLabel);

        JComboBox<String> roomBox = new JComboBox<>();
        roomBox.setBounds(160, 80, 180, 25);
        add(roomBox);

        // Load patients and rooms from database
        try {
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();

            // Load patients
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM patients");
            while (rs.next()) {
                patientBox.addItem(rs.getInt("id") + " - " + rs.getString("name"));
            }

            // Load available rooms
            rs = stmt.executeQuery("SELECT room_number FROM rooms");
            while (rs.next()) {
                roomBox.addItem(rs.getString("room_number"));
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        JButton assignBtn = new JButton("Assign");
        assignBtn.setBounds(140, 140, 100, 30);
        assignBtn.setBackground(new Color(100, 149, 237));
        assignBtn.setForeground(Color.WHITE);
        add(assignBtn);

        assignBtn.addActionListener(e -> {
            try {
                String patientEntry = (String) patientBox.getSelectedItem();
                String roomNumber = (String) roomBox.getSelectedItem();

                if (patientEntry == null || roomNumber == null) {
                    JOptionPane.showMessageDialog(this, "Please select both patient and room.");
                    return;
                }

                int patientId = Integer.parseInt(patientEntry.split(" - ")[0]);

                Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement("UPDATE patients SET room_number = ? WHERE id = ?");
                stmt.setString(1, roomNumber);
                stmt.setInt(2, patientId);
                stmt.executeUpdate();
                conn.close();

                JOptionPane.showMessageDialog(this, "Room assigned successfully.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
class BookAppointmentFrame extends JFrame {
    public BookAppointmentFrame() {
        setTitle("Book Appointment");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 250, 240));

        JLabel patientLabel = new JLabel("Patient:");
        patientLabel.setBounds(30, 30, 100, 25);
        add(patientLabel);

        JComboBox<String> patientBox = new JComboBox<>();
        patientBox.setBounds(150, 30, 250, 25);
        add(patientBox);

        JLabel doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(30, 70, 100, 25);
        add(doctorLabel);

        JComboBox<String> doctorBox = new JComboBox<>();
        doctorBox.setBounds(150, 70, 250, 25);
        add(doctorBox);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(30, 110, 150, 25);
        add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(190, 110, 150, 25);
        add(dateField);

        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setBounds(30, 150, 150, 25);
        add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(190, 150, 150, 25);
        add(timeField);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(30, 190, 100, 25);
        add(reasonLabel);

        JTextField reasonField = new JTextField();
        reasonField.setBounds(150, 190, 250, 25);
        add(reasonField);

        JButton bookBtn = new JButton("Book");
        bookBtn.setBounds(150, 240, 120, 30);
        bookBtn.setBackground(new Color(34, 139, 34));
        bookBtn.setForeground(Color.WHITE);
        add(bookBtn);

        // Load patient & doctor data
        try {
            Connection conn = DBConnection.connect();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id, name FROM patients");
            while (rs.next()) {
                patientBox.addItem(rs.getInt("id") + " - " + rs.getString("name"));
            }

            rs = stmt.executeQuery("SELECT id, name FROM doctors");
            while (rs.next()) {
                doctorBox.addItem(rs.getInt("id") + " - " + rs.getString("name"));
            }

            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }

        // Book appointment
        bookBtn.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(patientBox.getSelectedItem().toString().split(" - ")[0]);
                int doctorId = Integer.parseInt(doctorBox.getSelectedItem().toString().split(" - ")[0]);
                String date = dateField.getText();
                String time = timeField.getText();
                String reason = reasonField.getText();

                Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason) VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, patientId);
                stmt.setInt(2, doctorId);
                stmt.setString(3, date);
                stmt.setString(4, time);
                stmt.setString(5, reason);
                stmt.executeUpdate();

                conn.close();
                JOptionPane.showMessageDialog(this, "Appointment booked successfully.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
class HelpFrame extends JFrame {
    public HelpFrame() {
        setTitle("Help");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea helpText = new JTextArea();
        helpText.setText("Welcome to the Hospital Management System Help Center.\n\n"
                + "1. To add a patient, click 'Add Patient'.\n"
                + "2. To assign rooms, use 'Assign Room'.\n"
                + "3. Doctors and Nurses can be added/viewed similarly.\n"
                + "4. For any issues, use the 'Complain' button.\n\n"
                + "For further assistance, contact support@hospital.com");
        helpText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        helpText.setEditable(false);
        helpText.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(helpText), BorderLayout.CENTER);

        setVisible(true);
    }
}
class ComplainFrame extends JFrame {
    public ComplainFrame() {
        setTitle("Submit Complaint");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 248, 220));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 300, 25);
        add(nameField);

        JLabel complaintLabel = new JLabel("Complaint:");
        complaintLabel.setBounds(30, 70, 100, 25);
        add(complaintLabel);

        JTextArea complaintArea = new JTextArea();
        complaintArea.setLineWrap(true);
        complaintArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(complaintArea);
        scrollPane.setBounds(130, 70, 300, 150);
        add(scrollPane);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(180, 240, 120, 30);
        submitBtn.setBackground(new Color(255, 69, 0));
        submitBtn.setForeground(Color.WHITE);
        add(submitBtn);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String complaint = complaintArea.getText();

            if (name.isEmpty() || complaint.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try {
                Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO complaints (name, complaint_text) VALUES (?, ?)");
                stmt.setString(1, name);
                stmt.setString(2, complaint);
                stmt.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(this, "Complaint submitted successfully.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
class ViewComplaintsFrame extends JFrame {

    public ViewComplaintsFrame() {
        setTitle("All Complaints");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Create a panel for the table
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBounds(20, 50, 740, 300);
        add(tablePanel);

        // Create a table model and table
        String[] columnNames = {"ID", "Name", "Email", "Complaint", "Submitted At"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(20, 360, 740, 50);
        add(buttonPanel);

        // Create a "View" button
        JButton viewBtn = new JButton("View");
        viewBtn.setBackground(new Color(100, 149, 237));
        viewBtn.setForeground(Color.WHITE);
        buttonPanel.add(viewBtn);

        // Create a "Delete" button
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(220, 20, 60));
        deleteBtn.setForeground(Color.WHITE);
        buttonPanel.add(deleteBtn);

        // Create a "Refresh" button
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(new Color(30, 144, 255));
        refreshBtn.setForeground(Color.WHITE);
        buttonPanel.add(refreshBtn);

        // Create a panel for the complaint details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setBounds(20, 420, 740, 100);
        add(detailsPanel);

        // Create a text area for the complaint details
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Populate the table with data from the database
        try (Connection con = DBConnection.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM complaints")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String complaint = rs.getString("complaint_text");
                String date = rs.getString("submitted_at");

                model.addRow(new Object[]{id, name, email, complaint, date});
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading complaints");
        }

        // Add action listeners to the buttons
        viewBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);
                String name = (String) table.getValueAt(selectedRow, 1);
                String email = (String) table.getValueAt(selectedRow, 2);
                String complaint = (String) table.getValueAt(selectedRow, 3);
                String date = (String) table.getValueAt(selectedRow, 4);

                detailsArea.setText("Complaint Details:\n" +
                        "ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Complaint: " + complaint + "\n" +
                        "Submitted At: " + date);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a complaint to view.");
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) table.getValueAt(selectedRow, 0);

                try (Connection con = DBConnection.connect();
                     Statement stmt = con.createStatement()) {

                    stmt.executeUpdate("DELETE FROM complaints WHERE id = " + id);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Complaint deleted successfully.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting complaint: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a complaint to delete.");
            }
        });

        refreshBtn.addActionListener(e -> {
            try (Connection con = DBConnection.connect();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM complaints")) {

                model.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String complaint = rs.getString("complaint_text");
                    String date = rs.getString("submitted_at");

                    model.addRow(new Object[]{id, name, email, complaint, date});
                }

                JOptionPane.showMessageDialog(this, "Complaints refreshed successfully.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error refreshing complaints: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}




