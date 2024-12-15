package Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

    public class ChartPanel {

	}

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 375);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

       
        JLabel lblLoginTitle = new JLabel("Login");
        lblLoginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoginTitle.setBounds(0, 29, 400, 30);
        contentPane.add(lblLoginTitle);

       
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblEmail.setBounds(50, 70, 100, 20);
        contentPane.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtEmail.setBounds(50, 95, 300, 30);
        txtEmail.setBorder(new LineBorder(new Color(192, 192, 192)));
        contentPane.add(txtEmail);

        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPassword.setBounds(50, 147, 100, 20);
        contentPane.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPassword.setBounds(50, 171, 300, 30);
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        contentPane.add(txtPassword);

        
        JCheckBox chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
        chkShowPassword.setBounds(50, 210, 125, 20);
        chkShowPassword.setBackground(Color.WHITE);
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0); // Show password
            } else {
                txtPassword.setEchoChar('\u2022'); // Hide password
            }
        });
        contentPane.add(chkShowPassword);

     
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 123, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBounds(50, 240, 145, 35);
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(btnLogin);

       
        JButton btnSignup = new JButton("Sign Up");
        btnSignup.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSignup.setBackground(new Color(255, 0, 0));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setFocusPainted(false);
        btnSignup.setBounds(205, 240, 145, 35);
        btnSignup.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(btnSignup);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rusto\\Downloads\\no-removebg-preview.png"));
        lblNewLabel.setBounds(188, 111, 222, 282);
        contentPane.add(lblNewLabel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                try (Connection con = Dbcon.getConnection()) {
                    String query = "SELECT * FROM users WHERE email = ? AND password = ?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, email);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String dbEmail = rs.getString("email");
                        String dbPassword = rs.getString("password");

                        if ("warrensena@gmail.com".equals(dbEmail) && "Admin#21".equals(dbPassword)) {
                            JOptionPane.showMessageDialog(contentPane, "Welcome, Admin!");
                            Dashboard dashboard = new Dashboard();
                            dashboard.setVisible(true); 
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Login Successful");
                            HomePage homePage = new HomePage();
                            homePage.setVisible(true);
                        }

                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Invalid Email or Password");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage());
                }
            }
        });

        btnSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signup signupFrame = new Signup();
                signupFrame.setVisible(true);
                dispose();
            }
        });
    }
}
