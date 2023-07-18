package com.emlakotomasyonu.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.emlakotomasyonu.database.DB;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GayrimenkulListFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GayrimenkulListFrame frame = new GayrimenkulListFrame();
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
	public GayrimenkulListFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));
		setTitle("Gayrimenkul Listele");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-5, 100, 1920, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1520, 490);
		getContentPane().add(scrollPane);
		
		table = new JTable();

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Id", "Eklenme Tarihi", "Gayrimenkul Tipi", "Satılık/Kiralık", "Ev Tipi", "Oda Sayısı", "Alan", "Semt", "Şehir"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("");
		btnSearch.setBounds(1460, 10, 30, 30);
		btnSearch.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-search-20.png")));
		btnSearch.setForeground(Color.WHITE);
		getContentPane().add(btnSearch);
		
		JButton btnSearchClear = new JButton("");
		btnSearchClear.setBounds(1500, 10, 30, 30);
		btnSearchClear.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-clear-search-20.png")));
		btnSearchClear.setForeground(Color.WHITE);
		btnSearchClear.setEnabled(false);
		getContentPane().add(btnSearchClear);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(620, 12, 410, 30);
		comboBox.setToolTipText("Sıralama Ölçütü Seçiniz.");
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sıralama Ölçütü Seçiniz.", "Eklenme Tarihine Göre - Son Eklenen", "Eklenme Tarihine Göre - İlk Eklenen", "Oda Sayısına Göre - Artan", "Oda Sayısına Göre - Azalan", "Alanına Göre - Artan", "Alanına Göre - Azalan", "Semte Göre - A'dan Z'ye", "Semte Göre - Z'den A'ya", "Şehire Göre - A'dan Z'ye", "Şehire Göre - Z'den A'ya"}));
		getContentPane().add(comboBox);
		
		JButton btnShowDetails = new JButton("Detayları Göster");
		btnShowDetails.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-search-20.png")));
		btnShowDetails.setBounds(270, 550, 250, 35);
		btnShowDetails.setToolTipText("Detayları yeni bir sayfada raporlar");
		btnShowDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(btnShowDetails);
		
		JButton btnUpdate = new JButton("Güncelle/Düzenle");
		btnUpdate.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-update-done-32.png")));

		btnUpdate.setBounds(789, 550, 250, 35);
		btnUpdate.setToolTipText("Seçilen Kayıt İçin İşlemleri Yapar");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("SİL");
		btnDelete.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-waste-32.png")));

		btnDelete.setBounds(1049, 550, 250, 35);
		btnDelete.setToolTipText("Seçilen Kaydı Siler");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(btnDelete);
		
		JButton btnGoBack = new JButton("Geri Dön");
		btnGoBack.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-shutdown-32.png")));

		btnGoBack.setBounds(10, 550, 250, 35);
		btnGoBack.setToolTipText("Geri Dön");
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(btnGoBack);
		
		textField = new JTextField();
		textField.setToolTipText("Aramak İstediğiniz Kelimeyi Yazınız.");
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(1040, 10, 410, 30);
		contentPane.add(textField);
		
		JButton btnListele = new JButton("Listele");
		btnListele.setIcon(new ImageIcon(GayrimenkulListFrame.class.getResource("/com/emlakotomasyonu/images/icons8-list-24.png")));

		btnListele.setToolTipText("Seçilen Kayıt İçin İşlemleri Yapar");
		btnListele.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnListele.setBounds(530, 550, 250, 35);
		contentPane.add(btnListele);
		
		//İşlemler
		
		//Table'da satıra tıklayınca verileri textfield'a getirecek.
		// && Detaylandır Buttonu
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		//Frame Açılınca Listeyi Getirecek
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();
					
					String sql = "select id, addate, estatetype, titlestatus, housetype, roomnumber, district, province from public.tbl_estate;";
					Statement statement = (Statement) db.createConnection();
					
					//Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);
					
					//JTable modeli
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					//Tüm Satırları Temizle
					model.setRowCount(0);
					
					//Sonuçları JTable'a ekle
					while (resultSet.next()) {
						//Tablonun içerisine gelen veriler
						int id = resultSet.getInt("id");
						String addDate = resultSet.getString("addate");
						String estateType = resultSet.getString("estatetype");
					    String titleStatus = resultSet.getString("titlestatus");//TODO: Veritabanı değişecek, satılık/kiralık alanı doldurulacak.
						String houseType = resultSet.getString("housetype");
						int roomNumber = resultSet.getInt("roomnumber");
						String district = resultSet.getString("district");
						String province = resultSet.getString("province");
						
						Object[] row = {id,addDate,estateType,titleStatus,houseType,roomNumber,district,province};
						model.addRow(row);
					}
					
					//Bağlantıyı Kapat
					statement.close();
					db.createConnection().close();
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
		            JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(),"HATA!", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					System.out.println("SQL HATASI!\n");
				}
			}
		});
		
		//Listele Button
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("org.postgresql.Driver");
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();
					
					String sql = "select id, addate, estatetype, titlestatus, housetype, roomnumber, district, province from public.tbl_estate;";
					Statement statement = (Statement) db.createConnection();
					
					//Sorguyu Çalıştır
					ResultSet resultSet = statement.executeQuery(sql);
					
					//JTable modeli
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					//Tüm Satırları Temizle
					model.setRowCount(0);
					
					//Sonuçları JTable'a ekle
					while (resultSet.next()) {
						//Tablonun içerisine gelen veriler
						int id = resultSet.getInt("id");
						String addDate = resultSet.getString("addate");
						String estateType = resultSet.getString("estatetype");
					    String titleStatus = resultSet.getString("titlestatus");//TODO: Veritabanı değişecek, satılık/kiralık alanı doldurulacak.
						String houseType = resultSet.getString("housetype");
						int roomNumber = resultSet.getInt("roomnumber");
						String district = resultSet.getString("district");
						String province = resultSet.getString("province");
						
						Object[] row = {id,addDate,estateType,titleStatus,houseType,roomNumber,district,province};
						model.addRow(row);
					}
					
					//Bağlantıyı Kapat
					statement.close();
					db.createConnection().close();
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
		            JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e2.getMessage(),"HATA!", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					System.out.println("SQL HATASI!\n");
				}
			}
		});
		
		//Geri Dön Button
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Frame'i kapatır.
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(btnGoBack);
		        frame.dispose();
			}
		});
		
		//Güncelle Button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//Sil Button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//table'dan satır seçer
				int selectedRow = table.getSelectedRow();
				
				//Veritabanından Sil
				try {
					Class.forName("org.postgresql.Driver");
					
					// DB classında olan Connection'a erişim.
					DB db = new DB();
					db.createConnection();
					
					//Id
					//int id = resultSet.getInt("id");
					
					
					/*
					//Sql String
					//TODO: Id ?
					String sql = "delete from public.tbl_estate where id = '"id"';";
					
					PreparedStatement statement = db.createConnection().prepareStatement(sql);
					
					statement.executeUpdate(sql);
					statement.close();
					
					// Kayıt Başarılı İse
					if (statement.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Silme İşlemi Başarılı.");
						
					} else {
						JOptionPane.showMessageDialog(null, "Silme İşlemi Başarısız!", "HATA!", JOptionPane.ERROR_MESSAGE);
					}
					
					*/
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		
	}
}
