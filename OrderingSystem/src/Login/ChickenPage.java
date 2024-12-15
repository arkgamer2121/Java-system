package Login;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class ChickenPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public ChickenPage() {
        setTitle("Pizza Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600); // Set size
        setLocationRelativeTo(null); // Center on the screen

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240)); // Light gray background
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(contentPane, popupMenu);

        // Top Bar Panel
        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 936, 60);
        topBar.setBackground(new Color(50, 150, 255)); // Blue color
        contentPane.add(topBar);
        topBar.setLayout(null);

        // Search Bar
        JTextField searchField = new JTextField();
        searchField.setBounds(20, 15, 200, 30);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        topBar.add(searchField);

        JLabel searchIcon = new JLabel(scaleImageIcon("C:\\Users\\rusto\\Downloads\\search-icon.png", 30, 30));
        searchIcon.setBounds(225, 15, 30, 30);
        topBar.add(searchIcon);

        // Profile Icon
        JLabel profileIcon = new JLabel(scaleImageIcon("C:\\Users\\rusto\\Downloads\\profile-icon.png", 30, 30));
        profileIcon.setBounds(621, 15, 30, 30);
        topBar.add(profileIcon);

        JButton btnChickenSides = new JButton("PIZZA");
        customizeButton(btnChickenSides);
        btnChickenSides.setBounds(406, 16, 141, 30);
        topBar.add(btnChickenSides);

        // Home Button
        JButton button = new JButton("Home");
        customizeButton(button);
        button.setBounds(248, 15, 120, 30);
        topBar.add(button);

        // Add action listener to the Home button
        button.addActionListener((ActionEvent e) -> {
            HomePage homePage = new HomePage(); // Create an instance of HomePage
            homePage.setVisible(true);
            dispose(); // Close the PizzaPage JFrame
        });

        JButton btnCart = new JButton("CART");
        customizeButton(btnCart);
        btnCart.setBounds(741, 16, 120, 30);
        topBar.add(btnCart);
        
        JLabel lblNewLabel_1 = new JLabel("search");
        lblNewLabel_1.setForeground(new Color(192, 192, 192));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(39, 24, 156, 14);
        topBar.add(lblNewLabel_1);
        
        JButton btnChickenSwing = new JButton("CHICKEN & SIDE");
        btnChickenSwing.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnChickenSwing.setBounds(557, 14, 156, 30);
        topBar.add(btnChickenSwing);
        btnChickenSwing.setOpaque(true);
        btnChickenSwing.setForeground(Color.WHITE);
        btnChickenSwing.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnChickenSwing.setFocusPainted(false);
        btnChickenSwing.setBorderPainted(false);
        btnChickenSwing.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnChickenSwing.setBackground(new Color(50, 150, 255));

        JPanel panel = new JPanel();
        panel.setBounds(671, 61, 265, 502);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255)); // White background

        JLabel lblNewLabel = new JLabel("YOUR CART");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(80, 21, 116, 28);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 60, 245, 431);
        panel.add(panel_1);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(662, 60, 9, 503);
        contentPane.add(scrollBar);

        JPopupMenu popupMenu_1 = new JPopupMenu();
        popupMenu_1.setBounds(68, 218, 200, 50);
        contentPane.add(popupMenu_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(32, 117, 195, 282);
        contentPane.add(panel_2);
                panel_2.setLayout(null);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBounds(251, 117, 195, 282);
        contentPane.add(panel_2_1);
                panel_2_1.setLayout(null);
        
        JPanel panel_2_2 = new JPanel();
        panel_2_2.setBounds(467, 117, 184, 282);
        contentPane.add(panel_2_2);
                panel_2_2.setLayout(null);
    }

    private void customizeButton(JButton button) {
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

    class RoundedBorder extends AbstractBorder {
        private static final long serialVersionUID = 1L;
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    // Method to scale images
    private static ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);

        // Calculate the aspect ratio
        double aspectRatio = (double) imgWidth / imgHeight;
        int newWidth = width;
        int newHeight = height;

        // Adjust width and height to preserve the aspect ratio
        if (imgWidth > imgHeight) {
            newHeight = (int) (width / aspectRatio);
        } else {
            newWidth = (int) (height * aspectRatio);
        }

        // Resize the image proportionally
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
