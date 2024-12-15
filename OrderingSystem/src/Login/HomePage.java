package Login;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.LineBorder;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();

            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Failed to resize image: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    public HomePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(911, 627); 
        setLocationRelativeTo(null); 

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(239, 239, 239));
        panel.setBounds(10, 88, 877, 242);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("IT'S BLACK ");
        lblNewLabel.setBounds(343, 42, 237, 49);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        panel.add(lblNewLabel);

        JLabel lblFriday = new JLabel("FRIDAY!");
        lblFriday.setBounds(276, 103, 237, 49);
        lblFriday.setFont(new Font("Tahoma", Font.BOLD, 40));
        panel.add(lblFriday);

        JLabel lblKineme = new JLabel("KINEME");
        lblKineme.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblKineme.setBounds(462, 102, 237, 49);
        panel.add(lblKineme);

        JButton btnNewButton = new JButton("PROMO PREVIEW");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(332, 177, 237, 43);
        panel.add(btnNewButton);

        JLabel lblNewLabel_1 = new JLabel(resizeImage("C:\\Users\\rusto\\Downloads\\HAHA-removebg-preview.png", 212, 187));
        lblNewLabel_1.setBounds(631, 33, 212, 187);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel(resizeImage("C:\\Users\\rusto\\Downloads\\Arcobaleno.png", 195, 174));
        lblNewLabel_1_1.setBounds(38, 33, 195, 174);
        panel.add(lblNewLabel_1_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(239, 239, 239));
        panel_1.setBounds(10, 341, 877, 238);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblGetItBefore = new JLabel("GET IT BEFORE");
        lblGetItBefore.setBounds(283, 41, 324, 49);
        lblGetItBefore.setFont(new Font("Tahoma", Font.BOLD, 40));
        panel_1.add(lblGetItBefore);

        JLabel lblDec = new JLabel("DEC. 22, 2024");
        lblDec.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblDec.setBounds(336, 102, 233, 49);
        panel_1.add(lblDec);

        JLabel lblNewLabel_2 = new JLabel(resizeImage("C:\\Users\\rusto\\Downloads\\7844618b929e698339485bae73da9619-removebg-preview.png", 209, 162));
        lblNewLabel_2.setBounds(53, 42, 209, 162);
        panel_1.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel(resizeImage("C:\\Users\\rusto\\Downloads\\08f5cbad3d3b9fdee8c1d4609233559f-removebg-preview.png", 209, 162));
        lblNewLabel_2_1.setBounds(636, 42, 209, 162);
        panel_1.add(lblNewLabel_2_1);
        
        JPanel topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBackground(Color.LIGHT_GRAY);
        topBar.setBounds(10, 11, 877, 60);
        contentPane.add(topBar);
        
        // Search icon with resized image
        ImageIcon searchIconOriginal = new ImageIcon("C:\\Users\\rusto\\Downloads\\icon-removebg-preview.png");
        Image searchIconResized = searchIconOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel searchIcon = new JLabel(new ImageIcon(searchIconResized));
        searchIcon.setBounds(225, 15, 30, 30);
        topBar.add(searchIcon);
        
        JButton btnPizza = new JButton("PIZZA");
        btnPizza.setForeground(new Color(255, 255, 255));
        btnPizza.setBackground(new Color(255, 0, 0));
        customizeButton(btnPizza);
        btnPizza.setBounds(538, 15, 120, 30);
        topBar.add(btnPizza);

        btnPizza.addActionListener(e -> {
            PizzaPage pizzaPage;
            try {
                pizzaPage = new PizzaPage();
                pizzaPage.setVisible(true);
                dispose();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        
        textField = new JTextField();
        textField.setBounds(10, 15, 217, 32);
        topBar.add(textField);
        
        JButton btnHome = new JButton("HOME");
        btnHome.setOpaque(true);
        btnHome.setForeground(Color.RED);
        btnHome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHome.setFocusPainted(false);
        btnHome.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnHome.setBackground(Color.WHITE);
        btnHome.setBounds(348, 15, 120, 30);
        topBar.add(btnHome);
        
        JButton btnProfile = new JButton("PROFILE");
        btnProfile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnProfile.setForeground(Color.RED);
        btnProfile.setBackground(Color.WHITE);
        btnProfile.setBounds(734, 15, 120, 30);
        topBar.add(btnProfile);
    }

    private void customizeButton(JButton button) {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HomePage frame = new HomePage();
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
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
