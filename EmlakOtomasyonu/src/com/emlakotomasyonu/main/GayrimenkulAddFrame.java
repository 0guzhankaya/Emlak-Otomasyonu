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
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.emlakotomasyonu.database.DB;

public class GayrimenkulAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtAddDate;
	private JTextField txtPrice;
	private JTextField txtDeposit;
	private JTextField txtRoomNumber;
	private JTextField txtAlan;
	private JTextField txtFloor;
	private JTextField txtSite;
	private JTextField txtSub;
	private JTextField txtDistrict;
	private JTextField txtProvince;
	private JTable table;
	private JTextField txtOwner;
	private JTextField txtFacede;
	File selectedFile;
	private JTextField txtBuildAge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GayrimenkulAddFrame frame = new GayrimenkulAddFrame();
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

	/**
	 * Create the frame.
	 */
	public GayrimenkulAddFrame() {
		setTitle("Gayrimenkul");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GayrimenkulAddFrame.class
				.getResource("/com/emlakotomasyonu/images/Iconarchive-Essential-Buildings-Golf-Club.128.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1920, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Gayrimenkul Bilgileri Ekle", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 10, 330, 760);
		contentPane.add(panel_5);

		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 20, 129, 20);
		panel_5.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setToolTipText("Id");
		txtId.setColumns(10);
		txtId.setBounds(149, 22, 160, 20);
		panel_5.add(txtId);

		JLabel lblGvenlikSorusuCevab = new JLabel("Gayrimenkul Tipi:");
		lblGvenlikSorusuCevab.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab.setBounds(10, 50, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab);

		JLabel lblAddDate = new JLabel("Eklenme Tarihi:");

		lblAddDate.setIcon(new ImageIcon(
				GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-today-20.png")));
		lblAddDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddDate.setBounds(10, 80, 129, 20);
		panel_5.add(lblAddDate);

		txtAddDate = new JTextField();
		txtAddDate.setToolTipText("YYYY-AA-GG");
		txtAddDate.setColumns(10);
		txtAddDate.setBounds(149, 82, 160, 20);
		panel_5.add(txtAddDate);

		JLabel lblNewLabel_6 = new JLabel("Komisyon Oranı:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 110, 129, 20);
		panel_5.add(lblNewLabel_6);

		JLabel lblGvenlikSorusuCevab_3 = new JLabel("Satılık/Kiralık:");
		lblGvenlikSorusuCevab_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_3.setBounds(10, 140, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_3);

		JLabel lblGvenlikSorusuCevab_4 = new JLabel("Sahibi:");
		lblGvenlikSorusuCevab_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_4.setBounds(10, 228, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_4);

		JComboBox comboBoxGayrimenkulType = new JComboBox();
		comboBoxGayrimenkulType.setModel(new DefaultComboBoxModel(new String[] { "", "Konut", "İş Yeri", "Arsa" }));
		comboBoxGayrimenkulType.setBounds(149, 51, 160, 20);
		panel_5.add(comboBoxGayrimenkulType);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(119, 81, 20, 20);
		panel_5.add(lblNewLabel_8);

		JComboBox cbxCommission = new JComboBox();
		cbxCommission.setModel(new DefaultComboBoxModel(new String[] { "", "8", "10", "18", "25" }));
		cbxCommission.setBounds(149, 111, 160, 20);
		panel_5.add(cbxCommission);

		JComboBox cbxSaleHire = new JComboBox();
		cbxSaleHire.setModel(new DefaultComboBoxModel(new String[] { "", "Satılık", "Kiralık" }));
		cbxSaleHire.setBounds(149, 141, 160, 20);
		panel_5.add(cbxSaleHire);

		JLabel lblGvenlikSorusuCevab_6_1_1 = new JLabel("Fiyat:");
		lblGvenlikSorusuCevab_6_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_6_1_1.setBounds(10, 168, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_6_1_1);

		txtPrice = new JTextField();
		txtPrice.setToolTipText("Fiyat");
		txtPrice.setColumns(10);
		txtPrice.setBounds(149, 170, 160, 20);
		panel_5.add(txtPrice);

		JLabel lblGvenlikSorusuCevab_6_1_2 = new JLabel("Depozito:");
		lblGvenlikSorusuCevab_6_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_6_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_6_1_2.setBounds(10, 198, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_6_1_2);

		txtDeposit = new JTextField();
		txtDeposit.setToolTipText("Depozito");
		txtDeposit.setColumns(10);
		txtDeposit.setBounds(149, 200, 160, 20);
		panel_5.add(txtDeposit);

		JLabel lblNewLabel_3_1 = new JLabel("Ev Tipi:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(10, 258, 129, 20);
		panel_5.add(lblNewLabel_3_1);

		JComboBox cbxHouseType = new JComboBox();
		cbxHouseType.setModel(new DefaultComboBoxModel(new String[] { "", "Daire", "Müstakil", "Villa", "Yazlık" }));
		cbxHouseType.setBounds(149, 259, 160, 20);
		panel_5.add(cbxHouseType);

		JLabel lblGvenlikSorusuCevab_5 = new JLabel("Oda Sayısı:");
		lblGvenlikSorusuCevab_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_5.setBounds(10, 288, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_5);

		txtRoomNumber = new JTextField();
		txtRoomNumber.setToolTipText("");
		txtRoomNumber.setColumns(10);
		txtRoomNumber.setBounds(149, 290, 160, 20);
		panel_5.add(txtRoomNumber);

		JLabel lblNewLabel_4_1 = new JLabel("Bina Yaşı:");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(10, 318, 129, 20);
		panel_5.add(lblNewLabel_4_1);

		JLabel lblGvenlikSorusuCevab_1_1 = new JLabel("Cephe:");
		lblGvenlikSorusuCevab_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_1_1.setBounds(10, 348, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_1_1);

		JLabel lblGvenlikSorusuCevab_1 = new JLabel("Alan:");
		lblGvenlikSorusuCevab_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_1.setBounds(10, 378, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_1);

		txtAlan = new JTextField();
		txtAlan.setToolTipText("");
		txtAlan.setColumns(10);
		txtAlan.setBounds(149, 380, 160, 20);
		panel_5.add(txtAlan);

		JLabel lblNewLabel_7_1_1 = new JLabel("Isıtma:");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7_1_1.setBounds(10, 408, 129, 20);
		panel_5.add(lblNewLabel_7_1_1);

		JComboBox cbxHeating = new JComboBox();
		cbxHeating.setModel(new DefaultComboBoxModel(new String[] { "", "Doğalgaz", "Diğer" }));
		cbxHeating.setBounds(149, 410, 160, 20);
		panel_5.add(cbxHeating);

		JLabel lblNewLabel_7_1 = new JLabel("Kat :");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7_1.setBounds(10, 440, 129, 20);
		panel_5.add(lblNewLabel_7_1);

		txtFloor = new JTextField();
		txtFloor.setToolTipText("");
		txtFloor.setColumns(10);
		txtFloor.setBounds(149, 442, 160, 20);
		panel_5.add(txtFloor);

		JLabel lblNewLabel_10_1 = new JLabel("Site:");
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10_1.setBounds(10, 472, 129, 20);
		panel_5.add(lblNewLabel_10_1);

		txtSite = new JTextField();
		txtSite.setToolTipText("");
		txtSite.setColumns(10);
		txtSite.setBounds(149, 474, 160, 20);
		panel_5.add(txtSite);

		JLabel lblNewLabel_6_1 = new JLabel("Aidat:");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(10, 502, 129, 20);
		panel_5.add(lblNewLabel_6_1);

		txtSub = new JTextField();
		txtSub.setToolTipText("");
		txtSub.setColumns(10);
		txtSub.setBounds(149, 504, 160, 20);
		panel_5.add(txtSub);

		JLabel lblNewLabel_5 = new JLabel("Adres:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 532, 129, 20);
		panel_5.add(lblNewLabel_5);

		JTextPane txtAddress = new JTextPane();
		txtAddress.setBounds(149, 534, 160, 50);
		panel_5.add(txtAddress);

		JLabel lblNewLabel_5_1 = new JLabel("Semt:");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(10, 594, 129, 20);
		panel_5.add(lblNewLabel_5_1);

		txtDistrict = new JTextField();
		txtDistrict.setToolTipText("");
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(149, 596, 160, 20);
		panel_5.add(txtDistrict);

		JLabel lblGvenlikSorusuCevab_2_1 = new JLabel("Şehir:");
		lblGvenlikSorusuCevab_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGvenlikSorusuCevab_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGvenlikSorusuCevab_2_1.setBounds(10, 624, 129, 20);
		panel_5.add(lblGvenlikSorusuCevab_2_1);

		txtProvince = new JTextField();
		txtProvince.setToolTipText("");
		txtProvince.setColumns(10);
		txtProvince.setBounds(149, 626, 160, 20);
		panel_5.add(txtProvince);

		JComboBox cbxStatus = new JComboBox();
		cbxStatus.setModel(new DefaultComboBoxModel(new String[] { "", "İlanda", "Satıldı", "Kiralandı" }));
		cbxStatus.setBounds(149, 656, 160, 20);
		panel_5.add(cbxStatus);

		JLabel lblNewLabel_7_1_1_1 = new JLabel("Durum :");
		lblNewLabel_7_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7_1_1_1.setBounds(10, 654, 129, 20);
		panel_5.add(lblNewLabel_7_1_1_1);

		txtOwner = new JTextField();
		txtOwner.setToolTipText("Mal Sahibi Bilgileri");
		txtOwner.setColumns(10);
		txtOwner.setBounds(149, 230, 160, 20);
		panel_5.add(txtOwner);

		txtFacede = new JTextField();
		txtFacede.setToolTipText("");
		txtFacede.setColumns(10);
		txtFacede.setBounds(149, 349, 160, 20);
		panel_5.add(txtFacede);

		txtBuildAge = new JTextField();
		txtBuildAge.setToolTipText("");
		txtBuildAge.setColumns(10);
		txtBuildAge.setBounds(149, 320, 160, 20);
		panel_5.add(txtBuildAge);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setLayout(null);
		panelPhoto.setBounds(346, 10, 500, 500);
		contentPane.add(panelPhoto);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(1, 400, 499, 100);
		panelPhoto.add(panel);

		JLabel lblNewLabel = new JLabel("Fotoğraf Ekle");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(
				GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-camera-32.png")));
		lblNewLabel.setToolTipText("Fotoğraf Eklemek İçin Tıklayınız.");
		lblNewLabel.setBounds(0, 0, 499, 100);
		panel.add(lblNewLabel);

		JLabel lblPhotos = new JLabel("Fotoğraf Yok");
		lblPhotos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotos.setBounds(0, 0, 500, 400);
		panelPhoto.add(lblPhotos);

		JButton btnSave = new JButton("KAYDET");

		btnSave.setIcon(
				new ImageIcon(GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-save-32.png")));
		btnSave.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSave.setBounds(441, 520, 303, 36);
		contentPane.add(btnSave);

		JLabel lblCancel = new JLabel("İPTAL");

		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCancel.setBounds(441, 750, 303, 20);
		contentPane.add(lblCancel);

		JButton btnUpdate = new JButton("Güncelle/Düzenle");

		btnUpdate.setIcon(new ImageIcon(
				GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));
		btnUpdate.setToolTipText("Seçilen Kayıt İçin İşlemleri Yapar");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(441, 611, 303, 35);
		contentPane.add(btnUpdate);

		JButton btnList = new JButton("Listele");

		btnList.setIcon(
				new ImageIcon(GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		btnList.setToolTipText("Seçilen Kayıt İçin İşlemleri Yapar");
		btnList.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnList.setBounds(441, 566, 303, 35);
		contentPane.add(btnList);

		JButton btnDelete = new JButton("SİL");

		btnDelete.setIcon(new ImageIcon(
				GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));
		btnDelete.setToolTipText("Seçilen Kaydı Siler");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(441, 656, 303, 35);
		contentPane.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(856, 40, 674, 730);
		contentPane.add(scrollPane);

		table = new JTable();

		JButton btnGoBack = new JButton("Geri Dön");

		btnGoBack.setIcon(new ImageIcon(
				GayrimenkulAddFrame.class.getResource("/com/emlakotomasyonu/images/icons8-shutdown-32.png")));
		btnGoBack.setToolTipText("Geri Dön");
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGoBack.setBounds(441, 701, 303, 35);
		contentPane.add(btnGoBack);

		JButton btnDsc = new JButton("Tersten Sırala");

		btnDsc.setIcon(new ImageIcon(GayrimenkulAddFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16 (1).png")));
		btnDsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnDsc.setBounds(1330, 6, 200, 25);
		contentPane.add(btnDsc);

		JButton btnAsc = new JButton("Sırala");

		btnAsc.setIcon(new ImageIcon(GayrimenkulAddFrame.class
				.getResource("/com/emlakotomasyonu/images/icons8-reversed-numerical-sorting-16.png")));
		btnAsc.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAsc.setBounds(1120, 8, 200, 25);
		contentPane.add(btnAsc);

		// Table Set
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Ekleme Tarihi", "Tipi", "Ev Tipi",
				"Satılık/Kira", "Oda Sayısı", "Alan", "Semt", "Şehir", "Durum", "Fotoğraf" }));
		scrollPane.setViewportView(table);
		DefaultTableModel dtm = new DefaultTableModel();

		// İşlemler

		// İptal
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Fieldların içini boşalt
				txtAddress.setText("");
				txtAddDate.setText("");
				txtAlan.setText("");
				txtDeposit.setText("");
				txtDistrict.setText("");
				txtFacede.setText("");
				txtFloor.setText("");
				txtId.setText("");
				txtOwner.setText("");
				txtPrice.setText("");
				txtProvince.setText("");
				txtRoomNumber.setText("");
				txtSite.setText("");
				txtSub.setText("");
				txtBuildAge.setText("");
				cbxCommission.setSelectedIndex(0);
				cbxHeating.setSelectedIndex(0);
				cbxHouseType.setSelectedIndex(0);
				cbxSaleHire.setSelectedIndex(0);
				cbxStatus.setSelectedIndex(0);
				comboBoxGayrimenkulType.setSelectedIndex(0);
				lblPhotos.setIcon(null);
			}
		});

		// Sayfadan Çıkar
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(null, "Geri Dönmek İstediğinize Emin Misiniz?",
						"Çıkış Onayı", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirm == JOptionPane.OK_OPTION) {
					// GayrimenkulAddFrame'i kapatır.
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(lblCancel);
					frame.dispose();
				}

			}
		});

		// Tarih Label'ına tıklanınca sistem tarihini field'a yazar.
		lblAddDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sistem tarihini al
				LocalDate currentDate = LocalDate.now();

				// Tarihi string'e dönüştür
				String dateString = currentDate.toString();

				// Text field'a yazdır
				txtAddDate.setText(dateString);
			}
		});

		// Fotoğraf Seçme Ekranı
		lblNewLabel.addMouseListener(new MouseAdapter() {
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
						lblPhotos.setIcon(photoIcon);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// Kaydet Button
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estateType = (String) comboBoxGayrimenkulType.getSelectedItem();
				String addDate = txtAddDate.getText();
				int commission = cbxCommission.getSelectedIndex();
				String type = (String) cbxSaleHire.getSelectedItem();
				double price = Double.parseDouble(txtPrice.getText());
				double deposit = Double.parseDouble(txtDeposit.getText());
				int ownerid = Integer.parseInt(txtOwner.getText());
				String houseType = (String) cbxHouseType.getSelectedItem();
				String roomNumber = txtRoomNumber.getText();
				int buildAge = Integer.parseInt(txtBuildAge.getText());
				String facede = txtFacede.getText();
				String alan = txtAlan.getText();
				String heating = (String) cbxHeating.getSelectedItem();
				String floor = txtFloor.getText();
				String site = txtSite.getText();
				int sub = Integer.parseInt(txtSub.getText());
				String address = txtAddress.getText();
				String district = txtDistrict.getText();
				String province = txtProvince.getText();
				String status = (String) cbxStatus.getSelectedItem();

				// TextBox Nullcheck
				if (txtAddress.getText().equals("") || txtAddDate.getText().equals("") || txtAlan.getText().equals("")
						|| txtBuildAge.getText().equals("") || txtDeposit.getText().equals("")
						|| txtDistrict.getText().equals("") || txtFacede.getText().equals("")
						|| txtFloor.getText().equals("") || txtOwner.getText().equals("")
						|| txtPrice.getText().equals("") || txtProvince.getText().equals("")
						|| txtRoomNumber.getText().equals("") || txtSite.getText().equals("")
						|| txtSub.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "HATA!", "Lütfen Tüm Zorunlu Alanları Doldurunuz.",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Class.forName("org.postgresql.Driver");

						// DB classında olan Connection'a erişim.
						DB db = new DB();
						db.createConnection();

						// Sql String
						String sql = "insert into public.tbl_estate(estatetype,housetype,addate,commission,type,ownerid,roomnumber,buildage,facede,alan,floor,heating,site,subscription,price,deposit,address,district,province,status,photo) "
								+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement statement = db.createConnection().prepareStatement(sql);
						// Fotoğraf SQL String'e eklenecek (photo)
						// Fotoğrafın yolu alındıktan sonra ilgili byte dizisi olarak okunmalı ve
						// statement'a atanmalı
						FileInputStream fis = new FileInputStream(selectedFile);

						statement.setString(1, estateType);
						statement.setString(2, houseType);
						statement.setString(3, addDate);
						statement.setInt(4, commission);
						statement.setString(5, type);
						statement.setInt(6, ownerid);
						statement.setString(7, roomNumber);
						statement.setInt(8, buildAge);
						statement.setString(9, facede);
						statement.setString(10, alan);
						statement.setString(11, floor);
						statement.setString(12, heating);
						statement.setString(13, site);
						statement.setInt(14, sub);
						statement.setDouble(15, price);
						statement.setDouble(16, deposit);
						statement.setString(17, address);
						statement.setString(18, district);
						statement.setString(19, province);
						statement.setString(20, status);
						statement.setBinaryStream(21, fis, (int) selectedFile.length());

						// Sorguyu Çalıştır
						int rowsAffected = statement.executeUpdate();

						// Kayıt Başarılı İse
						if (rowsAffected > 0) {

							list();
							// Fotoğrafın kaldırılması
							JOptionPane.showMessageDialog(null, "Kayıt Başarılı.");
							lblPhotos.setIcon(null);

							// Field'ların içinin boşaltılması
							txtAddress.setText("");
							txtAddDate.setText("");
							txtAlan.setText("");
							txtDeposit.setText("");
							txtDistrict.setText("");
							txtFacede.setText("");
							txtFloor.setText("");
							txtId.setText("");
							txtOwner.setText("");
							txtPrice.setText("");
							txtProvince.setText("");
							txtRoomNumber.setText("");
							txtSite.setText("");
							txtSub.setText("");
							txtBuildAge.setText("");
							cbxCommission.setSelectedIndex(0);
							cbxHeating.setSelectedIndex(0);
							cbxHouseType.setSelectedIndex(0);
							cbxSaleHire.setSelectedIndex(0);
							cbxStatus.setSelectedIndex(0);
							comboBoxGayrimenkulType.setSelectedIndex(0);

						} else {
							JOptionPane.showMessageDialog(null, "Kayıt Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
						}

						// Bağlantıyı Kapat
						statement.close();
						db.createConnection().close();

					} catch (ClassNotFoundException e1) {
						System.out.println("HATA! \n");
						e1.printStackTrace();
					} catch (SQLException e2) {
						System.out.println("SQL HATASI \n");
						e2.printStackTrace();
					} catch (FileNotFoundException e3) {
						System.out.println("HATA! Dosya/Fotoğraf Bulunamadı.");
						e3.printStackTrace();
						JOptionPane.showMessageDialog(null, "HATA! Dosya/Fotoğraf Bulunamadı!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

		// Table'da satıra tıklayınca verileri textfield'a getirecek.
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();

				// Seçilen satırdaki ID'yi al
				int userId = (int) table.getValueAt(selectedRow, 0);

				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, estatetype, housetype, addate, commission, type, ownerid, roomnumber, buildage, facede, alan, floor, heating, site, subscription, price, deposit, address, district, province, status, photo FROM tbl_estate WHERE id = '"
							+ userId + "';";
					Statement statement = connection.createStatement();

					// Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);

					while (resultSet.next()) {
						String id = resultSet.getString("id");
						String estateType = resultSet.getString("estatetype");
						String houseType = resultSet.getString("houseType");
						String addDate = resultSet.getString("addate");
						int commission = resultSet.getInt("commission");
						String type = resultSet.getString("type");
						String ownerId = resultSet.getString("ownerid");
						String roomNumber = resultSet.getString("roomnumber");
						String buildAge = resultSet.getString("buildage");
						String facede = resultSet.getString("facede");
						String alan = resultSet.getString("alan");
						String floor = resultSet.getString("floor");
						String heating = resultSet.getString("heating");
						String site = resultSet.getString("site");
						String subscription = resultSet.getString("subscription");
						String price = resultSet.getString("price");
						String deposit = resultSet.getString("deposit");
						String address = resultSet.getString("address");
						String district = resultSet.getString("district");
						String province = resultSet.getString("province");
						String status = resultSet.getString("status");

						// Verileri textField'lara aktar
						txtId.setText(id);
						comboBoxGayrimenkulType.setSelectedItem(estateType);
						cbxHouseType.setSelectedItem(houseType);
						txtAddDate.setText(addDate);
						cbxCommission.setSelectedIndex(commission);
						cbxSaleHire.setSelectedItem(type);
						txtOwner.setText(ownerId);
						txtRoomNumber.setText(roomNumber);
						txtBuildAge.setText(buildAge);
						txtFacede.setText(facede);
						txtAlan.setText(alan);
						txtFloor.setText(floor);
						cbxHeating.setSelectedItem(heating);
						txtSite.setText(site);
						txtSub.setText(subscription);
						txtPrice.setText(price);
						txtDeposit.setText(deposit);
						txtAddress.setText(address);
						txtDistrict.setText(district);
						txtProvince.setText(province);
						cbxStatus.setSelectedItem(status);

						// Fotoğraf İşlemleri
						byte[] photoData = resultSet.getBytes("photo");

						// JLabel'da göster
						if (photoData != null) {
							ImageIcon photoIcon = new ImageIcon(photoData);
							lblPhotos.setIcon(photoIcon);
						} else {
							lblPhotos.setIcon(null);
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

		// Frame Açılınca Listeyi Getirecek
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				list();
			}
		});

		// Listele Button
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		// Guncelle Button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(GayrimenkulAddFrame.this,
							"Lütfen güncellemek istediğiniz satırı seçin.", "Hata! Satır Seçilmedi.",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String estateType = (String) comboBoxGayrimenkulType.getSelectedItem();
				String addDate = txtAddDate.getText();
				int commission = cbxCommission.getSelectedIndex();
				String type = (String) cbxSaleHire.getSelectedItem();
				double price = Double.parseDouble(txtPrice.getText());
				double deposit = Double.parseDouble(txtDeposit.getText());
				int ownerid = Integer.parseInt(txtOwner.getText());
				String houseType = (String) cbxHouseType.getSelectedItem();
				String roomNumber = txtRoomNumber.getText();
				int buildAge = Integer.parseInt(txtBuildAge.getText());
				String facede = txtFacede.getText();
				String alan = txtAlan.getText();
				String heating = (String) cbxHeating.getSelectedItem();
				String floor = txtFloor.getText();
				String site = txtSite.getText();
				int sub = Integer.parseInt(txtSub.getText());
				String address = txtAddress.getText();
				String district = txtDistrict.getText();
				String province = txtProvince.getText();
				String status = (String) cbxStatus.getSelectedItem();

				// Veritabanındaki bilgileri güncelle
				try {
					Class.forName("org.postgresql.Driver");

					// Seçilen fotoğrafın byte dizisine dönüştürülmesi
					byte[] photoBytes = Files.readAllBytes(selectedFile.toPath());

					DB db = new DB();
					Connection connection = db.createConnection();

					String sql;
					PreparedStatement statement;

					// Seçilen satırdaki ID'yi al
					int id = (int) table.getValueAt(selectedRow, 0);

					// Sql String
					sql = "UPDATE public.tbl_estate SET estatetype = ?, addate = ?, commission = ?, type = ?, price = ?, deposit = ?, ownerid = ?, housetype = ?, roomnumber = ?, buildage = ?, facede = ?, alan = ?, heating = ?, floor = ?, site = ?, subscription = ?, address = ?, district = ?, province = ?, status = ?, photo = ? WHERE id = '"
							+ id + "'";

					statement = connection.prepareStatement(sql);

					statement.setString(1, estateType);
					statement.setString(2, addDate);
					statement.setInt(3, commission);
					statement.setString(4, type);
					statement.setDouble(5, price);
					statement.setDouble(6, deposit);
					statement.setInt(7, ownerid);
					statement.setString(8, houseType);
					statement.setString(9, roomNumber);
					statement.setInt(10, buildAge);
					statement.setString(11, facede);
					statement.setString(12, alan);
					statement.setString(13, heating);
					statement.setString(14, floor);
					statement.setString(15, site);
					statement.setInt(16, sub);
					statement.setString(17, address);
					statement.setString(18, district);
					statement.setString(19, province);
					statement.setString(20, status);
					statement.setBytes(21, photoBytes);

					// Sorguyu Çalıştır
					int rowCount = statement.executeUpdate();

					// Kayıt Başarılı İse
					if (rowCount > 0) {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarılı.");

						// JTable Yenile
						list();

						// Fotoğraf Kutusunu Sıfırla
						lblPhotos.setIcon(null);

						// Fieldları Temizle
						txtAddress.setText("");
						txtAddDate.setText("");
						txtAlan.setText("");
						txtBuildAge.setText("");
						txtDeposit.setText("");
						txtDistrict.setText("");
						txtFacede.setText("");
						txtFloor.setText("");
						txtId.setText("");
						txtOwner.setText("");
						txtPrice.setText("");
						txtProvince.setText("");
						txtRoomNumber.setText("");
						txtSite.setText("");
						txtSub.setText("");
						cbxCommission.setSelectedIndex(0);
						cbxHeating.setSelectedIndex(0);
						cbxHouseType.setSelectedIndex(0);
						cbxSaleHire.setSelectedIndex(0);
						cbxStatus.setSelectedIndex(0);

					} else {
						JOptionPane.showMessageDialog(null, "Güncelleme Başarısız!", "HATA!",
								JOptionPane.ERROR_MESSAGE);
					}

					// Connection kapat
					statement.close();
					connection.close();

				} catch (SQLException e1) {
					System.out.println("SQL HATASI \n");
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e1.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Geçersiz sayı formatı!", "HATA!", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				} catch (IOException e3) {
					JOptionPane.showMessageDialog(null, "Fotoğraf okuma hatası: " + e3.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
					e3.printStackTrace();
				} catch (ClassNotFoundException e4) {
					e4.printStackTrace();
				}
			}
		});

		// Sil Button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// table'dan satır seçer
				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					// Eğer bir satır seçilmediyse kullanıcıyı uyar
					JOptionPane.showMessageDialog(GayrimenkulAddFrame.this, "Lütfen silmek istediğiniz satırı seçin.",
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
						String sql = "DELETE FROM public.tbl_estate WHERE id = ?";

						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setInt(1, id);

						// Sorguyu Çalıştır
						int rowsAffected = preparedStatement.executeUpdate();

						preparedStatement.close();
						connection.close();

						// Kayıt Başarılı İse
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı.");
							lblPhotos.setIcon(null);
							// Table'ı yenile
							list();
							// Fieldların içini boşalt
							txtAddress.setText("");
							txtAddDate.setText("");
							txtAlan.setText("");
							txtDeposit.setText("");
							txtDistrict.setText("");
							txtFacede.setText("");
							txtFloor.setText("");
							txtId.setText("");
							txtOwner.setText("");
							txtPrice.setText("");
							txtProvince.setText("");
							txtRoomNumber.setText("");
							txtSite.setText("");
							txtSub.setText("");
							txtBuildAge.setText("");
							cbxCommission.setSelectedIndex(0);
							cbxHeating.setSelectedIndex(0);
							cbxHouseType.setSelectedIndex(0);
							cbxSaleHire.setSelectedIndex(0);
							cbxStatus.setSelectedIndex(0);
							comboBoxGayrimenkulType.setSelectedIndex(0);
							
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

		// Sırala
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		// Tersten sırala
		btnDsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					Connection connection = db.createConnection();

					String sql = "SELECT id, addate, estatetype, housetype, type, roomnumber, alan, district, province, status, photo FROM public.tbl_estate ORDER BY id DESC;";
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
						String addDate = resultSet.getString("addate");
						String estateType = resultSet.getString("estatetype");
						String houseType = resultSet.getString("housetype");
						String type = resultSet.getString("type");
						int roomNumber = resultSet.getInt("roomnumber");
						String alan = resultSet.getString("alan");
						String district = resultSet.getString("district");
						String province = resultSet.getString("province");
						String status = resultSet.getString("status");
						byte[] photoBytes = resultSet.getBytes("photo");

						Object[] row = { id, addDate, estateType, houseType, type, roomNumber, alan, district, province,
								status, photoBytes };
						model.addRow(row);
					}

					// Bağlantıyı Kapat
					statement.close();
					connection.close();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(), "HATA!",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("SQL HATASI!\n");
					e2.printStackTrace();
				}
			}
		});
		
	}

	// Table'ı yenileyecek metot
	public void list() {
		try {
			// DB classında olan Connection'a erişim.
			DB db = new DB();
			Connection connection = db.createConnection();

			String sql = "SELECT id, addate, estatetype, housetype, type, roomnumber, alan, district, province, status, photo FROM public.tbl_estate ORDER BY id ASC;";
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
				String addDate = resultSet.getString("addate");
				String estateType = resultSet.getString("estatetype");
				String houseType = resultSet.getString("housetype");
				String type = resultSet.getString("type");
				int roomNumber = resultSet.getInt("roomnumber");
				String alan = resultSet.getString("alan");
				String district = resultSet.getString("district");
				String province = resultSet.getString("province");
				String status = resultSet.getString("status");
				byte[] photoBytes = resultSet.getBytes("photo");

				Object[] row = { id, addDate, estateType, houseType, type, roomNumber, alan, district, province, status,
						photoBytes };
				model.addRow(row);
			}

			// Bağlantıyı Kapat
			statement.close();
			connection.close();

		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(), "HATA!",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("SQL HATASI!\n");
			e2.printStackTrace();
		}
	}
}
