package Login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class MenuPage extends JFrame {

	private static final long serialVersionUID = 1L;

	public MenuPage() {
        setTitle("Menu Page");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to the Menu Page!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(100, 100, 300, 50);
        getContentPane().add(lblNewLabel);
    }
}
