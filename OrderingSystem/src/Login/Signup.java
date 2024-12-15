package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsername, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    
    public Signup() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 240, 240));

        
        JLabel lblSignup = new JLabel("Sign Up");
        lblSignup.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblSignup.setBounds(207, 29, 139, 50);
        lblSignup.setForeground(new Color(0, 0, 0));
        getContentPane().add(lblSignup);

       
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setBounds(50, 90, 100, 25);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setBounds(180, 90, 200, 30);
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        getContentPane().add(txtUsername);

       
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(50, 140, 100, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEmail.setBounds(180, 140, 200, 30);
        txtEmail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        getContentPane().add(txtEmail);

   
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(50, 190, 100, 25);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBounds(180, 190, 200, 30);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        getContentPane().add(txtPassword);

      
        JCheckBox chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chkShowPassword.setBounds(180, 227, 200, 20);
        chkShowPassword.setBackground(new Color(240, 240, 240));
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0); // Show password
            } else {
                txtPassword.setEchoChar('\u2022'); // Hide password
            }
        });
        getContentPane().add(chkShowPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConfirmPassword.setBounds(50, 254, 130, 25);
        getContentPane().add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtConfirmPassword.setBounds(180, 252, 200, 30);
        txtConfirmPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        getContentPane().add(txtConfirmPassword);

        JCheckBox chkShowConfirmPassword = new JCheckBox("Show Password");
        chkShowConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chkShowConfirmPassword.setBounds(180, 289, 200, 20);
        chkShowConfirmPassword.setBackground(new Color(240, 240, 240));
        chkShowConfirmPassword.addActionListener(e -> {
            if (chkShowConfirmPassword.isSelected()) {
                txtConfirmPassword.setEchoChar((char) 0); // Show password
            } else {
                txtConfirmPassword.setEchoChar('\u2022'); // Hide password
            }
        });
        getContentPane().add(chkShowConfirmPassword);
       
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnRegister.setBackground(new Color(0, 123, 255));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBounds(180, 316, 100, 35);
        btnRegister.setBorder(BorderFactory.createEmptyBorder());
        getContentPane().add(btnRegister);

        JButton btnBack = new JButton("Login");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBack.setBackground(new Color(255, 0, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(290, 316, 90, 35);
        btnBack.setBorder(BorderFactory.createEmptyBorder());
        getContentPane().add(btnBack);

        JLabel lblNewLabel = new JLabel("Forgot Password?");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setBounds(180, 357, 130, 20);
        getContentPane().add(lblNewLabel);
        
     
        ImageIcon icon = new ImageIcon("C:\\Users\\rusto\\Downloads\\d491031ab5e2704f5a4b9dd216e4939e-removebg-preview.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(72, 49, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

       
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(resizedIcon); 
        lblNewLabel_1.setBounds(50, 301, 100, 76);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Pizza Hut");
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel_2.setBounds(50, 29, 130, 43);
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\rusto\\Downloads\\jjjj-removebg-preview.png"));
        lblNewLabel_3.setBounds(228, 0, 450, 459);
        getContentPane().add(lblNewLabel_3);

        btnRegister.addActionListener(e -> handleRegistration());
        btnBack.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });
    }

    private void handleRegistration() {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        if (!containsSpecialCharacter(password)) {
            JOptionPane.showMessageDialog(this, "Password must contain at least one special character");
            return;
        }

        try (Connection con = Dbcon.getConnection()) {
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
                dispose();
                new Login().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
   
    private boolean containsSpecialCharacter(String password) {
        String specialCharacterPattern = ".*[!@#$%^&*(),.?\":{}|<>].*"; 
        Pattern pattern = Pattern.compile(specialCharacterPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup().setVisible(true));
    }
}
