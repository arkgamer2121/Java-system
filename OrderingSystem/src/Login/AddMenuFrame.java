package Login;

import java.awt.*;
import javax.swing.*;

public class AddMenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public AddMenuFrame() {
        setTitle("Add Menu");
        setSize(400, 300);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Add New Menu Item");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setBounds(100, 30, 200, 30);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);

        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblItemName.setBounds(50, 90, 80, 25);
        contentPane.add(lblItemName);

        JTextField txtItemName = new JTextField();
        txtItemName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtItemName.setBounds(150, 90, 200, 25);
        contentPane.add(txtItemName);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPrice.setBounds(50, 130, 80, 25);
        contentPane.add(lblPrice);

        JTextField txtPrice = new JTextField();
        txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPrice.setBounds(150, 130, 200, 25);
        contentPane.add(txtPrice);

        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnSave.setBackground(new Color(60, 179, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBounds(150, 180, 100, 30);
        contentPane.add(btnSave);
    }
}
