package Login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.sql.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OrderCustomerInfo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername, txtContact, txtAddress, txtOtherInfo;
    private JPanel cartItemsPanel;
    private File paymentReceiptFile;
    private List<CartItem> cartItems;

    public OrderCustomerInfo(List<CartItem> cartItems) {
        this.cartItems = cartItems;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 484, 600); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        contentPane.setBackground(Color.WHITE); 
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20)); 

      
        JLabel titleLabel = new JLabel("Order Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        contentPane.add(titleLabel, BorderLayout.NORTH);

     
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        
      
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(4, 340, 215, 38);
        btnSubmit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSubmit.setBackground(new Color(34, 139, 34)); 
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setHorizontalAlignment(SwingConstants.CENTER);  
        btnSubmit.addActionListener(e -> saveCustomerInfo());
        formPanel.add(btnSubmit);

        txtUsername = new JTextField();
        txtUsername.setBounds(4, 11, 215, 38);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtUsername);
        JLabel lblContact = new JLabel("CONTACT");
        lblContact.setBounds(244, 65, 225, 58);
        lblContact.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblContact);

        txtContact = new JTextField();
        txtContact.setBounds(4, 75, 215, 38);
        txtContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtContact);

       
        JLabel lblAddress = new JLabel("DELIVERY ADDRESS");
        lblAddress.setBounds(244, 133, 215, 58);
        lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(4, 143, 215, 38);
        txtAddress.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtAddress);

     
        JLabel lblOtherInfo = new JLabel("OTHER INFORMATION");
        lblOtherInfo.setBounds(244, 201, 215, 58);
        lblOtherInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblOtherInfo);

        txtOtherInfo = new JTextField();
        txtOtherInfo.setBounds(4, 211, 215, 38);
        txtOtherInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtOtherInfo);

      
        JLabel lblPaymentReceipt = new JLabel("PAYMENT RECIEPT");
        lblPaymentReceipt.setBounds(244, 270, 215, 58);
        lblPaymentReceipt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(lblPaymentReceipt);

        JButton btnUploadReceipt = new JButton("UPLOAD RECIEPT");
        btnUploadReceipt.setBounds(4, 277, 215, 43);
        btnUploadReceipt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnUploadReceipt.setBackground(new Color(211, 211, 211)); 
        btnUploadReceipt.setFocusPainted(false);
        btnUploadReceipt.setHorizontalAlignment(SwingConstants.CENTER); 
        btnUploadReceipt.addActionListener(e -> uploadReceipt());
        formPanel.add(btnUploadReceipt);

        
        JButton btnPayment = new JButton("Payment");
        btnPayment.setBounds(229, 340, 193, 38);
        btnPayment.setBackground(new Color(0, 0, 255));
        btnPayment.setForeground(Color.WHITE);
        btnPayment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnPayment.setFocusPainted(false);
        btnPayment.setHorizontalAlignment(SwingConstants.CENTER);
        btnPayment.addActionListener(e -> showPaymentModal());
        formPanel.add(btnPayment);

        contentPane.add(formPanel, BorderLayout.CENTER);
        
        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsername.setBounds(244, 1, 215, 58);
        formPanel.add(lblUsername);
        
     
        cartItemsPanel = new JPanel();
        cartItemsPanel.setBackground(Color.WHITE);
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

     
        for (CartItem item : cartItems) {
            JPanel cartItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cartItemPanel.setBackground(Color.WHITE);
            cartItemPanel.setPreferredSize(new Dimension(460, 40)); 

            JLabel lblItemName = new JLabel(item.getItemName());
            lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JLabel lblPrice = new JLabel("Price: " + item.getPrice());
            lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JLabel lblQuantity = new JLabel("Qty: " + item.getQuantity());
            lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            cartItemPanel.add(lblItemName);
            cartItemPanel.add(lblPrice);
            cartItemPanel.add(lblQuantity);

            cartItemsPanel.add(cartItemPanel);
        }

        contentPane.add(cartItemsPanel, BorderLayout.SOUTH);

       
        setLocationRelativeTo(null);
    }

    
    private void showPaymentModal() {
        String[] options = {"COD", "GCASH"};
        int choice = JOptionPane.showOptionDialog(this, 
                "Select Payment Method", 
                "Payment Options", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);

        if (choice == 1) {  
            showGCASHImage();
        }
    }
    private void showGCASHImage() {
      
        String imagePath = "C:\\Users\\rusto\\Downloads\\GCASH.jpg";  
        
      
        ImageIcon gcashIcon = new ImageIcon(imagePath);
        
        
        Image scaledImage = gcashIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledGcashIcon = new ImageIcon(scaledImage);
        
       
        JLabel imageLabel = new JLabel(scaledGcashIcon);

  
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);

      
        JOptionPane.showMessageDialog(this, imagePanel, "GCASH Payment", JOptionPane.PLAIN_MESSAGE);
    }


    
    private void uploadReceipt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Payment Receipt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "pdf");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            paymentReceiptFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Payment receipt uploaded successfully.");
        }
    }

    private void saveCustomerInfo() {
        String username = txtUsername.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String otherInfo = txtOtherInfo.getText();

        String url = "jdbc:mysql://localhost:3306/cruddb";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO customer_info (username, contact, address, other_info, payment_receipt, item_name, item_price, item_quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (CartItem item : cartItems) {
                    statement.setString(1, username);
                    statement.setString(2, contact);
                    statement.setString(3, address);
                    statement.setString(4, otherInfo);

                    if (paymentReceiptFile != null) {
                        String filePath = paymentReceiptFile.getAbsolutePath();
                        statement.setString(5, filePath);
                    } else {
                        statement.setNull(5, Types.VARCHAR);
                    }

                    statement.setString(6, item.getItemName());
                    statement.setDouble(7, item.getPrice());
                    statement.setInt(8, item.getQuantity());

                    statement.addBatch();
                }

                statement.executeBatch();

                JOptionPane.showMessageDialog(this, "Customer information and cart items saved successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving customer information and cart items.");
        }
    }
}
