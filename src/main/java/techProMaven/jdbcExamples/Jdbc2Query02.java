package techProMaven.jdbcExamples;
import java.sql.*;   // Tum JDBC metotlarini eklemek icin 
public class Jdbc2Query02 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		String yol="jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection(yol,"ahmet","1234");
		
		Statement st= con.createStatement();
		
		// Bolumler tablosundaki tum kayitlari listeleyen sorgu yapiniz
		
		// ResultSet bolumlerTablosu = st.executeQuery("SELECT*FROM bolumler"); yerine asagidaki 2 satir yazilabilir
		
		String selectOuery ="SELECT*FROM bolumler";
		ResultSet bT1 =st.executeQuery(selectOuery);
		
		while(bT1.next()) {
			//1.yol
			System.out .println(bT1.getInt(1)+" "+bT1.getString(2)+" "+"Konum:" + bT1.getString(3));
			//2.yol
			System.out.println("Bölüm ID:" + bT1.getString(1)+" "
				      +"Bölüm Isim:" + bT1.getString(2)+"\t"+"Konum:" + bT1.getString(3));
			
			/*
			 *	10 MUHASABE Konum:IST
				20 MUDURLUK Konum:ANKARA
				30 SATIS Konum:IZMIR
				40 ISLETME Konum:BURSA
				50 DEPO Konum:YOZGAT 
			 * 
			 */
			}
		
		//baglantiyi kapatmadan ayni connection ile birden fazla islem yapilabilir
	System.out.println("************************************************************");
	/*==========================================================================
		ORNEK3: SATIS ve MUHASABE bolumlerinde calisan personelin isimlerini ve 
		 maaslarini maas ters sirali olarak listeleyiniz
	   ========================================================================*/
		
		String q2 = "SELECT personel_isim,maas FROM personel"
						+ " WHERE bolum_id IN (10,30)" 
						+ " ORDER BY maas DESC";
		
		ResultSet sonuc =st.executeQuery(q2);
		while(sonuc.next()) { 	
		
		System.out.println("ISIM :" + sonuc.getString(1) + "\t" + "MAAS :" + sonuc.getInt(2));
		/*
		 * 	ISIM :SEHER	MAAS :5000
			ISIM :EMINE	MAAS :2850
			ISIM :HARUN	MAAS :2450
			ISIM :BAHATTIN	MAAS :1600
			ISIM :DUYGU	MAAS :1500
			ISIM :EBRU	MAAS :1300
			ISIM :NESE	MAAS :1250
			ISIM :MUHAMMET	MAAS :1250
			ISIM :MERVE	MAAS :950

		 */
			
		}
		System.out.println("************************************************************");
		/*=======================================================================
				 ORNEK4: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
				 ve maaslarini bolum ve maas siraali listeleyiniz. NOT: calisani olmasa 
				 bile bolum ismi gosterilmelidir.
				========================================================================*/
		
		String q3 ="SELECT b.bolum_isim, p.personel_isim, p.maas FROM personel p"  //satir sonunda tirnaktan once enter yap otomatik asagiya atiyor
				+ " FULL JOIN bolumler b"
				+ " ON b.bolum_id = p.bolum_id"  //ON ortak sutun belirtir
				+ " ORDER BY b.bolum_isim , p.maas";
			
		ResultSet sonuc1 =st.executeQuery(q3);
		while (sonuc1.next()) {
			System.out.println(sonuc1.getString(1) +  "\t" + sonuc1.getString(2) +  "\t" +sonuc1.getInt(3));
			/*
					DEPO		null		0
					ISLETME		null		0
					MUDURLUK	AHMET		800
					MUDURLUK	ALI			1100
					MUDURLUK	MUZAFFER	2975
					MUDURLUK	NAZLI		3000
					MUDURLUK	MESUT		3000
					MUHASABE	EBRU		1300
					MUHASABE	HARUN		2450
					MUHASABE	SEHER		5000
					SATIS		MERVE		950
					SATIS		MUHAMMET	1250
					SATIS		NESE		1250
					SATIS		DUYGU		1500
					SATIS		BAHATTIN	1600
					SATIS		EMINE		2850
					null		SIBEL		3300
					null		ZEKI		4300

			 */
		}
		System.out.println("************************************************************");
		/*      =======================================================================
				 ORNEK5: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
				========================================================================*/
		
		String q4 = "SELECT b.bolum_isim,p.personel_isim, p.maas FROM personel p"
                + " FULL JOIN bolumler b"
                + " ON b.bolum_id=p.bolum_id"
                + " ORDER BY p.maas DESC"
                + " FETCH NEXT 10 ROWS ONLY";
		
		ResultSet sonuc2 =st.executeQuery(q4);
		while (sonuc2.next()) {
			System.out.println(sonuc2.getString(1) +  "\t" + sonuc2.getString(2) +  "\t" +sonuc2.getInt(3));
		/*
		  	DEPO	    null	  0
			ISLETME	    null	  0
			MUHASABE    SEHER	  5000
			null	    ZEKI	  4300
			null	    SIBEL	  3300
			MUDURLUK	MESUT	  3000
			MUDURLUK	NAZLI	  3000
			MUDURLUK	MUZAFFER  2975
			SATIS	    EMINE	  2850
			MUHASABE	HARUN	  2450
		 
		 */
	
		
		}
		
			st.close();
			con.close();
			bT1.close();
			sonuc1.close();
			sonuc2.close();
	}
	
}
