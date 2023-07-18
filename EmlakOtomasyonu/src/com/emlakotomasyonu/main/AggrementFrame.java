package com.emlakotomasyonu.main;

import java.awt.Color;
import java.awt.Desktop;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class AggrementFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtFolderName;
	private JTextField txtTaraf1;
	private JTextField txtAraci;
	private JTextField txtTaraf2;
	private JTextField txtDate;
	private JTable table;
	private JTextField txtTaraf1TcNo;
	private JTextField txtTaraf2TcNo;
	private JTextField txtAraciTcNo;
	private JFileChooser fileChooser;
	File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AggrementFrame frame = new AggrementFrame();
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
	public AggrementFrame() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-petition-96.png")));
		setTitle("Sözleşme");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"S\u00F6zle\u015Fme Ekle", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 323, 643);
		contentPane.add(panel);

		JLabel lblId = new JLabel("Id :");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 186, 129, 20);
		panel.add(lblId);

		txtId = new JTextField();
		txtId.setToolTipText("");
		txtId.setEnabled(false);
		txtId.setColumns(10);
		txtId.setBounds(149, 188, 160, 20);
		panel.add(txtId);

		JLabel lblDosyaBal = new JLabel("Dosya Adı :");
		lblDosyaBal.setHorizontalAlignment(SwingConstants.LEFT);
		lblDosyaBal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDosyaBal.setBounds(10, 216, 129, 20);
		panel.add(lblDosyaBal);

		txtFolderName = new JTextField();
		txtFolderName.setToolTipText("");
		txtFolderName.setColumns(10);
		txtFolderName.setBounds(149, 218, 160, 20);
		panel.add(txtFolderName);

		JLabel lblTaraf = new JLabel("Taraf 1 Adı :");
		lblTaraf.setHorizontalAlignment(SwingConstants.LEFT);
		lblTaraf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaraf.setBounds(10, 246, 129, 20);
		panel.add(lblTaraf);

		JLabel lblTaraf_1 = new JLabel("Taraf 2 Adı :");
		lblTaraf_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTaraf_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaraf_1.setBounds(10, 306, 129, 20);
		panel.add(lblTaraf_1);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setLayout(null);
		panelPhoto.setBounds(113, 30, 100, 100);
		panel.add(panelPhoto);

		JLabel lblFolder = new JLabel("");
		lblFolder.setToolTipText("Dosyayı Görüntülemek İçin Tıklayınız.");

		lblFolder.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-document-96.png")));
		lblFolder.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolder.setBounds(0, 0, 100, 100);
		panelPhoto.add(lblFolder);

		JButton btnKaydet = new JButton("KAYDET");

		btnKaydet.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-save-32.png")));
		btnKaydet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnKaydet.setBounds(6, 458, 303, 36);
		panel.add(btnKaydet);

		JButton btnGncelle = new JButton("GÜNCELLE");

		btnGncelle.setIcon(new ImageIcon(
				AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));
		btnGncelle.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGncelle.setBounds(6, 550, 303, 36);
		panel.add(btnGncelle);

		JButton btnSil = new JButton("SİL");

		btnSil.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));
		btnSil.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSil.setBounds(6, 596, 303, 36);
		panel.add(btnSil);

		txtTaraf1 = new JTextField();
		txtTaraf1.setToolTipText("");
		txtTaraf1.setColumns(10);
		txtTaraf1.setBounds(149, 248, 160, 20);
		panel.add(txtTaraf1);

		JButton btnListele = new JButton("LİSTELE");

		btnListele.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		btnListele.setFont(new Font("Verdana", Font.BOLD, 12));
		btnListele.setBounds(6, 504, 303, 36);
		panel.add(btnListele);

		JLabel lblArac = new JLabel("Aracı Adı :");
		lblArac.setHorizontalAlignment(SwingConstants.LEFT);
		lblArac.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblArac.setBounds(10, 366, 129, 20);
		panel.add(lblArac);

		txtAraci = new JTextField();
		txtAraci.setToolTipText("");
		txtAraci.setColumns(10);
		txtAraci.setBounds(149, 368, 160, 20);
		panel.add(txtAraci);

		txtTaraf2 = new JTextField();
		txtTaraf2.setToolTipText("");
		txtTaraf2.setColumns(10);
		txtTaraf2.setBounds(149, 308, 160, 20);
		panel.add(txtTaraf2);

		JLabel lblTarih = new JLabel("Tarih :");

		lblTarih.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-today-20.png")));
		lblTarih.setHorizontalAlignment(SwingConstants.LEFT);
		lblTarih.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTarih.setBounds(10, 426, 129, 20);
		panel.add(lblTarih);

		txtDate = new JTextField();
		txtDate.setToolTipText("");
		txtDate.setColumns(10);
		txtDate.setBounds(149, 428, 160, 20);
		panel.add(txtDate);

		JLabel lblTarafTc = new JLabel("Taraf 1  TC NO:");
		lblTarafTc.setHorizontalAlignment(SwingConstants.LEFT);
		lblTarafTc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTarafTc.setBounds(10, 276, 129, 20);
		panel.add(lblTarafTc);

		txtTaraf1TcNo = new JTextField();
		txtTaraf1TcNo.setToolTipText("");
		txtTaraf1TcNo.setColumns(10);
		txtTaraf1TcNo.setBounds(149, 278, 160, 20);
		panel.add(txtTaraf1TcNo);

		JLabel lblTaraf_1_1 = new JLabel("Taraf 2 TC NO :");
		lblTaraf_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTaraf_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTaraf_1_1.setBounds(10, 336, 129, 20);
		panel.add(lblTaraf_1_1);

		txtTaraf2TcNo = new JTextField();
		txtTaraf2TcNo.setToolTipText("");
		txtTaraf2TcNo.setColumns(10);
		txtTaraf2TcNo.setBounds(149, 338, 160, 20);
		panel.add(txtTaraf2TcNo);

		JLabel lblAracTcNo = new JLabel("Aracı TC NO :");
		lblAracTcNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblAracTcNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAracTcNo.setBounds(10, 394, 129, 20);
		panel.add(lblAracTcNo);

		txtAraciTcNo = new JTextField();
		txtAraciTcNo.setToolTipText("");
		txtAraciTcNo.setColumns(10);
		txtAraciTcNo.setBounds(149, 396, 160, 20);
		panel.add(txtAraciTcNo);

		JButton btnChooseFile = new JButton("Dosya Seç");

		btnChooseFile.setIcon(
				new ImageIcon(AggrementFrame.class.getResource("/com/emlakotomasyonu/images/icons8-add-file-32.png")));
		btnChooseFile.setFont(new Font("Verdana", Font.BOLD, 12));
		btnChooseFile.setBounds(9, 135, 303, 36);
		panel.add(btnChooseFile);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(343, 43, 733, 610);
		contentPane.add(scrollPane);

		table = new JTable();

		// Islemler

		// Dosya Seç
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					lblFolder.setText("Seçilen Dosya: " + selectedFile.getAbsolutePath());
				}
			}
		});

		// Oto Tarih
		lblTarih.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sistem tarihini al
				LocalDate currentDate = LocalDate.now();

				// Tarihi string'e dönüştür
				String dateString = currentDate.toString();

				// Text field'a yazdır
				txtDate.setText(dateString);
			}
		});

		// Frame Load
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				list();
			}
		});

		// Tabloda seçilen veriyi fieldlara koy
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				// Seçilen satırdaki veriyi al
				int id = (int) table.getValueAt(selectedRow, 0);

				try {
					Class.forName("org.postgresql.Driver");
					DB db = new DB();
					Connection connection = db.createConnection();

					// SQL sorgusu
					String sql = "SELECT id, foldername, taraf1, taraf1tcno, taraf2, taraf2tcno, araci, aracitcno, date FROM public.tbl_aggrement WHERE id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, id);

					// Sorguyu çalıştır
					ResultSet resultSet = statement.executeQuery();

					// Verileri TextFieldlara aktar
					if (resultSet.next()) {
						String foldername = resultSet.getString("foldername");
						String taraf1 = resultSet.getString("taraf1");
						String taraf1tc = resultSet.getString("taraf1tcno");
						String taraf2 = resultSet.getString("taraf2");
						String taraf2tc = resultSet.getString("taraf2tcno");
						String araci = resultSet.getString("araci");
						String aracitc = resultSet.getString("aracitcno");
						String date = resultSet.getString("date");

						txtId.setText(String.valueOf(id));
						txtFolderName.setText(foldername);
						txtTaraf1.setText(taraf1);
						txtTaraf1TcNo.setText(taraf1tc);
						txtTaraf2.setText(taraf2);
						txtTaraf2TcNo.setText(taraf2tc);
						txtAraci.setText(araci);
						txtAraciTcNo.setText(aracitc);
						txtDate.setText(date);
					}

					// Kaynakları serbest bırak
					resultSet.close();
					statement.close();
					connection.close();

				} catch (ClassNotFoundException | SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		// Kaydet Button
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folderName = txtFolderName.getText();
				String taraf1 = txtTaraf1.getText();
				String taraf1tc = txtTaraf1TcNo.getText();
				String taraf2 = txtTaraf2.getText();
				String taraf2tc = txtTaraf2TcNo.getText();
				String araci = txtAraci.getText();
				String aracitc = txtAraciTcNo.getText();
				String date = txtDate.getText();

				try {
					Class.forName("org.postgresql.Driver");

					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();

					// File İşlemleri
					FileInputStream fis = new FileInputStream(selectedFile);

					// Sql String
					String sql = "INSERT INTO public.tbl_aggrement(foldername, taraf1, taraf1tcno, taraf2, taraf2tcno, araci, aracitcno, date, folder) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = db.createConnection().prepareStatement(sql);

					statement.setString(1, folderName);
					statement.setString(2, taraf1);
					statement.setString(3, taraf1tc);
					statement.setString(4, taraf2);
					statement.setString(5, taraf2tc);
					statement.setString(6, araci);
					statement.setString(7, aracitc);
					statement.setString(8, date);
					statement.setBinaryStream(9, fis, (int) selectedFile.length());

					// Sorguyu Çalıştır
					int rowsAffected = statement.executeUpdate();
					statement.close();
					fis.close();

					// Kayıt Başarılı İse
					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Kayıt Başarılı.");
						clear();
						list();
					} else {
						JOptionPane.showMessageDialog(null, "Kayıt Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "HATA! VERITABANI HATASI!", "HATA!", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				} catch (FileNotFoundException e3) {
					JOptionPane.showMessageDialog(null, "HATA! Dosya/Fotoğraf Bulunamadı!", "HATA!",
							JOptionPane.ERROR_MESSAGE);
					e3.printStackTrace();
				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, "HATA! Bilinmeyen Hata!", "HATA!", JOptionPane.ERROR_MESSAGE);
					e4.printStackTrace();
				}
			}
		});

		// Listele
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
					JOptionPane.showMessageDialog(AggrementFrame.this, "Lütfen güncellemek istediğiniz satırı seçin.",
							"Hata! Satır Seçilmedi.", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String folderName = txtFolderName.getText();
				String taraf1 = txtTaraf1.getText();
				String taraf1tc = txtTaraf1TcNo.getText();
				String taraf2 = txtTaraf2.getText();
				String taraf2tc = txtTaraf2TcNo.getText();
				String araci = txtAraci.getText();
				String aracitc = txtAraciTcNo.getText();
				String date = txtDate.getText();

				try {
					Class.forName("org.postgresql.Driver");

					FileInputStream fis = null;

					DB db = new DB();
					Connection connection = db.createConnection();

					// Dosya ekleme işlemi
					if (selectedFile != null) {
						fis = new FileInputStream(selectedFile);

					} else {
						JOptionPane.showMessageDialog(null, "Dosya Seçilmedi!", "HATA!", JOptionPane.ERROR_MESSAGE);
					}

					PreparedStatement statement = connection.prepareStatement(
							"UPDATE public.tbl_aggrement SET foldername = '" + folderName + "', taraf1 = '" + taraf1
									+ "', taraf1tcno = '" + taraf1tc + "', taraf2 = '" + taraf2 + "', taraf2tcno = '"
									+ taraf2tc + "', araci = '" + araci + "', aracitcno = '" + aracitc + "', date = '"
									+ date + "', folder = '" + fis + "' WHERE id = '" + selectedRow + "'");

					int rowCount = statement.executeUpdate();

					if (rowCount > 0) {
						JOptionPane.showMessageDialog(AggrementFrame.this, "Kayıt başarıyla güncellendi.", "Başarılı",
								JOptionPane.INFORMATION_MESSAGE);
						clear();
					} else {
						JOptionPane.showMessageDialog(AggrementFrame.this, "Kayıt güncellenirken bir hata oluştu.",
								"Hata", JOptionPane.ERROR_MESSAGE);
					}

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
					System.out.println("DOSYA HATASI \n");
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
					JOptionPane.showMessageDialog(AggrementFrame.this, "Lütfen silmek istediğiniz satırı seçin.",
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
						Connection connection = db.createConnection();
						Statement statement = connection.createStatement();

						int id = (int) table.getValueAt(selectedRow, 0);
						String sql = "DELETE FROM public.tbl_aggrement WHERE id = " + id;

						// Sorguyu Çalıştır
						int rowCount = statement.executeUpdate(sql);

						// Kayıt başarılı bir şekilde silindi ise
						if (rowCount > 0) {
							JOptionPane.showMessageDialog(AggrementFrame.this, "Kayıt başarıyla silindi.", "Başarılı",
									JOptionPane.INFORMATION_MESSAGE);
							clear();
							list();
						} else {
							JOptionPane.showMessageDialog(AggrementFrame.this, "Kayıt silinirken bir hata oluştu.",
									"Hata", JOptionPane.ERROR_MESSAGE);
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

				} else {
					return;
				}
			}

		});

		// Dosya İçeriğini Görüntüle
		lblFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				// Seçilen satırdaki veriyi al
				int id = (int) table.getValueAt(selectedRow, 0);

				try {
					Class.forName("org.postgresql.Driver");
					DB db = new DB();
					Connection connection = db.createConnection();

					// SQL sorgusu
					String sql = "SELECT id, foldername, taraf1, taraf1tcno, taraf2, taraf2tcno, araci, aracitcno, date, folder FROM public.tbl_aggrement WHERE id = ?";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, id);

					// Sorguyu çalıştır
					ResultSet resultSet = statement.executeQuery();

					// Verileri TextFieldlara aktar
					if (resultSet.next()) {
						String foldername = resultSet.getString("foldername");
						String taraf1 = resultSet.getString("taraf1");
						String taraf1tc = resultSet.getString("taraf1tcno");
						String taraf2 = resultSet.getString("taraf2");
						String taraf2tc = resultSet.getString("taraf2tcno");
						String araci = resultSet.getString("araci");
						String aracitc = resultSet.getString("aracitcno");
						String date = resultSet.getString("date");

						txtId.setText(String.valueOf(id));
						txtFolderName.setText(foldername);
						txtTaraf1.setText(taraf1);
						txtTaraf1TcNo.setText(taraf1tc);
						txtTaraf2.setText(taraf2);
						txtTaraf2TcNo.setText(taraf2tc);
						txtAraci.setText(araci);
						txtAraciTcNo.setText(aracitc);
						txtDate.setText(date);

						// Dosyayı görüntüleyici ile açma
						byte[] fileData = resultSet.getBytes("folder");
						if (fileData != null) {
							File tempFile = File.createTempFile("temp", null);
							FileOutputStream fos = new FileOutputStream(tempFile);
							fos.write(fileData);
							fos.close();
							Desktop.getDesktop().open(tempFile);
						}
					}

					// Kaynakları serbest bırak
					resultSet.close();
					statement.close();
					connection.close();

				} catch (ClassNotFoundException | SQLException | IOException ex) {
					ex.printStackTrace();
				}

			}
		});

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Dosya Adı", "Taraf 1", "Taraf 2", "Aracı", "Tarih" }));
		scrollPane.setViewportView(table);

		JButton btnAsc = new JButton("Sırala");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		btnAsc.setIcon(new ImageIcon(AggrementFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16.png")));
		btnAsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAsc.setBounds(666, 12, 200, 25);
		contentPane.add(btnAsc);

		JButton btnDsc = new JButton("Tersten Sırala");
		btnDsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, foldername, taraf1, taraf1tcno, taraf2, taraf2tcno, araci, aracitcno, date, folder FROM public.tbl_aggrement order by id desc;";
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
						String folderName = resultSet.getString("foldername");
						String taraf1 = resultSet.getString("taraf1");
						String taraf1tc = resultSet.getString("taraf1tcno");
						String taraf2 = resultSet.getString("taraf2");
						String taraf2tc = resultSet.getString("taraf2tcno");
						String araci = resultSet.getString("araci");
						String aracitc = resultSet.getString("aracitcno");
						String date = resultSet.getString("date");

						Object[] row = { id, folderName, taraf1, taraf1tc, taraf2, taraf2tc, araci, aracitc, date };
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
		btnDsc.setIcon(new ImageIcon(AggrementFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16 (1).png")));
		btnDsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDsc.setBounds(876, 10, 200, 25);
		contentPane.add(btnDsc);
	}
	
	public void clear() {
		txtAraci.setText("");
		txtAraciTcNo.setText("");
		txtDate.setText("");
		txtFolderName.setText("");
		txtId.setText("");
		txtTaraf1.setText("");
		txtTaraf1TcNo.setText("");
		txtTaraf2.setText("");
		txtTaraf2TcNo.setText("");
	}

	public void list() {
		try {
			Class.forName("org.postgresql.Driver");
			// DB classında olan Connection'a erişim.
			DB db = new DB();
			Connection connection = db.createConnection();

			String sql = "SELECT id, foldername, taraf1, taraf1tcno, taraf2, taraf2tcno, araci, aracitcno, date, folder FROM public.tbl_aggrement ORDER BY id ASC;";
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
				String folderName = resultSet.getString("foldername");
				String taraf1 = resultSet.getString("taraf1");
				String taraf1tc = resultSet.getString("taraf1tcno");
				String taraf2 = resultSet.getString("taraf2");
				String taraf2tc = resultSet.getString("taraf2tcno");
				String araci = resultSet.getString("araci");
				String aracitc = resultSet.getString("aracitcno");
				String date = resultSet.getString("date");

				Object[] row = { id, folderName, taraf1, taraf1tc, taraf2, taraf2tc, araci, aracitc, date };
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
