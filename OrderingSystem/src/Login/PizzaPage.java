package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.Icon; 

public class PizzaPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel itemsPanel; // Panel to display items
    private JTextField txtItemName, txtDescription, txtPrice, searchField; // Input fields
    private JPanel cartPanel; // Panel for Cart
    private GridBagConstraints gbc;
    private JLabel lblTotal; // Total label
    private List<CartItem> cartItems = new ArrayList<>(); // Store the cart items


    public PizzaPage() throws ClassNotFoundException {
        setTitle("Pizza Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1036, 600);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE); 
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 1019, 60);
        topBar.setBackground(new Color(192, 192, 192));
        contentPane.add(topBar);
        topBar.setLayout(null);

        JLabel searchIcon = new JLabel(scaleImageIcon("C:\\Users\\rusto\\Downloads\\search-icon.png", 20, 20));
        searchIcon.setBounds(225, 15, 30, 30);
        topBar.add(searchIcon);

        
        JLabel cartIcon = new JLabel(scaleImageIcon("C:\\Users\\rusto\\Downloads\\cart-icon.png", 30, 30));
        cartIcon.setBounds(610, 15, 30, 30);
        topBar.add(cartIcon);

      
        JLabel profileIcon = new JLabel(scaleImageIcon("C:\\Users\\rusto\\Downloads\\profile-icon.png", 30, 30));
        profileIcon.setBounds(650, 15, 30, 30);
        topBar.add(profileIcon);

     
        JButton btnPizza = new JButton("PIZZA");
        btnPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        customizeButton(btnPizza);
        btnPizza.setBounds(510, 15, 120, 30);
        topBar.add(btnPizza);

       
        JButton btnCart = new JButton("CART");
        btnCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCart.setForeground(new Color(255, 255, 255));
        btnCart.setBackground(new Color(255, 255, 255));
        customizeButton(btnCart);
        btnCart.setBounds(690, 15, 120, 30);
        topBar.add(btnCart);

       
        searchField = new JTextField();
        searchField.setBounds(10, 15, 217, 32);
        topBar.add(searchField);
        
        JButton btnHome = new JButton("HOME");
        btnHome.setOpaque(true);
        btnHome.setForeground(new Color(255, 0, 0));
        btnHome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHome.setFocusPainted(false);
        btnHome.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnHome.setBackground(new Color(255, 255, 255));
        btnHome.setBounds(323, 15, 120, 30);
        topBar.add(btnHome);
        

        JButton btnProfile = new JButton("PROFILE");
        btnProfile.setForeground(new Color(255, 0, 0));
        btnProfile.setBackground(new Color(255, 255, 255));
        btnProfile.setBounds(879, 15, 120, 30);
        topBar.add(btnProfile);
        
        JLabel searchIcon_1 = new JLabel((Icon) null);
        searchIcon_1.setBounds(237, 15, 30, 30);
        topBar.add(searchIcon_1);

     
        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                showProfileModal();
            }
        });

     
        cartIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                toggleCartVisibility(); 
            }
        });

        
        itemsPanel = new JPanel();
        itemsPanel.setBackground(new Color(255, 255, 255));
        itemsPanel.setLayout(new GridBagLayout());
        setGbc(new GridBagConstraints());
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBounds(10, 70, 650, 500); 
        contentPane.add(scrollPane);

       
        cartPanel = new JPanel();
        cartPanel.setBackground(Color.LIGHT_GRAY);
        cartPanel.setBounds(662, 120, 357, 392); 
        cartPanel.setVisible(false); 
        contentPane.add(cartPanel);

        JLabel lblNewLabel_1 = new JLabel("YOUR CART");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        lblNewLabel_1.setForeground(new Color(51, 51, 51)); 
        lblNewLabel_1.setBounds(662, 71, 357, 38); 
        lblNewLabel_1.setOpaque(true); 
        lblNewLabel_1.setBackground(new Color(255, 255, 255)); 
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER); 
        lblNewLabel_1.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); 
        contentPane.add(lblNewLabel_1);

        
        cartPanel.setBackground(new Color(245, 245, 245)); 
        
        lblTotal = new JLabel("TOTAL: 0.00");
        lblTotal.setOpaque(true);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setForeground(new Color(51, 51, 51));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTotal.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        lblTotal.setBackground(Color.WHITE);
        lblTotal.setBounds(662, 523, 251, 38);
        contentPane.add(lblTotal);
        
        JButton btnbuy = new JButton("ORDER");
        btnbuy.setForeground(new Color(255, 255, 255));
        btnbuy.setBackground(new Color(0, 64, 0));
        btnbuy.setFont(new Font("Serif", Font.BOLD, 14));
        btnbuy.setBounds(918, 523, 102, 40);
        btnbuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                OrderCustomerInfo orderInfoFrame = new OrderCustomerInfo(cartItems);
                orderInfoFrame.setVisible(true);
            }
        });
        contentPane.add(btnbuy);


      
        loadItems();
    }

    private void toggleCartVisibility() {
        boolean isVisible = cartPanel.isVisible();
        cartPanel.setVisible(!isVisible); 
    }

    private void showProfileModal() {
        
        JDialog profileModal = new JDialog();
        profileModal.setTitle("Profile Options");
        profileModal.setSize(200, 150);
        profileModal.setLocationRelativeTo(null); 

       
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        panel.setBackground(Color.WHITE);
        profileModal.getContentPane().add(panel);


        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
        JButton btnAccount = new JButton("ACCOUNT");
        btnAccount.setBackground(Color.WHITE); 
        btnAccount.setForeground(new Color(50, 150, 255)); 
        btnAccount.setFocusPainted(false); 
        btnAccount.setFont(new Font("Arial", Font.BOLD, 14)); 
        btnAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAccount.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 255), 2)); 
        btnAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("Account clicked!");
                profileModal.dispose(); 
            }
        });
        panel.add(btnAccount);

      
        panel.add(Box.createVerticalStrut(10));

     
        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setBackground(Color.WHITE); 
        btnLogout.setForeground(new Color(255, 69, 0)); 
        btnLogout.setFocusPainted(false);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14)); 
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnLogout.setBorder(BorderFactory.createLineBorder(new Color(255, 69, 0), 2)); 
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                System.out.println("Logout clicked!");
                profileModal.dispose(); 
            }
        });
        panel.add(btnLogout);

        
        profileModal.setVisible(true);
    }

    private void loadItems() throws ClassNotFoundException {
        itemsPanel.removeAll(); 
        itemsPanel.setLayout(new GridBagLayout()); 

        try (Connection con = Dbcon.getConnection()) {
            String query = "SELECT * FROM items";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);

            int rowCount = 0;
            while (rs.next()) {
                String itemName = rs.getString("name");
                String description = rs.getString("description");
                String price = rs.getString("price");
                String imagePath = rs.getString("image"); 

              
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BorderLayout());
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setPreferredSize(new Dimension(300, 300));

            
                JLabel imageLabel = new JLabel();
                imageLabel.setPreferredSize(new Dimension(100, 100));
                imageLabel.setHorizontalAlignment(JLabel.CENTER);

                if (imagePath != null && !imagePath.isEmpty()) {
                    ImageIcon itemImage = scaleImageIcon(imagePath, 100, 100);
                    imageLabel.setIcon(itemImage);
                } else {
                    imageLabel.setText("No Image Available");
                }

                itemPanel.add(imageLabel, BorderLayout.CENTER);

                
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new GridBagLayout()); 
                infoPanel.setBackground(Color.WHITE);

                GridBagConstraints infoGbc = new GridBagConstraints();
                infoGbc.gridx = 0;
                infoGbc.gridy = 0;
                infoGbc.insets = new Insets(5, 5, 5, 5);
                infoGbc.anchor = GridBagConstraints.CENTER;

           
                JLabel lblItemName = new JLabel(itemName);
                lblItemName.setFont(new Font("Arial", Font.BOLD, 16));
                lblItemName.setHorizontalAlignment(JLabel.CENTER);
                infoPanel.add(lblItemName, infoGbc);

             
                infoGbc.gridy++;
                JLabel lblDescription = new JLabel(description);
                lblDescription.setFont(new Font("Arial", Font.PLAIN, 12));
                lblDescription.setHorizontalAlignment(JLabel.CENTER);
                infoPanel.add(lblDescription, infoGbc);

              
                infoGbc.gridy++;
                JLabel lblPrice = new JLabel("Price: " + price);
                lblPrice.setFont(new Font("Arial", Font.PLAIN, 12));
                lblPrice.setHorizontalAlignment(JLabel.CENTER);
                infoPanel.add(lblPrice, infoGbc);

             
                infoGbc.gridy++;
                JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
                quantityPanel.setBackground(Color.WHITE);

                JLabel lblQuantity = new JLabel("Quantity:");
                lblQuantity.setFont(new Font("Arial", Font.PLAIN, 12));
                quantityPanel.add(lblQuantity);

                JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                quantityPanel.add(quantitySpinner);

                infoPanel.add(quantityPanel, infoGbc);

                
                infoGbc.gridy++;
                JButton btnOrder = new JButton("Add");
                btnOrder.setFont(new Font("Arial", Font.BOLD, 12));
                btnOrder.setForeground(Color.WHITE);
                btnOrder.setBackground(new Color(0, 128, 0));
                btnOrder.setFocusPainted(false);
                btnOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnOrder.addActionListener(e -> {
                    int quantity = (int) quantitySpinner.getValue();
                    addToCart(itemName, description, price, imagePath, quantity);
                    JOptionPane.showMessageDialog(this, itemName + " (" + quantity + ") added to cart.", "Info", JOptionPane.INFORMATION_MESSAGE);
                });

                infoPanel.add(btnOrder, infoGbc);

              
                itemPanel.add(infoPanel, BorderLayout.SOUTH);

               
                gbc.gridx = rowCount % 3;
                gbc.gridy = rowCount / 3;
                itemsPanel.add(itemPanel, gbc);

                rowCount++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading items", "Error", JOptionPane.ERROR_MESSAGE);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }


    private void customizeButton(JButton button) {
      
        button.setForeground(new Color(255, 255, 255)); 
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 255, 255)); 
        button.setOpaque(true); 

        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 69, 0)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.RED); 
            }
        });

      
        button.setBorder(new LineBorder(new Color(0, 0, 0)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    class RoundedBorder extends AbstractBorder {
        private static final long serialVersionUID = 1L;
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private static ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PizzaPage frame = new PizzaPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JTextField getTxtDescription() {
        return txtDescription;
    }
    
    private double totalPrice = 0.0; 

    private void addToCart(String itemName, String description, String price, String imagePath, int quantity) {
      
        if (!cartPanel.isVisible()) {
            cartPanel.setVisible(true);
        }

       
        JPanel cartItemPanel = new JPanel(new BorderLayout());
        cartItemPanel.setBackground(Color.WHITE);
        cartItemPanel.setPreferredSize(new Dimension(350, 80));
        cartItemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

     
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); 
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblItemName = new JLabel(itemName);
        lblItemName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblItemName.setForeground(Color.DARK_GRAY);

        JLabel lblDescription = new JLabel(description);
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescription.setForeground(Color.GRAY);

        JLabel lblPrice = new JLabel("Price: " + price);
        lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPrice.setForeground(new Color(34, 139, 34));

        JLabel lblQuantity = new JLabel("Qty: " + quantity);
        lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblQuantity.setForeground(Color.GRAY);

        infoPanel.add(lblItemName);
        infoPanel.add(lblDescription);
        infoPanel.add(lblPrice);
        infoPanel.add(lblQuantity);

        cartItemPanel.add(infoPanel, BorderLayout.CENTER);

        
        cartPanel.add(cartItemPanel);

        
        CartItem cartItem = new CartItem(itemName, price, quantity);
        cartItems.add(cartItem);

        double itemPrice = Double.parseDouble(price) * quantity;
        totalPrice += itemPrice;
        updateTotalLabel();

       
        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    private void updateTotalLabel() {
        lblTotal.setText("TOTAL: " + String.format("%.2f", totalPrice)); 
    }


    public void setTxtDescription(JTextField txtDescription) {
        this.txtDescription = txtDescription;
    }

    public JTextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(JTextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public JTextField getTxtItemName() {
        return txtItemName;
    }

    public void setTxtItemName(JTextField txtItemName) {
        this.txtItemName = txtItemName;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void setGbc(GridBagConstraints gbc) {
        this.gbc = gbc;
    }
}
