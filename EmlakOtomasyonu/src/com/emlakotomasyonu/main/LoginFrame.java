package com.emlakotomasyonu.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.emlakotomasyonu.database.DB;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtLoginUsername;
	private JPasswordField passwordFieldLogin;
	ApplicationFrame applicationFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(LoginFrame.class.getResource("/com/emlakotomasyonu/images/icons8-open-door-32.png")));
		setFont(new Font("Tahoma", Font.BOLD, 14));
		setTitle("Emlak Otomasyonu - Giriş");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kullanıcı Adı:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(110, 260, 90, 20);
		contentPane.add(lblNewLabel);

		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblifre.setBounds(130, 314, 81, 20);
		contentPane.add(lblifre);

		txtLoginUsername = new JTextField();
		txtLoginUsername.setBounds(210, 262, 160, 20);
		contentPane.add(txtLoginUsername);
		txtLoginUsername.setColumns(10);

		JButton btnLogin = new JButton("GİRİŞ");

		btnLogin.setIcon(
				new ImageIcon(LoginFrame.class.getResource("/com/emlakotomasyonu/images/icons8-open-door-32.png")));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(110, 378, 260, 36);
		contentPane.add(btnLogin);

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBounds(210, 316, 160, 20);
		contentPane.add(passwordFieldLogin);

		JButton btnResetPassword = new JButton("Şifremi Unuttum");

		btnResetPassword.setIcon(
				new ImageIcon(LoginFrame.class.getResource("/com/emlakotomasyonu/images/icons8-reset-32.png")));
		btnResetPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnResetPassword.setBounds(110, 424, 260, 36);
		contentPane.add(btnResetPassword);

		JLabel lblLoginFrameImage = new JLabel("");
		lblLoginFrameImage.setToolTipText("Emlak Otomasyon Yazılımı");
		lblLoginFrameImage.setIcon(new ImageIcon(LoginFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Home.256.png")));
		lblLoginFrameImage.setBounds(110, 23, 260, 211);
		contentPane.add(lblLoginFrameImage);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				LoginFrame.class.getResource("/com/emlakotomasyonu/images/Gosquared-Flag-Turkey-flat.32.png")));
		lblNewLabel_1.setBounds(408, 10, 32, 32);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setIcon(new ImageIcon(
				LoginFrame.class.getResource("/com/emlakotomasyonu/images/Gosquared-Flag-United-Kingdom-flat.32.png")));
		lblNewLabel_1_1.setBounds(444, 10, 32, 32);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblOpenedEye = new JLabel("");

		lblOpenedEye
				.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/emlakotomasyonu/images/icons8-eye-16.png")));
		lblOpenedEye.setBounds(380, 316, 20, 20);
		contentPane.add(lblOpenedEye);

		JLabel lblRegister = new JLabel("Kayıt Ol");

		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRegister.setBounds(110, 461, 260, 20);
		contentPane.add(lblRegister);

		JLabel lblOpenedEye_1 = new JLabel("");
		lblOpenedEye_1.setEnabled(false);

		lblOpenedEye_1.setIcon(
				new ImageIcon(LoginFrame.class.getResource("/com/emlakotomasyonu/images/icons8-eyelashes-2d-16.png")));
		lblOpenedEye_1.setBounds(408, 316, 20, 20);
		contentPane.add(lblOpenedEye_1);

		// İşlemler

		// Kayıt Ol Label Clicked
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterFrame registerFrame = new RegisterFrame();
				registerFrame.setVisible(true);
				registerFrame.setLocationRelativeTo(null);
			}
		});

		// Şifremi Unuttum Button
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetPasswordFrame resetPasswordFrame = new ResetPasswordFrame();
				resetPasswordFrame.setVisible(true);
				resetPasswordFrame.setLocationRelativeTo(null);
			}
		});

		// Şifreyi Gör
		lblOpenedEye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordFieldLogin.setEchoChar((char) 0);
				lblOpenedEye.setEnabled(false);
				lblOpenedEye_1.setEnabled(true);
			}
		});

		// Şifreyi Gizle
		lblOpenedEye_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordFieldLogin.setEchoChar('*');
				lblOpenedEye.setEnabled(true);
				lblOpenedEye_1.setEnabled(false);
			}
		});

		// Giriş Button
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = txtLoginUsername.getText();
				// ApplicationFrame.lblName.setText(txtLoginUsername);

				String password = new String(passwordFieldLogin.getPassword());

				try {
					Class.forName("org.postgresql.Driver");

					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();

					// Sql String
					String sql = "SELECT username, password FROM public.tbl_personel Where username='" + username
							+ "' and password = '" + password + "';";
					ResultSet resultSet = db.statement.executeQuery(sql);

					if (resultSet.next()) {
						applicationFrame = new ApplicationFrame();
						applicationFrame.setVisible(true);

						// Kullanıcı adını lblName JLabel'ına yazma
						ApplicationFrame.lblName.setText(username);

						// frame'in tüm ekranı kaplamasını istediğimiz için bu iki değeri set ettik.
						applicationFrame.setLocationRelativeTo(null);
						applicationFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						// Login Frame'i setVisible(false); yap.
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Kullanıcı Adı veya Şifre Yanlış", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}

					// Bağlantıyı Kapat
					resultSet.close();
					db.createConnection().close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

	}
}
