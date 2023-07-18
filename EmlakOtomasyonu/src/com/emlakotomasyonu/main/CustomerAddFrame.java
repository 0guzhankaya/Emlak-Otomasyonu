package com.emlakotomasyonu.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.emlakotomasyonu.database.DB;

public class CustomerAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtBirthDate;
	private JTextField txtPhoneNumber;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtEstateId;
	File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerAddFrame frame = new CustomerAddFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerAddFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-budget-96.png")));
		setTitle("Müşteri");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1210, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "M\u00FC\u015Fteri Ekle", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 323, 643);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ad :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 170, 129, 20);
		panel.add(lblNewLabel);

		txtName = new JTextField();
		txtName.setToolTipText("Adınızı Giriniz.");
		txtName.setColumns(10);
		txtName.setBounds(149, 172, 160, 20);
		panel.add(txtName);

		JLabel lblGvenlikSorusuCevab = new JLabel("Soyad : ");
		lblGvenlikSorusuCevab.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab.setBounds(10, 200, 129, 20);
		panel.add(lblGvenlikSorusuCevab);

		txtSurname = new JTextField();
		txtSurname.setToolTipText("Soyadınızı Giriniz.");
		txtSurname.setColumns(10);
		txtSurname.setBounds(149, 202, 160, 20);
		panel.add(txtSurname);

		JLabel lblCinsiyet = new JLabel("Cinsiyet :");
		lblCinsiyet.setHorizontalAlignment(SwingConstants.LEFT);
		lblCinsiyet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCinsiyet.setBounds(10, 230, 129, 20);
		panel.add(lblCinsiyet);

		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(null);
		radioButtonPanel.setToolTipText("Cinsiyetinizi Seçiniz.");
		radioButtonPanel.setBounds(149, 232, 160, 26);
		panel.add(radioButtonPanel);

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

		JLabel lblDoumTarihi = new JLabel("Doğum Tarihi :");
		lblDoumTarihi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoumTarihi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoumTarihi.setBounds(10, 260, 129, 20);
		panel.add(lblDoumTarihi);

		txtBirthDate = new JTextField();
		txtBirthDate.setToolTipText("GG/AA/YYYY Şeklinde Doğum Tarihinizi Giriniz.");
		txtBirthDate.setColumns(10);
		txtBirthDate.setBounds(149, 262, 160, 20);
		panel.add(txtBirthDate);

		JLabel lblTelefonNumaras = new JLabel("Telefon Numarası :");
		lblTelefonNumaras.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefonNumaras.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefonNumaras.setBounds(10, 292, 129, 20);
		panel.add(lblTelefonNumaras);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setToolTipText("0(xxx)xxxxxxx Şeklinde Telefon Numaranızı Giriniz.");
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(149, 294, 160, 20);
		panel.add(txtPhoneNumber);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 325, 129, 20);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setToolTipText("Email Adresinizi Giriniz.");
		txtEmail.setColumns(10);
		txtEmail.setBounds(149, 327, 160, 20);
		panel.add(txtEmail);

		JLabel lblAdres = new JLabel("Adres :");
		lblAdres.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdres.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdres.setBounds(10, 357, 129, 20);
		panel.add(lblAdres);

		JLabel lblVarlk = new JLabel("Varlık Id: ");
		lblVarlk.setHorizontalAlignment(SwingConstants.LEFT);
		lblVarlk.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVarlk.setBounds(10, 419, 129, 20);
		panel.add(lblVarlk);

		JLabel lblAddPhoto = new JLabel("Fotoğraf Ekle");
		lblAddPhoto.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAddPhoto.setIcon(
				new ImageIcon(CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-camera-32.png")));

		lblAddPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPhoto.setBounds(10, 140, 299, 20);
		panel.add(lblAddPhoto);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setBounds(113, 30, 100, 100);
		panel.add(panelPhoto);
		panelPhoto.setLayout(null);

		JLabel lblPhoto = new JLabel("Fotoğraf Yok");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(0, 0, 100, 100);
		panelPhoto.add(lblPhoto);

		JButton btnList = new JButton("LİSTELE");

		btnList.setIcon(
				new ImageIcon(CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		btnList.setFont(new Font("Verdana", Font.BOLD, 12));
		btnList.setBounds(6, 504, 303, 36);
		panel.add(btnList);

		JButton btnGncelle = new JButton("GÜNCELLE");

		btnGncelle.setIcon(new ImageIcon(
				CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));
		btnGncelle.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGncelle.setBounds(6, 550, 303, 36);
		panel.add(btnGncelle);

		JButton btnSil = new JButton("SİL");

		btnSil.setIcon(
				new ImageIcon(CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));
		btnSil.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSil.setBounds(6, 596, 303, 36);
		panel.add(btnSil);

		JTextPane txtAddress = new JTextPane();
		txtAddress.setBounds(149, 357, 160, 50);
		panel.add(txtAddress);

		JButton btnKaydet = new JButton("KAYDET");

		btnKaydet.setIcon(
				new ImageIcon(CustomerAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-save-32.png")));
		btnKaydet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnKaydet.setBounds(6, 458, 303, 36);
		panel.add(btnKaydet);

		txtEstateId = new JTextField();
		txtEstateId.setToolTipText("Varlık Id Giriniz.");
		txtEstateId.setColumns(10);
		txtEstateId.setBounds(149, 421, 160, 20);
		panel.add(txtEstateId);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 42, 843, 611);
		contentPane.add(scrollPane);

		table = new JTable();

		// Islemler
		
		// Fotoğraf Ekle
		lblAddPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					// Seçilen fotoğrafın yolu selectedFile değişkeninde tutuluyor.
					// Seçilen fotoğrafın yolunu kullanarak işle
					try {
						// Seçilen fotoğrafın ImageIcon olarak yüklenmesi
						ImageIcon photoIcon = new ImageIcon(selectedFile.getAbsolutePath());
						lblPhoto.setIcon(photoIcon);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// Satıra Tıklama İşlemi
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				// Seçilen satırdaki ID'yi al
				int customerId = (int) table.getValueAt(selectedRow, 0);

				try {
					Class.forName("org.postgresql.Driver");

					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, name, surname, gender, birthdate, phonenumber, email, address, estateid, photo FROM tbl_customer WHERE id = '"
							+ customerId + "';";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					while (resultSet.next()) {
						String id = resultSet.getString("id");
						String name = resultSet.getString("name");
						String surname = resultSet.getString("surname");
						String gender = resultSet.getString("gender");
						String birthDate = resultSet.getString("birthdate");
						String phoneNumber = resultSet.getString("phonenumber");
						String email = resultSet.getString("email");
						String address = resultSet.getString("address");
						int estateId = resultSet.getInt("estateid");

						txtName.setText(name);
						txtSurname.setText(surname);
						if (gender.equals("E")) {
							radioButtonMale.setSelected(true);
						} else if (gender.equals("K")) {
							radioButtonFemale.setSelected(true);
						}
						txtBirthDate.setText(birthDate);
						txtPhoneNumber.setText(phoneNumber);
						txtEmail.setText(email);
						txtAddress.setText(address);
						txtEstateId.setText(String.valueOf(estateId));

						// Fotoğraf İşlemleri
						byte[] photoData = resultSet.getBytes("photo");

						if (photoData != null) {
							ImageIcon photoIcon = new ImageIcon(photoData);
							lblPhoto.setIcon(photoIcon);
						} else {
							lblPhoto.setIcon(null);
						}

					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});

		// Kaydet Button
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String surname = txtSurname.getText();
				// Cinsiyet Seçimi
				String gender = null;
				if (radioButtonMale.isSelected()) {
					gender = "E";
				} else if (radioButtonFemale.isSelected()) {
					gender = "K";
				}
				String birthDate = txtBirthDate.getText();
				String phoneNumber = txtPhoneNumber.getText();
				String email = txtEmail.getText();
				String address = txtAddress.getText();
				int estateid = Integer.parseInt(txtEstateId.getText());

				// TextBox Check
				if (txtName.getText().equals("") || txtSurname.getText().equals("") || txtBirthDate.getText().equals("")
						|| txtPhoneNumber.getText().equals("") || txtEmail.getText().equals("")
						|| txtAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "HATA!", "Lütfen Tüm Zorunlu Alanları Doldurunuz.",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Class.forName("org.postgresql.Driver");
						// DB classında olan Connection'a erişim.
						DB db = new DB();
						db.createConnection();

						String sql = "INSERT INTO public.tbl_customer(name, surname, gender, birthdate, phonenumber, email, address, estateid, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement statement = db.createConnection().prepareStatement(sql);

						FileInputStream fis = new FileInputStream(selectedFile);

						statement.setString(1, name);
						statement.setString(2, surname);
						statement.setString(3, gender);
						statement.setString(4, birthDate);
						statement.setString(5, phoneNumber);
						statement.setString(6, email);
						statement.setString(7, address);
						statement.setInt(8, estateid);
						statement.setBinaryStream(9, fis, (int) selectedFile.length());

						// Sorguyu çalıştır
						int rowsAffected = statement.executeUpdate();

						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(null, "Kayıt Başarılı.");

							// Fotoğraf Kutusunu Temizle
							lblPhoto.setIcon(null);

							// Fieldları Temizle
							txtName.setText("");
							txtSurname.setText("");
							txtBirthDate.setText("");
							txtPhoneNumber.setText("");
							txtEmail.setText("");
							txtAddress.setText("");
							txtEstateId.setText("");
							buttonGroup.clearSelection();

							// JTable yenile
							list();
						} else {
							JOptionPane.showMessageDialog(null, "Kayıt Başarısız! ", "HATA!",
									JOptionPane.ERROR_MESSAGE);
						}

						// Bağlantıyı Kapat
						statement.close();
						db.createConnection().close();
						;
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e2) {
						System.out.println("SQL HATASI\n");
						e2.printStackTrace();
					} catch (FileNotFoundException e3) {
						JOptionPane.showMessageDialog(null, "HATA! Dosya/Fotoğraf Bulunamadı!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
						e3.printStackTrace();
					}
				}
			}
		});

		// Frame
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				list();
			}
		});

		// List Button
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		// Guncelle Button
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(CustomerAddFrame.this, "Lütfen güncellemek istediğiniz satırı seçin.",
							"Hata! Satır Seçilmedi.", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Seçilen satırdaki ID'yi al
				int id = (int) table.getValueAt(selectedRow, 0);

				String name = txtName.getText();
				String surname = txtSurname.getText();
				// Cinsiyet Seçimi
				String gender = null;
				if (radioButtonMale.isSelected()) {
					gender = "E";
				} else if (radioButtonFemale.isSelected()) {
					gender = "K";
				}
				String birthDate = txtBirthDate.getText();
				String phoneNumber = txtPhoneNumber.getText();
				String email = txtEmail.getText();
				String address = txtAddress.getText();
				int estateid = Integer.parseInt(txtEstateId.getText());

				// Veritabanındaki bilgileri güncelle
				try {
					// Seçilen fotoğrafın byte dizisine dönüştürülmesi
					byte[] photoBytes = Files.readAllBytes(selectedFile.toPath());

					DB db = new DB();
					Connection connection = db.createConnection();

					// Sql String
					String sql = "UPDATE public.tbl_customer SET name = ?, surname = ?, gender = ?, birthdate = ?, phonenumber = ?, email = ?, address = ?, estateid = ?, photo = ? WHERE id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, name);
					statement.setString(2, surname);
					statement.setString(3, gender);
					statement.setString(4, birthDate);
					statement.setString(5, phoneNumber);
					statement.setString(6, email);
					statement.setString(7, address);
					statement.setInt(8, estateid);
					statement.setBytes(9, photoBytes);
					statement.setInt(10, id);

					// Sorguyu Çalıştır
					int result = statement.executeUpdate();

					if (result >= 0) {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarılı.");
						list();
					} else {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarısız!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}

					// Connection kapat
					statement.close();
					connection.close();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("SQL HATASI \n");
					e2.printStackTrace();
				} catch (IOException e3) {
					JOptionPane.showMessageDialog(null, "Fotoğraf okuma hatası: " + e3.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
					e3.printStackTrace();
				}
			}
		});

		// Sil Button
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// table'dan satır seçer
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					// Eğer bir satır seçilmediyse kullanıcıyı uyar
					JOptionPane.showMessageDialog(CustomerAddFrame.this, "Lütfen silmek istediğiniz satırı seçin.",
							"Hata! Satır Seçilmedi.", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int res = JOptionPane.showConfirmDialog(null, "Silmek İstediğinize Emin Misiniz?");

				if (res == 0) {
					// Veritabanından Sil
					try {
						Class.forName("org.postgresql.Driver");

						// DB classında olan Connection'a erişim.
						DB db = new DB();
						db.createConnection();
						Connection connection = db.createConnection();

						int id = (int) table.getValueAt(selectedRow, 0);
						String sql = "DELETE FROM public.tbl_customer WHERE id = ?";

						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, id);

						// Sorguyu Çalıştır
						int rowsAffected = preparedStatement.executeUpdate();

						preparedStatement.close();
						connection.close();

						// Kayıt Başarılı İse
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı.");
							
							// Fieldları Temizle
							txtName.setText("");
							txtSurname.setText("");
							txtBirthDate.setText("");
							txtPhoneNumber.setText("");
							txtEmail.setText("");
							txtAddress.setText("");
							txtEstateId.setText("");
							buttonGroup.clearSelection();
							
							list();
						} else {
							JOptionPane.showMessageDialog(null, "Silme İşlemi Başarısız!", "HATA!",
									JOptionPane.ERROR_MESSAGE);
						}

						// Table'ı yenile
						list();

					} catch (ClassNotFoundException e1) {
						System.out.println("HATA! \n");
						e1.printStackTrace();
					} catch (SQLException e2) {
						System.out.println("SQL HATASI \n");
						e2.printStackTrace();
					}
				} else {
					return;
				}
			}
		});

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Ad", "Soyad", "Cinsiyet",
				"Doğum Tarihi", "Telefon No", "Email", "Adres", "Varlık" }));
		scrollPane.setViewportView(table);

		JButton btnAsc = new JButton("Sırala");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		btnAsc.setIcon(new ImageIcon(CustomerAddFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16.png")));
		btnAsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAsc.setBounds(776, 12, 200, 25);
		contentPane.add(btnAsc);

		JButton btnDsc = new JButton("Tersten Sırala");
		btnDsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, name, surname, gender, birthdate, phonenumber, email, address, estateid, photo FROM public.tbl_customer order by id desc";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					// JTable modeli
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					// Tüm Satırları Temizle
					model.setRowCount(0);

					// Sonuçları JTable'a ekle
					while (resultSet.next()) {

						// Tablonun içerisine gelen veriler
						int id = resultSet.getInt("id");
						String name = resultSet.getString("name");
						String surname = resultSet.getString("surname");
						String gender = resultSet.getString("gender");
						String birthdate = resultSet.getString("birthdate");
						String phonenumber = resultSet.getString("phonenumber");
						String email = resultSet.getString("email");
						String address = resultSet.getString("address");
						int estateid = resultSet.getInt("estateid");

						Object[] row = { id, name, surname, gender, birthdate, phonenumber, email, address, estateid };
						model.addRow(row);
					}

					// Bağlantıyı Kapat
					statement.close();
					connection.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("SQL HATASI!\n");
					e2.printStackTrace();
				}
			}
		});
		btnDsc.setIcon(new ImageIcon(CustomerAddFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16 (1).png")));
		btnDsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDsc.setBounds(986, 10, 200, 25);
		contentPane.add(btnDsc);
	}

	public void list() {
		try {
			Class.forName("org.postgresql.Driver");
			// DB classında olan Connection'a erişim.
			DB db = new DB();
			Connection connection = db.createConnection();

			String sql = "SELECT id, name, surname, gender, birthdate, phonenumber, email, address, estateid, photo FROM public.tbl_customer";
			Statement statement = connection.createStatement();

			// Sorguyu Çalıştır
			ResultSet resultSet = statement.executeQuery(sql);

			// JTable modeli
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Tüm Satırları Temizle
			model.setRowCount(0);

			// Sonuçları JTable'a ekle
			while (resultSet.next()) {

				// Tablonun içerisine gelen veriler
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String gender = resultSet.getString("gender");
				String birthdate = resultSet.getString("birthdate");
				String phonenumber = resultSet.getString("phonenumber");
				String email = resultSet.getString("email");
				String address = resultSet.getString("address");
				int estateid = resultSet.getInt("estateid");

				Object[] row = { id, name, surname, gender, birthdate, phonenumber, email, address, estateid };
				model.addRow(row);
			}

			// Bağlantıyı Kapat
			statement.close();
			connection.close();

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(), "HATA!",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL HATASI!\n");
			e2.printStackTrace();
		}
	}
}
