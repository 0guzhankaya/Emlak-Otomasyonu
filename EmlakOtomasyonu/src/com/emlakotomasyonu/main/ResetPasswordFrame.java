package com.emlakotomasyonu.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.emlakotomasyonu.database.DB;

public class ResetPasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordFieldNewPasswordAgain;
	private JPasswordField passwordFieldNewPassword;
	private JTextField txtSecurityQuestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswordFrame frame = new ResetPasswordFrame();
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
	public ResetPasswordFrame() {
		setFont(new Font("Verdana", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ResetPasswordFrame.class.getResource("/com/emlakotomasyonu/images/icons8-reset-32.png")));
		setTitle("Emlak Otomasyonu- Şifre Sıfırla");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kullanıcı Adı:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(109, 214, 90, 20);
		contentPane.add(lblNewLabel);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(209, 216, 160, 20);
		contentPane.add(txtUsername);

		JLabel lblGvenlikSorusuCevab = new JLabel("Güvenlik Sorusu Cevabı:");
		lblGvenlikSorusuCevab.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab.setBounds(50, 268, 160, 20);
		contentPane.add(lblGvenlikSorusuCevab);

		JLabel lblYeniifre = new JLabel("Yeni Şifre:");
		lblYeniifre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYeniifre.setBounds(109, 322, 90, 20);
		contentPane.add(lblYeniifre);

		JLabel lblYeniifreTekrar = new JLabel("Yeni Şifre Tekrar:");
		lblYeniifreTekrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYeniifreTekrar.setBounds(94, 376, 116, 20);
		contentPane.add(lblYeniifreTekrar);

		passwordFieldNewPasswordAgain = new JPasswordField();
		passwordFieldNewPasswordAgain.setBounds(209, 378, 160, 20);
		contentPane.add(passwordFieldNewPasswordAgain);

		passwordFieldNewPassword = new JPasswordField();
		passwordFieldNewPassword.setBounds(209, 324, 160, 20);
		contentPane.add(passwordFieldNewPassword);

		txtSecurityQuestion = new JTextField();
		txtSecurityQuestion.setColumns(10);
		txtSecurityQuestion.setBounds(209, 270, 160, 20);
		contentPane.add(txtSecurityQuestion);

		JLabel lblLoginFrameImage = new JLabel("");
		lblLoginFrameImage.setIcon(new ImageIcon(
				ResetPasswordFrame.class.getResource("/com/emlakotomasyonu/images/icons8-password-128.png")));
		lblLoginFrameImage.setBounds(175, 56, 128, 128);
		contentPane.add(lblLoginFrameImage);

		JButton btnResetPasswordConfirm = new JButton("ONAYLA");

		btnResetPasswordConfirm.setIcon(
				new ImageIcon(ResetPasswordFrame.class.getResource("/com/emlakotomasyonu/images/icons8-ok-32.png")));
		btnResetPasswordConfirm.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnResetPasswordConfirm.setBounds(109, 426, 260, 36);
		contentPane.add(btnResetPasswordConfirm);

		// İşlemler
		
		// Onayla Button
		btnResetPasswordConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textboxların boş olup olmadığını kontrol et eğer boş değilse mesajı ver

				if (txtUsername.getText().equals("") || txtSecurityQuestion.getText().equals("")
						|| passwordFieldNewPassword.getText().equals("")
						|| passwordFieldNewPasswordAgain.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "HATA!", "Lütfen Tüm Zorunlu Alanları Doldurunuz.",
							JOptionPane.ERROR_MESSAGE);
				} else if (!passwordFieldNewPassword.getText().equals(passwordFieldNewPasswordAgain.getText())) {
					JOptionPane.showMessageDialog(null, "HATA!", "Girdiğiniz Yeni Şifreler Eşleşmiyor.",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String username = txtUsername.getText();
					String answer = txtSecurityQuestion.getText();
					String password = passwordFieldNewPassword.getText();

					try {
						Class.forName("org.postgresql.Driver");
						// DB classında olan Connection'a erişim.
						DB db = new DB();
						db.createConnection();

						String sql = "SELECT saveanswer FROM public.tbl_personel WHERE username = ?";
						PreparedStatement statement = db.createConnection().prepareStatement(sql);
						statement.setString(1, username);
						ResultSet resultSet = statement.executeQuery();

						if (resultSet.next()) {
							String savedAnswer = resultSet.getString("saveanswer");

							// Güvenlik Sorusu Kontrolü
							if (answer.equals(savedAnswer)) {
								try {
									// Sql String
									String updateSql = "UPDATE public.tbl_personel SET password = ? WHERE username = ?";
									PreparedStatement updateStatement = db.createConnection()
											.prepareStatement(updateSql);
									updateStatement.setString(1, password);
									updateStatement.setString(2, username);

									updateStatement.executeUpdate();
									updateStatement.close();

									JOptionPane.showMessageDialog(null,
											"Şifreniz Başarıyla Sıfırlandı.\nGiriş Yapabilirsiniz.");
									
									txtUsername.setText("");
									txtSecurityQuestion.setText("");
									passwordFieldNewPassword.setText("");
									passwordFieldNewPasswordAgain.setText("");
									
								} catch (Exception e2) {
									System.out.println("HATA!\n");
									e2.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null, "HATA!", "Kurtarma Cevabınız Yanlış!",
										JOptionPane.ERROR_MESSAGE);
							}
						}

						resultSet.close();
						statement.close();
						db.createConnection().close();

					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						System.out.println("SQL Hatasi!");
						e1.printStackTrace();
					}
				}

				// JFrame'i kapatmak için dispose() methodunu kullan.
				RegisterFrame frame = new RegisterFrame();
				frame.dispose();
			}
		});

	}
}