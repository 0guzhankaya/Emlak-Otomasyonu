package com.emlakotomasyonu.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.emlakotomasyonu.database.DB;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNameRegister;
	private JTextField txtSurnameRegister;
	private JTextField txtBDateRegister;
	private JTextField txtPhoneNumberRegister;
	private JTextField txtEmailRegister;
	private JTextField txtAddressRegister;
	private JPasswordField passwordFieldRegister;
	private JTextField txtAnswerRegister;
	private JTextField txtUsernameRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setFont(new Font("Verdana", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RegisterFrame.class.getResource("/com/emlakotomasyonu/images/icons8-register-100.png")));
		setTitle("Emlak Otomasyonu - Kayıt Ol");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLoginFrameImage = new JLabel("");
		lblLoginFrameImage.setIcon(
				new ImageIcon(RegisterFrame.class.getResource("/com/emlakotomasyonu/images/icons8-register-100.png")));
		lblLoginFrameImage.setBounds(180, 35, 128, 128);
		contentPane.add(lblLoginFrameImage);

		JLabel lblNewLabel = new JLabel("Ad :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(91, 173, 129, 20);
		contentPane.add(lblNewLabel);

		txtNameRegister = new JTextField();
		txtNameRegister.setToolTipText("Adınızı Giriniz.");
		txtNameRegister.setColumns(10);
		txtNameRegister.setBounds(230, 175, 160, 20);
		contentPane.add(txtNameRegister);

		JLabel lblGvenlikSorusuCevab = new JLabel("Soyad : ");
		lblGvenlikSorusuCevab.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab.setBounds(91, 203, 129, 20);
		contentPane.add(lblGvenlikSorusuCevab);

		txtSurnameRegister = new JTextField();
		txtSurnameRegister.setToolTipText("Soyadınızı Giriniz.");
		txtSurnameRegister.setColumns(10);
		txtSurnameRegister.setBounds(230, 205, 160, 20);
		contentPane.add(txtSurnameRegister);

		JLabel lblCinsiyet = new JLabel("Cinsiyet :");
		lblCinsiyet.setHorizontalAlignment(SwingConstants.LEFT);
		lblCinsiyet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCinsiyet.setBounds(91, 233, 129, 20);
		contentPane.add(lblCinsiyet);

		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi :");
		lblDoumTarihi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoumTarihi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoumTarihi.setBounds(91, 263, 129, 20);
		contentPane.add(lblDoumTarihi);

		txtBDateRegister = new JTextField();
		txtBDateRegister.setToolTipText("GG/AA/YYYY Şeklinde Doğum Tarihinizi Giriniz.");
		txtBDateRegister.setColumns(10);
		txtBDateRegister.setBounds(230, 265, 160, 20);
		contentPane.add(txtBDateRegister);

		JLabel lblTelefonNumaras = new JLabel("Telefon Numarası :");
		lblTelefonNumaras.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefonNumaras.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefonNumaras.setBounds(91, 295, 129, 20);
		contentPane.add(lblTelefonNumaras);

		txtPhoneNumberRegister = new JTextField();
		txtPhoneNumberRegister.setToolTipText("0(xxx)xxxxxxx Şeklinde Telefon Numaranızı Giriniz.");
		txtPhoneNumberRegister.setColumns(10);
		txtPhoneNumberRegister.setBounds(230, 297, 160, 20);
		contentPane.add(txtPhoneNumberRegister);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(91, 328, 129, 20);
		contentPane.add(lblEmail);

		txtEmailRegister = new JTextField();
		txtEmailRegister.setToolTipText("Email Adresinizi Giriniz.");
		txtEmailRegister.setColumns(10);
		txtEmailRegister.setBounds(230, 330, 160, 20);
		contentPane.add(txtEmailRegister);

		JLabel lblAdres = new JLabel("Adres :");
		lblAdres.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdres.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdres.setBounds(91, 360, 129, 20);
		contentPane.add(lblAdres);

		txtAddressRegister = new JTextField();
		txtAddressRegister.setToolTipText("Adresinizi Giriniz.");
		txtAddressRegister.setColumns(10);
		txtAddressRegister.setBounds(230, 362, 160, 47);
		contentPane.add(txtAddressRegister);

		JLabel lblifre = new JLabel("Şifre :");
		lblifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblifre.setBounds(91, 449, 129, 20);
		contentPane.add(lblifre);

		JLabel lblGvenlikSorusu = new JLabel("Güvenlik Sorusu :");
		lblGvenlikSorusu.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusu.setBounds(92, 479, 128, 20);
		contentPane.add(lblGvenlikSorusu);

		JButton btnRegister = new JButton("KAYIT OL");

		btnRegister.setIcon(
				new ImageIcon(RegisterFrame.class.getResource("/com/emlakotomasyonu/images/icons8-ok-32.png")));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegister.setBounds(91, 550, 299, 36);
		contentPane.add(btnRegister);

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setToolTipText("Cinsiyetinizi Seçiniz.");
		radioButtonPanel.setBounds(230, 235, 160, 26);
		contentPane.add(radioButtonPanel);
		radioButtonPanel.setLayout(null);

		JRadioButton radioButtonMale = new JRadioButton("Erkek");
		radioButtonMale.setFont(new Font("Verdana", Font.PLAIN, 11));
		radioButtonMale.setBounds(10, 0, 70, 26);
		radioButtonPanel.add(radioButtonMale);

		JRadioButton radioButtonFemale = new JRadioButton("Kadın");
		radioButtonFemale.setFont(new Font("Verdana", Font.PLAIN, 11));
		radioButtonFemale.setBounds(80, 0, 70, 26);
		radioButtonPanel.add(radioButtonFemale);

		// ButtonGroup oluşturma
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonFemale);
		buttonGroup.add(radioButtonMale);

		passwordFieldRegister = new JPasswordField();
		passwordFieldRegister.setToolTipText("En Az 6 Karakterli Şifre Oluşturunuz.");
		passwordFieldRegister.setBounds(230, 451, 160, 20);
		contentPane.add(passwordFieldRegister);

		JComboBox comboBoxSaveQuestion = new JComboBox();

		comboBoxSaveQuestion.setToolTipText("Kurtarma İçin Güvenlik Kodu Seçiniz.");
		comboBoxSaveQuestion.setModel(new DefaultComboBoxModel(new String[] { "", "İlkokul Öğretmeni Adı",
				"Anne Kızlık Soyadı", "Çocukluk Lakabı", "Favori Film Oyuncusu" }));
		comboBoxSaveQuestion.setBackground(Color.WHITE);
		comboBoxSaveQuestion.setBounds(230, 480, 160, 21);
		contentPane.add(comboBoxSaveQuestion);

		JLabel lblCevap = new JLabel("Cevap :");
		lblCevap.setHorizontalAlignment(SwingConstants.LEFT);
		lblCevap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCevap.setBounds(91, 509, 129, 20);
		contentPane.add(lblCevap);

		txtAnswerRegister = new JTextField();
		txtAnswerRegister.setEnabled(false);
		txtAnswerRegister.setToolTipText("Kullanıcı Adınızı Giriniz.");
		txtAnswerRegister.setColumns(10);
		txtAnswerRegister.setBounds(230, 511, 160, 20);
		contentPane.add(txtAnswerRegister);

		JLabel lblNewLabel_2 = new JLabel("Kullanıcı Adı:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(91, 417, 129, 20);
		contentPane.add(lblNewLabel_2);

		txtUsernameRegister = new JTextField();
		txtUsernameRegister.setToolTipText("Kullanıcı Adınızı Giriniz.");
		txtUsernameRegister.setColumns(10);
		txtUsernameRegister.setBounds(230, 419, 160, 20);
		contentPane.add(txtUsernameRegister);

		// İşlemler
		// Index seçildiği zaman cevap kutusunu aktive et.
		comboBoxSaveQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxSaveQuestion.getSelectedItem() != null) {
					txtAnswerRegister.setEnabled(true);
				} else if (comboBoxSaveQuestion.getSelectedIndex() <= 0
						&& comboBoxSaveQuestion.getSelectedItem() == null) {
					txtAnswerRegister.setEnabled(false);
				}
			}
		});

		// Kayıt Ol Butonu
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtNameRegister.getText();
				String surname = txtSurnameRegister.getText();
				// Cinsiyet Seçimi
				String gender = null;
				if (radioButtonMale.isSelected()) {
					gender = "E";
				} else if (radioButtonFemale.isSelected()) {
					gender = "K";
				}
				String birthDate = txtBDateRegister.getText();
				String phoneNumber = txtPhoneNumberRegister.getText();
				String email = txtEmailRegister.getText();
				String address = txtAddressRegister.getText();
				String username = txtUsernameRegister.getText();
				String password = new String(passwordFieldRegister.getPassword());
				int saveQuestion = comboBoxSaveQuestion.getSelectedIndex();
				String saveAnswer = txtAnswerRegister.getText();

				// TextBox Check
				if (txtNameRegister.getText().equals("") || txtSurnameRegister.getText().equals("")
						|| txtAddressRegister.getText().equals("") || txtAnswerRegister.getText().equals("")
						|| txtBDateRegister.getText().equals("") || txtEmailRegister.getText().equals("")
						|| txtPhoneNumberRegister.getText().equals("") || txtUsernameRegister.getText().equals("")
						|| passwordFieldRegister.getPassword().length == 0
						|| comboBoxSaveQuestion.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "HATA!", "Lütfen Tüm Zorunlu Alanları Doldurunuz.",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Class.forName("org.postgresql.Driver");
						// DB classında olan Connection'a erişim.
						DB db = new DB();
						db.createConnection();

						String sql = "INSERT INTO public.tbl_personel(name, surname, gender, birthdate, phonenumber, email, address, username, password, savequestion, saveanswer) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement statement = db.createConnection().prepareStatement(sql);
						statement.setString(1, name);
						statement.setString(2, surname);
						statement.setString(3, gender);
						statement.setString(4, birthDate);
						statement.setString(5, phoneNumber);
						statement.setString(6, email);
						statement.setString(7, address);
						statement.setString(8, username);
						statement.setString(9, password);
						statement.setInt(10, saveQuestion);
						statement.setString(11, saveAnswer);

						// Sorguyu çalıştır
						int rowsInserted = statement.executeUpdate();

						if (rowsInserted > 0) {
							JOptionPane.showMessageDialog(null,
									"Başarılı Bir Şekilde Kayıt Oldunuz.\nGiriş Ekranına Dönüp Giriş Yapabilirsiniz.");
							txtAddressRegister.setText("");
							txtAnswerRegister.setText("");
							txtBDateRegister.setText("");
							txtEmailRegister.setText("");
							txtNameRegister.setText("");
							txtPhoneNumberRegister.setText("");
							txtSurnameRegister.setText("");
							txtUsernameRegister.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Kayıt Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
						}

						// Bağlantıyı Kapat
						statement.close();
						db.createConnection().close();

					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						System.out.println("SQL HATASI\n");
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
