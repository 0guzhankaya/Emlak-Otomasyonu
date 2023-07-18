package com.emlakotomasyonu.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	String constr = "";// jdbc:postgresql://localhost:5432/emlakotomasyonudb", "postgres","123";
	String driver = "jdbc:postgresql";
	String server = "localhost";
	String port = "5432";
	String database = "emlakotomasyonudb";
	String user = "postgres";
	String pass = "123";

	public Connection connection = null;
	public Statement statement = null;

	// Constructor
	public DB() {
		if (!port.equals("")) {
			port = ":" + port;
		}

		constr = driver + server + port + ";" + database;
		//createConnection();
		setConnectionString();
		getConnectionString();
	}

	public Connection createConnection() {

		try {
			//connection = DriverManager.getConnection(constr, user, pass);
			
			//Connection String
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/emlakotomasyonudb", "postgres","123");
			statement = connection.createStatement();
			System.out.println("BAGLANTI BASARILI.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Bağlantı Hatası!" + e.getMessage());
		}
		return connection;
	}

	// Dosyaya yazma işlemleri
	public void setConnectionString() {

		File bufferedFile = new File("ConnectionData.txt");
		
		try {
			FileWriter conFile = new FileWriter(bufferedFile);

			BufferedWriter bufferedfileWriter = new BufferedWriter(conFile);
			bufferedfileWriter.write(createConnection().toString());
			bufferedfileWriter.flush();
			bufferedfileWriter.close();
			System.out.print("Yazildi.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Dosya Yazma Isleminde Hata!");
		}
	}
	

	//Dosyadan okuma işlemleri
	public void getConnectionString() {
		
		try {
			FileReader reader;
			BufferedReader bufferedReader;
			String readData = null;
			File fileReader = new File("connectionData.txt");

			try {
				reader = new FileReader(fileReader);
				bufferedReader = new BufferedReader(reader);
				readData = bufferedReader.readLine();
				while (readData != null) {
					System.out.println(readData);
					readData = bufferedReader.readLine();
				}

				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
