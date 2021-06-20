package techProMaven.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//tek tek SQL importlar yerine bir tek SQL import olur:  import java.sql.*;
//ama butun kutuphaneyi ekledigi icin tercih edilen bir durum degildir
public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//1) Veritabani icin uygun Driver'ı ekle
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2) Veritabani baglantisi olustur
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain", "ahmet","1234");
		
		//3) SQL komutlari icin bir Statement nesnesi olustur.
		Statement st =con.createStatement();   //SQL ifadeleri olusturur
		
		//4)Sorgu ifadesini calistir. 
		//(Personel tablosundaki personel_id’si 7369 olan personelin adini sorgulayiniz)
		ResultSet isim = st.executeQuery("SELECT personel_isim from personel where personel_id=7369");
		
		//5)Sonuclari isle
		while(isim.next()) {
		System.out .println("Personel Adi: " + isim.getString("personel_isim"));  //Personel Adi: AHMET
		System.out .println("Personel Adi: " + isim.getString(1) + " Maas: " + isim.getInt(2));
																	   		//isim.getInt("maas");
		}
		
		//6) Olusturulan nesneleri bellekten kaldir.
		st.close();
		con.close();
		isim.close();  
		
	}

}
