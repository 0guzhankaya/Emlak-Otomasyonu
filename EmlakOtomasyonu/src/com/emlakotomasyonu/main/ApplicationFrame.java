package com.emlakotomasyonu.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.emlakotomasyonu.database.DB;

public class ApplicationFrame extends JFrame {

	static ApplicationFrame frame;
	private JPanel contentPane;
	private JTextField txtSearch;
	static JDesktopPane desktopPane = new JDesktopPane();
	static JPanel panelDesign;
	static JLabel lblName = new JLabel("isim");

	/**
	 * Create the frame.
	 */
	public ApplicationFrame() {

		setFont(new Font("Tahoma", Font.BOLD, 14));
		setTitle("Emlak Otomasyonu - Anasayfa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Home.512.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Dosya");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Düzen");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_3 = new JMenu("Kes");
		mnNewMenu_3.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-cut-16.png")));
		mnNewMenu_1.add(mnNewMenu_3);

		JMenu mnNewMenu_4 = new JMenu("Kopyala");
		mnNewMenu_4.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-copy-16.png")));
		mnNewMenu_1.add(mnNewMenu_4);

		JMenu mnNewMenu_5 = new JMenu("Yapıştır");
		mnNewMenu_5.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-paste-16.png")));
		mnNewMenu_1.add(mnNewMenu_5);

		JMenu mnNewMenu_2 = new JMenu("Yardım");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1920, 50);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		// Arama Çubuğu
		txtSearch = new JTextField();
		txtSearch.setToolTipText("Aramak İstediğiniz Kelimeyi Yazınız.");
		txtSearch.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSearch.setBounds(1033, 10, 410, 30);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("");

		btnSearch.setForeground(Color.WHITE);
		btnSearch.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-search-20.png")));
		btnSearch.setBounds(1453, 10, 30, 30);
		panel.add(btnSearch);

		JButton btnSearchClear = new JButton("");

		btnSearchClear.setForeground(Color.WHITE);
		btnSearchClear.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-clear-search-20.png")));
		btnSearchClear.setBounds(1493, 10, 30, 30);
		panel.add(btnSearchClear);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBorderPainted(false);
		menuBar_1.setBackground(Color.WHITE);
		menuBar_1.setBounds(0, 0, 1023, 50);
		panel.add(menuBar_1);

		JMenu mnGayrimenkulMenu = new JMenu("Gayrimenkul");

		mnGayrimenkulMenu.setIcon(new ImageIcon(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Home-Pool-Garden.32.png")));
		mnGayrimenkulMenu.setFont(new Font("Verdana", Font.PLAIN, 16));
		menuBar_1.add(mnGayrimenkulMenu);

		JMenuItem mnGayrimenkulAddMenu = new JMenuItem("Gayrimenkul");

		mnGayrimenkulAddMenu.setIcon(new ImageIcon(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Home-Pool-Garden.32.png")));
		mnGayrimenkulAddMenu.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnGayrimenkulMenu.add(mnGayrimenkulAddMenu);

		JMenu mnNewMenu_7 = new JMenu("Kira Takibi");
		mnNewMenu_7.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-cash-in-hand-32.png")));
		mnNewMenu_7.setFont(new Font("Verdana", Font.PLAIN, 16));
		menuBar_1.add(mnNewMenu_7);

		JMenuItem mnıtmNewMenuItem_3 = new JMenuItem("Ekle");
		mnıtmNewMenuItem_3.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-add-24.png")));
		mnıtmNewMenuItem_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnNewMenu_7.add(mnıtmNewMenuItem_3);

		JMenuItem mnıtmNewMenuItem_1_1 = new JMenuItem("Listele");
		mnıtmNewMenuItem_1_1.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		mnıtmNewMenuItem_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnNewMenu_7.add(mnıtmNewMenuItem_1_1);

		JMenu mnNewMenu_8 = new JMenu("Müşteri");

		mnNewMenu_8.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-budget-32.png")));
		mnNewMenu_8.setFont(new Font("Verdana", Font.PLAIN, 16));
		menuBar_1.add(mnNewMenu_8);

		JMenuItem mnMusteri = new JMenuItem("Müşteri");

		mnMusteri.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-budget-32.png")));
		mnMusteri.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnNewMenu_8.add(mnMusteri);

		JMenuItem mnAggrement = new JMenuItem("Sözleşme");

		mnAggrement.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-petition-32.png")));
		mnAggrement.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnNewMenu_8.add(mnAggrement);

		JMenu mnNewMenu_9 = new JMenu("Muhasebe");
		mnNewMenu_9.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-us-dollar-circled-32.png")));
		mnNewMenu_9.setFont(new Font("Verdana", Font.PLAIN, 16));
		menuBar_1.add(mnNewMenu_9);

		JMenuItem mnMuhasebe = new JMenuItem("Muhasebe");

		mnMuhasebe.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnMuhasebe.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-us-dollar-circled-32.png")));
		mnNewMenu_9.add(mnMuhasebe);

		JMenu mnNewMenu_10 = new JMenu("Personel");
		mnNewMenu_10.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-account-32.png")));
		mnNewMenu_10.setFont(new Font("Verdana", Font.PLAIN, 16));
		menuBar_1.add(mnNewMenu_10);

		JMenuItem mnPersonel = new JMenuItem("Personel");

		mnPersonel.setIcon(
				new ImageIcon(ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-account-32.png")));
		mnPersonel.setFont(new Font("Verdana", Font.PLAIN, 14));
		mnNewMenu_10.add(mnPersonel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 49, 200, 774);
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblName.setBounds(93, 80, 97, 30);
		panel_1.add(lblName);

		JLabel lblNewLabel_4_2 = new JLabel("Hoş Geldiniz");
		lblNewLabel_4_2.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel_4_2.setBounds(10, 80, 83, 30);
		panel_1.add(lblNewLabel_4_2);

		JButton btnExit = new JButton("ÇIKIŞ");

		btnExit.setIcon(new ImageIcon(
				ApplicationFrame.class.getResource("/com/emlakotomasyonu/images/icons8-shutdown-32.png")));
		btnExit.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(10, 675, 180, 30);
		panel_1.add(btnExit);

		// DesktopPane
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(200, 49, 1330, 730);
		contentPane.add(desktopPane);

		// Anasayfadaki görselleri içinde tutan panel
		panelDesign = new JPanel();
		panelDesign.setBackground(Color.WHITE);
		panelDesign.setBounds(146, 162, 1038, 406);
		desktopPane.add(panelDesign);
		panelDesign.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Market-Hall.256.png")));
		lblNewLabel.setBounds(0, 59, 256, 256);
		panelDesign.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Home.256.png")));
		lblNewLabel_1.setBounds(396, 59, 256, 256);
		panelDesign.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ApplicationFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Tower-Block-Small.256.png")));
		lblNewLabel_2.setBounds(776, 59, 256, 256);
		panelDesign.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Emlak Otomasyonu");
		lblNewLabel_3.setToolTipText("Emlak Şirketinin İsmi");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_3.setBounds(396, 326, 256, 30);
		panelDesign.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Ana Sayfa");
		lblNewLabel_3_1.setToolTipText("Emlak Şirketinin İsmi");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_3_1.setBounds(396, 369, 256, 30);
		panelDesign.add(lblNewLabel_3_1);

		// İşlemler

		// Çıkış Button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(ApplicationFrame.this,
						"Çıkış Yapmak İstediğinize Emin Misiniz?");
				if (res == 0) {
					// Application Frame'i kapatır.
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(btnExit);
					frame.dispose();
				}

			}
		});

		// Gayrimenkul Ekle
		mnGayrimenkulAddMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GayrimenkulAddFrame gayrimenkulAddFrame = new GayrimenkulAddFrame();
				gayrimenkulAddFrame.setVisible(true);
			}
		});

		// Müşteri Frame
		mnMusteri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerAddFrame customerFrame = new CustomerAddFrame();
				customerFrame.setVisible(true);
			}
		});

		// Personel Frame
		mnPersonel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * EmployeeFrame employeeFrame = new EmployeeFrame();
				 * employeeFrame.setVisible(true);
				 */

				//Yetkilendirme İşlemi

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
							EmployeeFrame employeeFrame = new EmployeeFrame();
							employeeFrame.setVisible(true);
						} else if (authory == false) {
							JOptionPane.showMessageDialog(null, "Erişim Yetkiniz Bulunmamaktadır!",
									"HATA! Erişim Kısıtlaması.", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});

		// Muhasebe Frame
		mnMuhasebe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountingFrame accountingFrame = new AccountingFrame();
				accountingFrame.setVisible(true);
			}
		});

		// Sözleşme Frame
		mnAggrement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AggrementFrame aggrementFrame = new AggrementFrame();
				aggrementFrame.setVisible(true);
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearch.getText() != null) {
					if (txtSearch.getText().equals("Gayrimenkul") || txtSearch.getText().equals("gayrimenkul")
							|| txtSearch.getText().equals("Gayrimenkul Ekle")
							|| txtSearch.getText().equals("gayrimenkul ekle")
							|| txtSearch.getText().equals("Gayrimenkul ekle")) {
						GayrimenkulAddFrame gayrimenkulAddFrame = new GayrimenkulAddFrame();
						gayrimenkulAddFrame.setVisible(true);
					} else if (txtSearch.getText().equals("Müşteri") || txtSearch.getText().equals("müşteri")
							|| txtSearch.getText().equals("Müşteri Ekle") || txtSearch.getText().equals("müşteri ekle")
							|| txtSearch.getText().equals("Müşteri ekle")) {
						CustomerAddFrame customerFrame = new CustomerAddFrame();
						customerFrame.setVisible(true);
					} else if (txtSearch.getText().equals("Personel") || txtSearch.getText().equals("personel")
							|| txtSearch.getText().equals("Personel Ekle")
							|| txtSearch.getText().equals("personel ekle")
							|| txtSearch.getText().equals("Personel ekle")) {
						EmployeeFrame employeeFrame = new EmployeeFrame();
						employeeFrame.setVisible(true);
					} else if (txtSearch.getText().equals("Muhasebe") || txtSearch.getText().equals("muhasebe")
							|| txtSearch.getText().equals("Muhasebe Ekle")
							|| txtSearch.getText().equals("muhasebe ekle")
							|| txtSearch.getText().equals("Muhasebe ekle")) {
						AccountingFrame accountingFrame = new AccountingFrame();
						accountingFrame.setVisible(true);
					} else if (txtSearch.getText().equals("Sözleşme") || txtSearch.getText().equals("sözleşme")
							|| txtSearch.getText().equals("Sözleşme Ekle")
							|| txtSearch.getText().equals("sözleşme ekle")
							|| txtSearch.getText().equals("Sözleşme ekle")) {
						AggrementFrame aggrementFrame = new AggrementFrame();
						aggrementFrame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Aramak İstediğiniz Kelimeyi Giriniz");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aramak İstediğiniz Kelimeyi Giriniz");
					btnSearchClear.enable(false);
				}
			}
		});

		btnSearchClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
			}
		});

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ApplicationFrame();
					frame.setVisible(true);

					// frame'in tüm ekranı kaplamasını istediğimiz için bu iki değeri set ettik.
					frame.setLocationRelativeTo(null);
					frame.setExtendedState(MAXIMIZED_BOTH);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}