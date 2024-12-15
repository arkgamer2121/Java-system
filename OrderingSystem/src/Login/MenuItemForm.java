package Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MenuItemForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection connection;
    private JTextField txtItemName;
    private JTextField txtDescription;
    private JTextField txtPrice;
    private JTextField txtImagePath;  

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuItemForm frame = new MenuItemForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuItemForm() {
        
        initializeDatabaseConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 836, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setBounds(188, 0, 634, 574);
        mainContent.setLayout(null);
        contentPane.add(mainContent);

        JLabel lblTitle = new JLabel("MENU ITEMS");
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(-31, 11, 226, 50);
        mainContent.add(lblTitle);

        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setBounds(20, 70, 100, 25);
        mainContent.add(lblItemName);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(20, 110, 100, 25);
        mainContent.add(lblDescription);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 150, 100, 25);
        mainContent.add(lblPrice);

        JLabel lblImage = new JLabel("Image:");
        lblImage.setBounds(20, 190, 100, 25);
        mainContent.add(lblImage);

        JButton btnAdd = new JButton("Add Item");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setBackground(new Color(0, 128, 0));
        btnAdd.setBounds(408, 68, 183, 30);
        btnAdd.addActionListener(this::addItem);
        mainContent.add(btnAdd);

        JButton btnUpdate = new JButton("Update Item");
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setBackground(new Color(0, 0, 255));
        btnUpdate.setBounds(408, 128, 183, 30);
        btnUpdate.addActionListener(this::updateItem);
        mainContent.add(btnUpdate);

        JButton btnDelete = new JButton("Delete Item");
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setBounds(408, 187, 183, 30);
        btnDelete.addActionListener(this::deleteItem);
        mainContent.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 266, 604, 320);
        mainContent.add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Price", "Image"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        txtItemName = new JTextField();
        txtItemName.setBounds(130, 72, 212, 23);
        mainContent.add(txtItemName);
        txtItemName.setColumns(10);

        txtDescription = new JTextField();
        txtDescription.setColumns(10);
        txtDescription.setBounds(130, 111, 212, 23);
        mainContent.add(txtDescription);

        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBounds(130, 151, 212, 23);
        mainContent.add(txtPrice);

        txtImagePath = new JTextField();
        txtImagePath.setColumns(10);
        txtImagePath.setBounds(130, 191, 212, 23);
        mainContent.add(txtImagePath);

        JButton btnChooseImage = new JButton("Choose Image");
        btnChooseImage.setBackground(new Color(0, 0, 255));
        btnChooseImage.setForeground(new Color(255, 255, 255));
        btnChooseImage.setBounds(124, 226, 120, 30);
        btnChooseImage.addActionListener(e -> chooseImage());
        mainContent.add(btnChooseImage);
 
        JPanel sidebar = new JPanel();
        sidebar.setBounds(10, 0, 180, 563);
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

        
        JLabel lblMenuItem = new JLabel("MENU ITEM");
        lblMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenuItem.setForeground(Color.WHITE);
        lblMenuItem.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblMenuItem.setBounds(25, 173, 130, 50);
        sidebar.add(lblMenuItem);
        
        JLabel lblOrders = new JLabel("ORDERS");
        lblOrders.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrders.setForeground(Color.WHITE);
        lblOrders.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOrders.setBounds(25, 277, 130, 50);
        sidebar.add(lblOrders);
        
        JLabel lblEmployeesMenu = new JLabel("EMPLOYEES");
        lblEmployeesMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmployeesMenu.setForeground(Color.WHITE);
        lblEmployeesMenu.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEmployeesMenu.setBounds(25, 371, 130, 50);
        sidebar.add(lblEmployeesMenu);
        
        JLabel lblLogout = new JLabel("LOGOUT");
        lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogout.setForeground(Color.WHITE);
        lblLogout.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblLogout.setBounds(25, 475, 130, 50);
        sidebar.add(lblLogout);

        loadItems();
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtImagePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void initializeDatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddb", "root", "");
            System.out.println("Database connection established successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void addItem(ActionEvent e) {
        String itemName = txtItemName.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String imagePath = txtImagePath.getText();

        try {
            String query = "INSERT INTO items (name, description, price, image) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, itemName);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, imagePath); 
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item added successfully");
            loadItems();
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateItem(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select an item to update");
            return;
        }

        // Retrieve ng ID para sa selected item
        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String itemName = tableModel.getValueAt(selectedRow, 1).toString();
        String description = tableModel.getValueAt(selectedRow, 2).toString();
        double price = (double) tableModel.getValueAt(selectedRow, 3);

       
        UpdateItemDialog updateDialog = new UpdateItemDialog(this, id, itemName, description, price, connection, tableModel);
        updateDialog.setVisible(true);  
    }

    class UpdateItemDialog extends JDialog {
      
		private static final long serialVersionUID = 1L;
		private JTextField txtItemName;
        private JTextField txtDescription;
        private JTextField txtPrice;
        private String itemId;
        private Connection connection;
        private DefaultTableModel tableModel;

        public UpdateItemDialog(JFrame parent, String itemId, String itemName, String description, double price, Connection connection, DefaultTableModel tableModel) {
            super(parent, "Update Item", true);
            this.connection = connection;
            this.itemId = itemId;
            this.tableModel = tableModel;
            
            setBounds(200, 200, 400, 300);
            setLocationRelativeTo(parent);
            getContentPane().setLayout(new FlowLayout());

            JLabel lblItemName = new JLabel("Item Name:");
            txtItemName = new JTextField(20);
            txtItemName.setText(itemName);

            JLabel lblDescription = new JLabel("Description:");
            txtDescription = new JTextField(20);
            txtDescription.setText(description);

            JLabel lblPrice = new JLabel("Price:");
            txtPrice = new JTextField(20);
            txtPrice.setText(String.valueOf(price));

            JButton btnSave = new JButton("Save");
            btnSave.addActionListener(e -> saveItem());

            JButton btnCancel = new JButton("Cancel");
            btnCancel.addActionListener(e -> dispose());

            getContentPane().add(lblItemName);
            getContentPane().add(txtItemName);
            getContentPane().add(lblDescription);
            getContentPane().add(txtDescription);
            getContentPane().add(lblPrice);
            getContentPane().add(txtPrice);
            getContentPane().add(btnSave);
            getContentPane().add(btnCancel);
        }

        private void saveItem() {
            String updatedName = txtItemName.getText().trim();
            String updatedDescription = txtDescription.getText().trim();
            String updatedPriceText = txtPrice.getText().trim();

            if (updatedName.isEmpty() || updatedDescription.isEmpty() || updatedPriceText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double updatedPrice;
            try {
                updatedPrice = Double.parseDouble(updatedPriceText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Price must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String query = "UPDATE items SET name = ?, description = ?, price = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, updatedName);
                ps.setString(2, updatedDescription);
                ps.setDouble(3, updatedPrice);
                ps.setString(4, itemId);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Item updated successfully");
                    // Reload the items in the table
                    loadItems();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update item", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void loadItems() {
            try {
                tableModel.setRowCount(0); 
                String query = "SELECT * FROM items";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("description"), 
                        rs.getDouble("price")
                    });
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading items: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void deleteItem(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select an item to delete");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try {
            String query = "DELETE FROM items WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item deleted successfully");
            loadItems();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadItems() {
        try {
            tableModel.setRowCount(0);
            String query = "SELECT * FROM items";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load items", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtItemName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
    }

    @SuppressWarnings("unused")
	private void openDashboardFrame() {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        this.dispose();
    }
}
