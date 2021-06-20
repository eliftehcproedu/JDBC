package techProMaven.jdbcExamples;
import java.sql.*;
public class Jdbc3DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String yol="jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(yol,"ahmet","1234");
		
		Statement st= con.createStatement();
		
		
		/*
		 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
		 	   dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC’de 2 alternatif bulunmaktadir.  
		 	  	1) execute() metodu 
		 	  	2) excuteUpdate() metodu.  
		 	  
		 	B) 	- execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur. 
		 	 	- execute(), Boolean bir deger dondurur. 
		 	 	- DDL islemlerin false dondururken, DML islemlerinde true deger dondurur.(ResultSet olusturur) 
		 	 	- Ozellikle hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi 
		 	  	  durumlarda tercih edilmektedir.  
		 	   
		 	C) 	- executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
		 		- bu islemlerde islemden etkilenen satir sayisini dondurur.
		 	 	- Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
		 	 
		 	 execute() her turlu SQL kjomutuyla kullanilir .......  DDL ---> False    DML----> True
		 	 executeUpdate()  DDL ----> 0           DML ----->etkilenen satir sayisini verir
		 */

/*		=======================================================================
		 ORNEK1:isciler adinda bir tablo olusturunuz id NUMBER(3), 
		 birim VARCHAR2(10), maas NUMBER(5)
		========================================================================*/
		String createQuery ="CREATE TABLE isciler"
							+ "( id NUMBER(3), "
							+ " birim VARCHAR2(10), "
							+ " maas NUMBER(5))";
		
		
		//1.yontem : execute () methodu ile
		
	//	boolean s1 = st.execute(createQuery); // false deger dondurur
	//	System.out.println("isciler tablosu olusturuldu " +s1);
	
	//	st.execute(createQuery); // false deger dondurur
	//	System.out.println("isciler tablosu olusturuldu " ); //isciler tablosu olusturuldu 
	
		
		//2.yontem : executeUpdate() methodu ile
		
	//	int s2 = st.executeUpdate(createQuery);  //DDL islemleri icin 0 degerini dondurur
	//	System.out.println("isciler tablosu olusturuldu " +s2);

	//	st.executeUpdate(createQuery);
	//	System.out.println("isciler tablosu olusturuldu "); //isciler tablosu olusturuldu 
		
		/*======================================================================
		 ORNEK2:isciler tablosunu kalici olarak siliniz 		
		========================================================================*/
	//	String dropQuery1 = "DROP TABLE isciler PURGE";
	//	st.execute(dropQuery1);
	//	System.out.println("isciler tablosu silindi "); //isciler tablosu silindi 
		
	/*=======================================================================
		ORNEK3:isciler tablosuna yeni bir sutun {isim Varchar2(20)} ekleyiniz.  
	========================================================================*/	
		
	//	String alterQuery = "ALTER TABLE isciler ADD isim Varchar2(20) ";
  	//	st.executeUpdate(alterQuery);
	
	//	System.out.println("isciler tablosuna isim Varchar2(20) adinda bir sutun eklendi");
		//"isciler tablosuna isim Varchar2(20) adinda bir sutun eklendi"
		/*
		 ID	    NUMBER(3,0)			Yes		1	
		BIRIM	VARCHAR2(10 BYTE)	Yes		2	
		MAAS	NUMBER(5,0)			Yes		3	
		ISIM	VARCHAR2(20 BYTE)	Yes		4	 SQL de bu sutun eklenmis oldu
		 */
		
		/*=======================================================================
		 ORNEK4:isciler tablosuna soyisim VARCHAR2(20) ve sehir VARCHAR2(10)) 
		 adinda 2 yeni sutun ekleyiniz.  
		========================================================================*/
		
	//	String alterQuery1 = "ALTER TABLE isciler ADD (soyisim Varchar2(20), sehir VARCHAR2(10) )";
		
	//	st.executeUpdate(alterQuery1);
	//	System.out.println("isciler tablosuna isim Varchar2(20) ve sehir VARCHAR2(10) adinda iki sutun eklendi");
		//isciler tablosuna isim Varchar2(20) ve sehir VARCHAR2(10) adinda iki sutun eklendi
		/*
		 *	ID	    NUMBER(3,0)
			BIRIM	VARCHAR2(10 BYTE)
			MAAS	NUMBER(5,0)
			ISIM	VARCHAR2(20 BYTE)
			SOYISIM	VARCHAR2(20 BYTE)
			SEHIR	VARCHAR2(10 BYTE) SQL de 2 sutun geldi..
		 */
		
		/*=======================================================================
		 ORNEK5:isciler tablosundaki soyisim sutunu siliniz.
		========================================================================*/ 
	//	String alterDeleteQuery = "ALTER TABLE isciler DROP COLUMN soyisim";
		//isciler tablosundan soyisim sutununu silindi..
	//	st.execute(alterDeleteQuery);
	//	System.out.println("isciler tablosundan soyisim sutununu silindi..");
		/*
		 *	ID
			BIRIM
			MAAS
			ISIM
			SEHIR
		 */
		
		/*=======================================================================
		 ORNEK6:isciler tablosununadini calisanlar olarak degistiriniz.  
		========================================================================*/	
		
	//	String alterQuery2 = "ALTER TABLE isciler RENAME TO calisanlar";
		
	//	st.execute(alterQuery2);
	//	System.out.println("isciler tablosunun ismi calisanlar olarak degisti..");
		//isciler tablosunun ismi calisanlar olarak degisti..
		
		
		/*=======================================================================
		  ORNEK7:calisanlar tablosunu siliniz.  
		========================================================================*/
		String dropQuery2 = "DROP TABLE calisanlar PURGE";
		st.execute(dropQuery2);
		System.out.println("calisanlar tablosu silindi.."); //calisanlar tablosu silindi..
		
		st.close();
		con.close();
		
		
	}

}
