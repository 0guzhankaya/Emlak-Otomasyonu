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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AccountingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSalesRevenue;
	private JTextField txtHireRevenue;
	private JTextField txtCompanyExpense;
	private JTextField txtEmployeExp;
	private JTextField txtTax;
	private JTextField txtResult;
	private JTable table;
	File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountingFrame frame = new AccountingFrame();
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
	public AccountingFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				AccountingFrame.class.getResource("/com/emlakotomasyonu/images/icons8-us-dollar-circled-96.png")));
		setTitle("Muhasebe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Gelir - Gider Hesaplamas\u0131", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 323, 530);
		contentPane.add(panel);

		JLabel lblSatGelirleri = new JLabel("Satış Gelirleri :");
		lblSatGelirleri.setHorizontalAlignment(SwingConstants.LEFT);
		lblSatGelirleri.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSatGelirleri.setBounds(10, 82, 129, 20);
		panel.add(lblSatGelirleri);

		txtSalesRevenue = new JTextField();
		txtSalesRevenue.setToolTipText("Adınızı Giriniz.");
		txtSalesRevenue.setColumns(10);
		txtSalesRevenue.setBounds(149, 84, 160, 20);
		panel.add(txtSalesRevenue);

		JLabel lblKiraGelirleri = new JLabel("Kira Gelirleri :");
		lblKiraGelirleri.setHorizontalAlignment(SwingConstants.LEFT);
		lblKiraGelirleri.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKiraGelirleri.setBounds(10, 112, 129, 20);
		panel.add(lblKiraGelirleri);

		txtHireRevenue = new JTextField();
		txtHireRevenue.setToolTipText("Soyadınızı Giriniz.");
		txtHireRevenue.setColumns(10);
		txtHireRevenue.setBounds(149, 114, 160, 20);
		panel.add(txtHireRevenue);

		JButton btnKaydet = new JButton("KAYDET");

		btnKaydet.setIcon(
				new ImageIcon(AccountingFrame.class.getResource("/com/emlakotomasyonu/images/icons8-save-32.png")));
		btnKaydet.setFont(new Font("Verdana", Font.BOLD, 12));
		btnKaydet.setBounds(10, 346, 303, 36);
		panel.add(btnKaydet);

		JLabel lblAy = new JLabel("Ay :");
		lblAy.setHorizontalAlignment(SwingConstants.LEFT);
		lblAy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAy.setBounds(10, 22, 129, 20);
		panel.add(lblAy);

		JLabel lblYl = new JLabel("Yıl :");
		lblYl.setHorizontalAlignment(SwingConstants.LEFT);
		lblYl.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYl.setBounds(10, 52, 129, 20);
		panel.add(lblYl);

		JLabel lblirketGideri = new JLabel("Şirket Gideri :");
		lblirketGideri.setBounds(10, 142, 129, 20);
		panel.add(lblirketGideri);
		lblirketGideri.setHorizontalAlignment(SwingConstants.LEFT);
		lblirketGideri.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCompanyExpense = new JTextField();
		txtCompanyExpense.setBounds(149, 144, 160, 20);
		panel.add(txtCompanyExpense);
		txtCompanyExpense.setToolTipText("Adınızı Giriniz.");
		txtCompanyExpense.setColumns(10);

		JLabel lblPersonelGideri = new JLabel("Personel Gideri :");
		lblPersonelGideri.setBounds(10, 172, 129, 20);
		panel.add(lblPersonelGideri);
		lblPersonelGideri.setHorizontalAlignment(SwingConstants.LEFT);
		lblPersonelGideri.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtEmployeExp = new JTextField();
		txtEmployeExp.setBounds(149, 174, 160, 20);
		panel.add(txtEmployeExp);
		txtEmployeExp.setToolTipText("Soyadınızı Giriniz.");
		txtEmployeExp.setColumns(10);

		JLabel lblVergiler = new JLabel("Vergiler :");
		lblVergiler.setBounds(10, 202, 129, 20);
		panel.add(lblVergiler);
		lblVergiler.setHorizontalAlignment(SwingConstants.LEFT);
		lblVergiler.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtTax = new JTextField();
		txtTax.setBounds(149, 204, 160, 20);
		panel.add(txtTax);
		txtTax.setToolTipText("Soyadınızı Giriniz.");
		txtTax.setColumns(10);

		JLabel lblVergiler_1_2 = new JLabel("Sonuç :");
		lblVergiler_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblVergiler_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVergiler_1_2.setBounds(10, 232, 129, 20);
		panel.add(lblVergiler_1_2);

		txtResult = new JTextField();
		txtResult.setEnabled(false);
		txtResult.setToolTipText("Soyadınızı Giriniz.");
		txtResult.setColumns(10);
		txtResult.setBounds(149, 234, 160, 20);
		panel.add(txtResult);

		JComboBox cbxMonth = new JComboBox();
		cbxMonth.setModel(new DefaultComboBoxModel(new String[] { "", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs",
				"Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık" }));
		cbxMonth.setToolTipText("Müşterinin, şirkete kayıtlı mal varlığı var ise seçiniz.");
		cbxMonth.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbxMonth.setBackground(Color.WHITE);
		cbxMonth.setBounds(149, 23, 160, 20);
		panel.add(cbxMonth);

		JComboBox cbxYear = new JComboBox();
		cbxYear.setModel(new DefaultComboBoxModel(new String[] { "", "2023", "2024", "2025", "2026", "2027", "2028",
				"2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040" }));
		cbxYear.setToolTipText("Müşterinin, şirkete kayıtlı mal varlığı var ise seçiniz.");
		cbxYear.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbxYear.setBackground(Color.WHITE);
		cbxYear.setBounds(149, 53, 160, 20);
		panel.add(cbxYear);

		JButton btnList = new JButton("LİSTELE");

		btnList.setIcon(
				new ImageIcon(AccountingFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		btnList.setFont(new Font("Verdana", Font.BOLD, 12));
		btnList.setBounds(10, 392, 303, 36);
		panel.add(btnList);

		JButton btnGncelle = new JButton("GÜNCELLE");
		btnGncelle.setEnabled(false);
		btnGncelle.setIcon(new ImageIcon(
				AccountingFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));
		btnGncelle.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGncelle.setBounds(10, 438, 303, 36);
		panel.add(btnGncelle);

		JButton btnSil = new JButton("SİL");
		btnSil.setEnabled(false);
		btnSil.setIcon(
				new ImageIcon(AccountingFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));
		btnSil.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSil.setBounds(10, 484, 303, 36);
		panel.add(btnSil);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(343, 42, 680, 498);
		contentPane.add(scrollPane);

		table = new JTable();

		// Islemler

		// Kaydet Button
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String month = (String) cbxMonth.getSelectedItem();
				String year = (String) cbxYear.getSelectedItem();
				double salesRevenue = Double.parseDouble(txtSalesRevenue.getText());
				double hiresRevenue = Double.parseDouble(txtHireRevenue.getText());
				double companyExpense = Double.parseDouble(txtCompanyExpense.getText());
				double employeExpense = Double.parseDouble(txtCompanyExpense.getText());
				double tax = Double.parseDouble(txtTax.getText());

				// Gelir-Gider Hesaplaması
				double result = ((salesRevenue + hiresRevenue) - (companyExpense + employeExpense + tax));

				// String.valueOf()
				txtResult.setText(String.valueOf(result));

				try {
					Class.forName("org.postgresql.Driver");

					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();

					// Sql String
					String sql = "insert into public.tbl_acc(month,year,salesrevenue,hiresrevenue,companyexpense,employeexpense,tax,result) values"
							+ "(?,?,?,?,?,?,?,?);";

					PreparedStatement statement = db.createConnection().prepareStatement(sql);
					statement.setString(1, month);
					statement.setString(2, year);
					statement.setDouble(3, salesRevenue);
					statement.setDouble(4, hiresRevenue);
					statement.setDouble(5, companyExpense);
					statement.setDouble(6, employeExpense);
					statement.setDouble(7, tax);
					statement.setDouble(8, result);

					// Sorguyu Çalıştır
					statement.execute();
					statement.close();

					// Kayıt Başarılı İse
					if (statement.execute() == true) {
						JOptionPane.showMessageDialog(null, "Kayıt Başarılı.");
						
						// Fieldları Sıfırla
						cbxMonth.setSelectedIndex(0);
						cbxYear.setSelectedIndex(0);
						txtCompanyExpense.setText("");
						txtEmployeExp.setText("");
						txtHireRevenue.setText("");
						txtResult.setText("");
						txtSalesRevenue.setText("");
						txtTax.setText("");
						
						list();

					} else {
						JOptionPane.showMessageDialog(null, "Kayıt Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
					}

				} catch (ClassNotFoundException e1) {
					System.out.println("HATA! \n");
					e1.printStackTrace();
				} catch (SQLException e2) {
					System.out.println("SQL HATASI \n");
					e2.printStackTrace();
				}
			}
		});

		// Table Getir
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				// Seçilen satırdaki verileri al
				String id = table.getModel().getValueAt(selectedRow, 0).toString();
				String month = table.getModel().getValueAt(selectedRow, 1).toString();
				String year = table.getModel().getValueAt(selectedRow, 2).toString();
				String sales = table.getModel().getValueAt(selectedRow, 3).toString();
				String hires = table.getModel().getValueAt(selectedRow, 4).toString();
				String company = table.getModel().getValueAt(selectedRow, 5).toString();
				String employee = table.getModel().getValueAt(selectedRow, 6).toString();
				String tax = table.getModel().getValueAt(selectedRow, 7).toString();
				String result = table.getModel().getValueAt(selectedRow, 8).toString();

				// Verileri textfield'lara aktar.
				if (selectedRow > -1) {
					cbxMonth.setSelectedItem(month);
					cbxYear.setSelectedItem(year);
					txtSalesRevenue.setText(sales);
					txtHireRevenue.setText(hires);
					txtCompanyExpense.setText(company);
					txtEmployeExp.setText(employee);
					txtTax.setText(tax);
					txtResult.setText(result);
				}
			}
		});

		// Table Load
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//Yetkilendirme İşlemi Olacak - Güncelle ve Sil işlemlerini sadece admin yapabilecek.

				boolean authory = false;

				try {
					Class.forName("org.postgresql.Driver");
					DB db = new DB();
					Connection connection = db.createConnection();

					// Kullanıcı adını alma
					String username = ApplicationFrame.lblName.getText();

					String sql = "SELECT authory FROM public.tbl_personel WHERE username = '" + username + "';";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					if (resultSet.next()) {
						authory = resultSet.getBoolean("authory");

						if (authory == true) {
							btnGncelle.setEnabled(true);
							btnSil.setEnabled(true);
						} else if (authory == false) {
							JOptionPane.showMessageDialog(null,
									"Erişim Yetkiniz Kısıtlıdır. İşlemlerinizde Dikkatli Olunuz!",
									"UYARI! Erişim Kısıtlaması.", JOptionPane.WARNING_MESSAGE);
							list();
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}

					resultSet.close();
					statement.close();
					connection.close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				list();
			}
		});

		// Listele Button
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		// Güncelle Button
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(AccountingFrame.this, "Lütfen güncellemek istediğiniz satırı seçin.",
							"Hata! Satır Seçilmedi.", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String month = (String) cbxMonth.getSelectedItem();
				String year = (String) cbxYear.getSelectedItem();
				Double salesRevenue = Double.parseDouble(txtSalesRevenue.getText());
				Double hiresRevenue = Double.parseDouble(txtHireRevenue.getText());
				Double companyExpense = Double.parseDouble(txtCompanyExpense.getText());
				Double employeExpense = Double.parseDouble(txtEmployeExp.getText());
				Double tax = Double.parseDouble(txtTax.getText());
				Double result = Double.parseDouble(txtResult.getText());

				// Veritabanındaki bilgileri güncelle

				try {
					DB db = new DB();
					Connection connection = db.createConnection();

					// Seçilen satırdaki ID'yi al
					int id = (int) table.getValueAt(selectedRow, 0);

					// Sql String
					String sql = "UPDATE public.tbl_acc SET month = ?, year = ?, salesrevenue = ?, hiresrevenue = ?, companyexpense = ?, employeexpense = ?, tax = ?, result = ? WHERE id = ?;";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1, month);
					statement.setString(2, year);
					statement.setDouble(3, salesRevenue);
					statement.setDouble(4, hiresRevenue);
					statement.setDouble(5, companyExpense);
					statement.setDouble(6, employeExpense);
					statement.setDouble(7, tax);
					statement.setDouble(8, result);
					statement.setInt(9, id);

					// Sorguyu Çalıştır
					int res = statement.executeUpdate();

					if (res >= 0) {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarılı.");

						// Fieldları Sıfırla
						cbxMonth.setSelectedIndex(0);
						cbxYear.setSelectedIndex(0);
						txtCompanyExpense.setText("");
						txtEmployeExp.setText("");
						txtHireRevenue.setText("");
						txtResult.setText("");
						txtSalesRevenue.setText("");
						txtTax.setText("");

						// JTable Yenile
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
				} catch (Exception e3) {
					System.out.println("HATA");
					e3.printStackTrace();
				}
			}
		});

		// Sil Button
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// table'dan satır seç
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					// Eğer bir satır seçilmediyse kullanıcıyı uyar
					JOptionPane.showMessageDialog(AccountingFrame.this, "Lütfen silmek istediğiniz satırı seçin.",
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
						String sql = "DELETE FROM public.tbl_acc WHERE id = ?";

						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, id);

						// Sorguyu Çalıştır
						int rowsAffected = preparedStatement.executeUpdate();

						preparedStatement.close();
						connection.close();

						// Kayıt Başarılı İse
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı.");

							// Fieldları Sıfırla
							cbxMonth.setSelectedIndex(0);
							cbxYear.setSelectedIndex(0);
							txtCompanyExpense.setText("");
							txtEmployeExp.setText("");
							txtHireRevenue.setText("");
							txtResult.setText("");
							txtSalesRevenue.setText("");
							txtTax.setText("");

							// Table'ı yenile
							list();

						} else {
							JOptionPane.showMessageDialog(null, "Silme İşlemi Başarısız!", "HATA!",
									JOptionPane.ERROR_MESSAGE);
						}

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

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Ay", "Yıl", "Satış Geliri",
				"Kira Geliri", "Şirket Gideri", "Personel Gideri", "Vergiler", "Sonuç" }));
		scrollPane.setViewportView(table);

		JButton btnAsc = new JButton("Sırala");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, month, year, salesrevenue, hiresrevenue, companyexpense, employeexpense, tax, result FROM public.tbl_acc order by id asc";
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
						String month = resultSet.getString("month");
						String year = resultSet.getString("year");
						String sales = resultSet.getString("salesrevenue");
						String hires = resultSet.getString("hiresrevenue");
						String company = resultSet.getString("companyexpense");
						String employee = resultSet.getString("employeexpense");
						String tax = resultSet.getString("tax");
						String result = resultSet.getString("result");

						Object[] row = { id, month, year, sales, hires, company, employee, tax, result };
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
		btnAsc.setIcon(new ImageIcon(AccountingFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16.png")));
		btnAsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAsc.setBounds(613, 12, 200, 25);
		contentPane.add(btnAsc);

		JButton btnDsc = new JButton("Tersten Sırala");
		btnDsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		btnDsc.setIcon(new ImageIcon(AccountingFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16 (1).png")));
		btnDsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDsc.setBounds(823, 10, 200, 25);
		contentPane.add(btnDsc);
	}

	public void list() {
		try {
			Class.forName("org.postgresql.Driver");
			// DB classında olan Connection'a erişim.
			DB db = new DB();
			Connection connection = db.createConnection();

			String sql = "SELECT id, month, year, salesrevenue, hiresrevenue, companyexpense, employeexpense, tax, result FROM public.tbl_acc order by id desc";
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
				String month = resultSet.getString("month");
				String year = resultSet.getString("year");
				String sales = resultSet.getString("salesrevenue");
				String hires = resultSet.getString("hiresrevenue");
				String company = resultSet.getString("companyexpense");
				String employee = resultSet.getString("employeexpense");
				String tax = resultSet.getString("tax");
				String result = resultSet.getString("result");

				Object[] row = { id, month, year, sales, hires, company, employee, tax, result };
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
