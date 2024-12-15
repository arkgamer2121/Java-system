package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OrdersForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;

    // Database 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cruddb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OrdersForm frame = new OrdersForm(); 
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public OrdersForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 845, 600); 
        setLocationRelativeTo(null); 

     
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); 
        setContentPane(contentPane);

      
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setBounds(188, 0, 643, 574); 
        mainContent.setLayout(null);
        contentPane.add(mainContent);

        JLabel lblWelcome = new JLabel("Good Day Administrator!");
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(0, 11, 294, 50);
        mainContent.add(lblWelcome);

        JLabel lblOrdersForm = new JLabel("Orders Form");
        lblOrdersForm.setForeground(new Color(0, 0, 160));
        lblOrdersForm.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrdersForm.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblOrdersForm.setBounds(10, 57, 130, 50);
        mainContent.add(lblOrdersForm);

        String[] columns = { "Username", "Contact", "Address", "Other Info", "Payment Receipt", "Item Name", "Prize", "Quantity"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 613, 412);
        mainContent.add(scrollPane);

        // Fetch data
        loadCustomerData();

        
        JPanel sidebar = new JPanel();
        sidebar.setBounds(10, 0, 180, 563);
        contentPane.add(sidebar);
        sidebar.setBackground(new Color(0, 0, 0));
        sidebar.setLayout(null);

        JLabel lblDashboard = new JLabel("DASHBOARD");
        lblDashboard.setForeground(new Color(255, 255, 255));
        lblDashboard.setBackground(new Color(0, 0, 0));
        lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
        lblDashboard.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblDashboard.setBounds(25, 105, 130, 50);
        sidebar.add(lblDashboard);
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openDashboardFrame(); 
            }
        });

        JLabel lblOrders = new JLabel("ORDERS");
        lblOrders.setForeground(new Color(255, 255, 255));
        lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrders.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblOrders.setBackground(Color.BLACK);
        lblOrders.setBounds(25, 272, 130, 50);
        sidebar.add(lblOrders);
        lblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openOrdersFrame();             }
        });

        JLabel lblemploy = new JLabel("EMPLOYEES");
        lblemploy.setForeground(new Color(255, 255, 255));
        lblemploy.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblemploy.setBounds(40, 366, 130, 31);
        sidebar.add(lblemploy);
        lblemploy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openEmployeesFrame();
            }
        });

        JLabel lblLogout = new JLabel("LOGOUT");
        lblLogout.setForeground(new Color(255, 255, 255));
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblLogout.setBackground(Color.BLACK);
        lblLogout.setBounds(25, 441, 130, 50);
        sidebar.add(lblLogout);

        JLabel lblMenuItem = new JLabel("MENU ITEM");
        lblMenuItem.setForeground(new Color(255, 255, 255));
        lblMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuItem.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblMenuItem.setBackground(Color.BLACK);
        lblMenuItem.setBounds(25, 195, 130, 50);
        sidebar.add(lblMenuItem);
        lblMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openMenuItemFrame(); 
            }
        });

        contentPane.revalidate();
        contentPane.repaint();
    }

   
    private void openEmployeesFrame() {
        EmployeesForm employeesFrame = new EmployeesForm();
        employeesFrame.setVisible(true);
        this.dispose();  
    }

   
    protected void openMenuItemFrame() {
        
    }

    private void openOrdersFrame() {
        OrdersForm ordersFrame = new OrdersForm(); 
        ordersFrame.setVisible(true);             
        this.dispose();                          
    }


  
    @SuppressWarnings("unused")
    private void openLogoutFrame() {
        LogoutFrame logoutFrame = new LogoutFrame();
        logoutFrame.setVisible(true);
        this.dispose(); 
    }

 
    private void openDashboardFrame() {
        Dashboard dashboardFrame = new Dashboard(); 
        dashboardFrame.setVisible(true);
        this.dispose(); 
    }

    private void loadCustomerData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
   
            String sql = "SELECT username, contact, address, other_info, payment_receipt, item_name, item_price, item_quantity FROM customer_info";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            while (rs.next()) {
                String username = rs.getString("username");
                String contact = rs.getString("contact");
                String address = rs.getString("address");
                String otherInfo = rs.getString("other_info");
                String paymentReceipt = rs.getString("payment_receipt");
                String itemName = rs.getString("item_name");
                String itemPrice = rs.getString("item_price");
                String itemQuantity = rs.getString("item_quantity");

                model.addRow(new Object[]{username, contact, address, otherInfo, paymentReceipt, itemName, itemPrice, itemQuantity});
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "Error loading customer data: " + ex.getMessage());
        }
    }
}