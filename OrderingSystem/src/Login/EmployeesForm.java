package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeesForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtFirstName, txtLastName, txtAge, txtContact, txtEmail, txtAddress, txtCity;
    private JLabel lblProfilePhoto;
    private JTable table;
    private DefaultTableModel tableModel;
    private File selectedFile;

  
    private final String DB_URL = "jdbc:mysql://localhost:3306/cruddb";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    public EmployeesForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 639);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setBounds(186, 0, 700, 612);
        mainContent.setLayout(null);
        contentPane.add(mainContent);

        JLabel lblFormTitle = new JLabel("Employees Form");
        lblFormTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblFormTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormTitle.setBounds(20, 11, 670, 36);
        mainContent.add(lblFormTitle);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(20, 60, 100, 20);
        mainContent.add(lblFirstName);
        txtFirstName = new JTextField();
        txtFirstName.setBounds(120, 60, 200, 25);
        mainContent.add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(370, 60, 100, 20);
        mainContent.add(lblLastName);
        txtLastName = new JTextField();
        txtLastName.setBounds(452, 58, 200, 25);
        mainContent.add(txtLastName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(370, 117, 100, 20);
        mainContent.add(lblAge);
        txtAge = new JTextField();
        txtAge.setBounds(452, 115, 200, 25);
        mainContent.add(txtAge);

        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(20, 117, 100, 20);
        mainContent.add(lblContact);
        txtContact = new JTextField();
        txtContact.setBounds(120, 115, 200, 25);
        mainContent.add(txtContact);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 173, 100, 20);
        mainContent.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(120, 171, 200, 25);
        mainContent.add(txtEmail);

        JLabel lblProfile = new JLabel("Profile Photo:");
        lblProfile.setBounds(20, 235, 100, 20);
        mainContent.add(lblProfile);
        lblProfilePhoto = new JLabel("No file selected");
        lblProfilePhoto.setBounds(120, 233, 200, 25);
        lblProfilePhoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblProfilePhoto.setOpaque(true);
        lblProfilePhoto.setBackground(Color.WHITE);
        mainContent.add(lblProfilePhoto);

        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(120, 269, 90, 25);
        btnUpload.addActionListener(e -> chooseFile());
        mainContent.add(btnUpload);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(370, 173, 100, 20);
        mainContent.add(lblAddress);
        txtAddress = new JTextField();
        txtAddress.setBounds(452, 171, 200, 25);
        mainContent.add(txtAddress);

        JLabel lblCity = new JLabel("City:");
        lblCity.setBounds(370, 235, 100, 20);
        mainContent.add(lblCity);
        txtCity = new JTextField();
        txtCity.setBounds(452, 233, 200, 25);
        mainContent.add(txtCity);

        JButton btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setBackground(new Color(0, 128, 0));
        btnAdd.setBounds(563, 269, 90, 25);
        btnAdd.addActionListener(e -> addEmployee());
        mainContent.add(btnAdd);

     
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "First Name", "Last Name", "Age", "Contact", "Email", "Address", "City", "Profile"},
                0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 305, 690, 280);
        mainContent.add(scrollPane);

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 190, 599);
        contentPane.add(sidebar);
        sidebar.setBackground(new Color(0, 0, 0));
        sidebar.setLayout(null);

        JLabel lblDashboard = new JLabel("DASHBOARD");
        lblDashboard.setForeground(new Color(255, 255, 255));
        lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
        lblDashboard.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblDashboard.setBounds(25, 73, 130, 50);
        sidebar.add(lblDashboard);
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openDashboardFrame();
            }
        });

        JLabel lblEmployees = new JLabel("EMPLOYEES");
        lblEmployees.setForeground(new Color(255, 255, 255));
        lblEmployees.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmployees.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEmployees.setBackground(Color.BLACK);
        lblEmployees.setBounds(25, 394, 130, 50);
        sidebar.add(lblEmployees);
        
        JLabel lblMenuItem = new JLabel("MENU ITEM");
        lblMenuItem.setForeground(new Color(255, 255, 255));
        lblMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuItem.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblMenuItem.setBounds(25, 176, 130, 50);
        sidebar.add(lblMenuItem);
        
        JLabel lblOrders = new JLabel("ORDERS");
        lblOrders.setForeground(new Color(255, 255, 255));
        lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrders.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOrders.setBounds(25, 288, 130, 50);
        sidebar.add(lblOrders);
        
        JLabel lblLogout = new JLabel("LOGOUT");
        lblLogout.setForeground(new Color(255, 255, 255));
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblLogout.setBounds(25, 498, 130, 50);
        sidebar.add(lblLogout);

      
        loadEmployees();
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            lblProfilePhoto.setText(selectedFile.getName());
        }
    }

    private void addEmployee() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String photo = (selectedFile != null) ? selectedFile.getAbsolutePath() : null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO employees (first_name, last_name, age, contact_number, email, profile_photo, address, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setString(4, contact);
            stmt.setString(5, email);
            stmt.setString(6, photo);
            stmt.setString(7, address);
            stmt.setString(8, city);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            loadEmployees(); // Refresh the table
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    private void loadEmployees() {
        tableModel.setRowCount(0); 
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("profile_photo")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void openDashboardFrame() {
        dispose();
        EventQueue.invokeLater(() -> {
            Dashboard frame = new Dashboard();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            EmployeesForm frame = new EmployeesForm();
            frame.setVisible(true);
        });
    }
}
