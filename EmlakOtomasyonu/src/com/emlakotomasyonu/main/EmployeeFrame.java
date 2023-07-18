package com.emlakotomasyonu.main;

import java.awt.Color;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.emlakotomasyonu.database.DB;

public class EmployeeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtSalary;
	private JTextField txtPrim;
	private JTable table;
	private JFileChooser fileChooser;
	File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeFrame frame = new EmployeeFrame();
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
	public EmployeeFrame() {
		setFont(new Font("Verdana", Font.PLAIN, 14));

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-account-32.png")));
		setTitle("Personel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1210, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 323, 643);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u00C7al\u0131\u015Fan Bilgilerini Detay", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		contentPane.add(panel);

		JLabel lblKullancAd = new JLabel("Kullanıcı Adı :");
		lblKullancAd.setHorizontalAlignment(SwingConstants.LEFT);
		lblKullancAd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKullancAd.setBounds(10, 170, 129, 20);
		panel.add(lblKullancAd);

		txtUsername = new JTextField();
		txtUsername.setToolTipText("Adınızı Giriniz.");
		txtUsername.setColumns(10);
		txtUsername.setBounds(149, 172, 160, 20);
		panel.add(txtUsername);

		JLabel lblMaa = new JLabel("Maaş :");
		lblMaa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaa.setBounds(10, 200, 129, 20);
		panel.add(lblMaa);

		txtSalary = new JTextField();
		txtSalary.setToolTipText("Soyadınızı Giriniz.");
		txtSalary.setColumns(10);
		txtSalary.setBounds(149, 202, 160, 20);
		panel.add(txtSalary);

		JLabel lblPrim = new JLabel("Prim :");
		lblPrim.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrim.setBounds(10, 230, 129, 20);
		panel.add(lblPrim);

		JLabel lblRol = new JLabel("Rol :");
		lblRol.setHorizontalAlignment(SwingConstants.LEFT);
		lblRol.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRol.setBounds(10, 262, 129, 20);
		panel.add(lblRol);

		JComboBox cbAuthory = new JComboBox();
		cbAuthory.setModel(new DefaultComboBoxModel(new String[] { "", "Personel", "Patron" }));
		cbAuthory.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbAuthory.setToolTipText("Müşterinin, şirkete kayıtlı mal varlığı var ise seçiniz.");
		cbAuthory.setBackground(Color.WHITE);
		cbAuthory.setBounds(149, 263, 160, 20);
		panel.add(cbAuthory);

		JLabel lblAddPhoto = new JLabel("Fotoğraf Ekle");
		lblAddPhoto.setIcon(
				new ImageIcon(EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-camera-32.png")));
		lblAddPhoto.setFont(new Font("Verdana", Font.PLAIN, 12));

		lblAddPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPhoto.setBounds(10, 135, 299, 32);
		panel.add(lblAddPhoto);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setLayout(null);
		panelPhoto.setBounds(113, 30, 100, 100);
		panel.add(panelPhoto);

		JLabel lblPhoto = new JLabel("Fotoğraf Yok");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(0, 0, 100, 100);
		panelPhoto.add(lblPhoto);

		JButton btnKaydet = new JButton("KAYDET");

		btnKaydet.setIcon(
				new ImageIcon(EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-save-32.png")));
		btnKaydet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnKaydet.setBounds(6, 458, 303, 36);
		panel.add(btnKaydet);

		JButton btnGncelle = new JButton("GÜNCELLE");

		btnGncelle.setIcon(new ImageIcon(
				EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));
		btnGncelle.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGncelle.setBounds(6, 550, 303, 36);
		panel.add(btnGncelle);

		JButton btnSil = new JButton("SİL");

		btnSil.setIcon(
				new ImageIcon(EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));
		btnSil.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSil.setBounds(6, 596, 303, 36);
		panel.add(btnSil);

		txtPrim = new JTextField();
		txtPrim.setToolTipText("Soyadınızı Giriniz.");
		txtPrim.setColumns(10);
		txtPrim.setBounds(149, 232, 160, 20);
		panel.add(txtPrim);

		JButton btnListele = new JButton("LİSTELE");

		btnListele.setIcon(
				new ImageIcon(EmployeeFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		btnListele.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListele.setBounds(6, 504, 303, 36);
		panel.add(btnListele);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 42, 843, 611);
		contentPane.add(scrollPane);

		table = new JTable();

		// Islemler

		// Fotoğraf Seç
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

		// Satır Seç
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				// Seçilen satırdaki ID'yi al
				int id = (int) table.getValueAt(selectedRow, 0);

				try {
					Class.forName("org.postgresql.Driver");
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT username, salary, prim, authory, photo FROM public.tbl_personel WHERE id = '"
							+ id + "';";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					while (resultSet.next()) {
						String username = resultSet.getString("username");
						Double salary = resultSet.getDouble("salary");
						Double prim = resultSet.getDouble("prim");
						boolean authory = resultSet.getBoolean("authory");

						// Verileri textField'lara aktar
						txtUsername.setText(username);
						txtSalary.setText(String.valueOf(salary));
						txtPrim.setText(String.valueOf(prim));
						if (authory == true) {
							cbAuthory.setSelectedIndex(2);
						} else if (authory == false) {
							cbAuthory.setSelectedIndex(1);
						}

						// Fotoğraf İşlemleri
						byte[] photoData = resultSet.getBytes("photo");

						// JLabel'da göster
						if (photoData != null) {
							ImageIcon photoIcon = new ImageIcon(photoData);
							lblPhoto.setIcon(photoIcon);
						} else {
							lblPhoto.setIcon(null);
						}

					}

					resultSet.close();
					statement.close();
					connection.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					System.out.println("SQL HATASI\n");
					e2.printStackTrace();
				}
			}
		});

		// Kaydet Button
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				Double salary = Double.parseDouble(txtSalary.getText());
				Double prim = Double.parseDouble(txtPrim.getText());

				// Yetkilendirme İşlemi
				boolean authory = false;

				if (cbAuthory.getSelectedIndex() == 2) {
					authory = true;
				} else if (cbAuthory.getSelectedIndex() == 1) {
					authory = false;
				}

				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					// Fotoğraf İşlemleri
					File file = selectedFile;
					FileInputStream fis = new FileInputStream(file);

					String sql = "INSERT INTO public.tbl_personel(username, salary, prim, authory, photo) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, username);
					statement.setDouble(2, salary);
					statement.setDouble(3, prim);
					statement.setBoolean(4, authory);
					statement.setBinaryStream(5, fis, (int) file.length());

					// Sorguyu çalıştır
					int rowCount = statement.executeUpdate();

					if (rowCount > 0) {
						JOptionPane.showMessageDialog(null, "Kayıt Başarılı.");
						
						txtPrim.setText("");
						txtSalary.setText("");
						txtUsername.setText("");
						cbAuthory.setSelectedIndex(0);
						lblPhoto.setIcon(null);
						
						list();
						
					} else {
						JOptionPane.showMessageDialog(null, "Kayıt Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
					}

					// Bağlantıyı Kapat
					statement.close();
					fis.close();
					connection.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					System.out.println("SQL HATASI\n");
					e2.printStackTrace();
				} catch (FileNotFoundException e3) {
					e3.printStackTrace();
				} catch (IOException e4) {
					e4.printStackTrace();
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

		// Listele Button
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		// Güncelle Button
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Lütfen güncellenecek bir satır seçin.", "Uyarı",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				int id = (int) table.getValueAt(selectedRow, 0);
				String username = txtUsername.getText();
				Double salary = Double.parseDouble(txtSalary.getText());
				Double prim = Double.parseDouble(txtPrim.getText());

				// Yetkilendirme İşlemi
				boolean authory = false;

				if (cbAuthory.getSelectedIndex() == 2) {
					authory = true;
				} else if (cbAuthory.getSelectedIndex() == 1) {
					authory = false;
				}

				// boolean authory = (boolean) cbAuthory.getSelectedItem();
				String filePath = selectedFile.getAbsolutePath();

				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql;
					PreparedStatement statement;

					if (filePath.equals("")) {
						// Fotoğraf güncellemesi olmadan sadece diğer alanları güncelle
						sql = "UPDATE public.tbl_personel SET username = '" + username + "', salary = '" + salary
								+ "', prim = '" + prim + "', authory = '" + authory + "' WHERE id = '" + id + "'";
						statement = connection.prepareStatement(sql);

					} else {
						// Fotoğraf güncellemesi ile birlikte diğer alanları da güncelle
						File file = new File(filePath);
						FileInputStream fis = new FileInputStream(file);

						sql = "UPDATE public.tbl_personel SET username = '" + username + "', salary = '" + salary
								+ "', prim = '" + prim + "', authory = '" + authory + "', photo = '" + fis
								+ "' WHERE id = '" + id + "'";
						statement = connection.prepareStatement(sql);

						fis.close();
					}

					// Sorguyu çalıştır
					int rowCount = statement.executeUpdate();

					if (rowCount > 0) {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarılı.");				

						// Tabloyu Yenile
						list();

						// Fotoğraf Kutusunu Sıfırla
						lblPhoto.setIcon(null);

						// Fieldları Sıfırla
						txtUsername.setText("");
						txtSalary.setText("");
						txtPrim.setText("");
						cbAuthory.setSelectedIndex(0);
						lblPhoto.setIcon(null);

					} else {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarısız!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
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
				} catch (FileNotFoundException e3) {
					e3.printStackTrace();
				} catch (IOException e4) {
					e4.printStackTrace();
				}
			}
		});

		// Sil Button
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Table'dan satır seç
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					// Eğer bir satır seçilmediyse kullanıcıyı uyar
					JOptionPane.showMessageDialog(EmployeeFrame.this, "Lütfen silmek istediğiniz satırı seçin.",
							"Hata! Satır Seçilmedi.", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Veritabanından sil
				try {
					Class.forName("org.postgresql.Driver");

					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();
					String sql = "DELETE FROM public.tbl_personel WHERE id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);

					// Seçilen satırın ID'sini alarak sorguya parametre olarak ekliyoruz
					int id = (int) table.getValueAt(selectedRow, 0);
					statement.setInt(1, id);

					// Sorguyu Çalıştır
					int rowsAffected = statement.executeUpdate();

					// Kayıt Başarılı İse
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı.");
						
						// Fieldları Sıfırla
						txtUsername.setText("");
						txtSalary.setText("");
						txtPrim.setText("");
						cbAuthory.setSelectedIndex(0);
						lblPhoto.setIcon(null);
						
						// Table'ı yenile
						list();
					} else {
						JOptionPane.showMessageDialog(null, "Silme İşlemi Başarısız!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}

					statement.close();
					connection.close();

				} catch (ClassNotFoundException e1) {
					System.out.println("HATA! \n");
					e1.printStackTrace();
				} catch (SQLException e2) {
					System.out.println("SQL HATASI : \n");
					e2.printStackTrace();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Ad", "Soyad", "Kullanıcı Adı", "Telefon No", "Maaş", "Prim", "Rol" }));
		scrollPane.setViewportView(table);

		JButton btnAsc = new JButton("Sırala");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		btnAsc.setIcon(new ImageIcon(EmployeeFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16.png")));
		btnAsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAsc.setBounds(776, 7, 200, 25);
		contentPane.add(btnAsc);

		JButton btnDsc = new JButton("Tersten Sırala");
		btnDsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, name, surname, username, phonenumber, salary, prim, authory FROM public.tbl_personel ORDER BY id DESC";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					// JTable modeli
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					// Tüm Satırları Temizle
					model.setRowCount(0);

					// Sonuçları JTable'a ekle
					while (resultSet.next()) {
						// Tablodaki verileri al
						int id = resultSet.getInt("id");
						String name = resultSet.getString("name");
						String surname = resultSet.getString("surname");
						String username = resultSet.getString("username");
						String phoneNumber = resultSet.getString("phonenumber");
						String salary = resultSet.getString("salary");
						String prim = resultSet.getString("prim");
						boolean authory = resultSet.getBoolean("authory");

						// Tabloya yeni satır ekle
						Object[] row = { id, name, surname, username, phoneNumber, salary, prim, authory };
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

		btnDsc.setIcon(new ImageIcon(EmployeeFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16 (1).png")));
		btnDsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDsc.setBounds(986, 5, 200, 25);
		contentPane.add(btnDsc);
	}

	public void list() {

		try {
			Class.forName("org.postgresql.Driver");
			// DB classında olan Connection'a erişim.
			DB db = new DB();
			Connection connection = db.createConnection();

			String sql = "SELECT id, name, surname, username, phonenumber, salary, prim, authory FROM public.tbl_personel ORDER BY id ASC";
			Statement statement = connection.createStatement();

			// Sorguyu Çalıştır
			ResultSet resultSet = statement.executeQuery(sql);

			// JTable modeli
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Tüm Satırları Temizle
			model.setRowCount(0);

			// Sonuçları JTable'a ekle
			while (resultSet.next()) {
				// Tablodaki verileri al
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String username = resultSet.getString("username");
				String phoneNumber = resultSet.getString("phonenumber");
				String salary = resultSet.getString("salary");
				String prim = resultSet.getString("prim");
				boolean authory = resultSet.getBoolean("authory");

				// Tabloya yeni satır ekle
				Object[] row = { id, name, surname, username, phoneNumber, salary, prim, authory };
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
