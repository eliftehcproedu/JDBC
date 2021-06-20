package techProMaven.jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc4DMLInsert {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	String yol="jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection con = DriverManager.getConnection(yol,"ahmet","1234");
	
	Statement st= con.createStatement();
	
	/*=======================================================================
		 ORNEK1: Bolumler tablosuna yeni bir kayit ((80, ‘ARGE’, ‘ISTANBUL’) 
		 ekleyelim ve eklenen kaydi teyit icin sorgulayalim.
		========================================================================*/
//	String insertQuery= "INSERT INTO bolumler VALUES(80, 'ARGE', 'ISTANBUL')";
//	int s1 = st.executeUpdate(insertQuery);
//	System.out.println(s1 + " kadar satir eklendi" ); //1 kadar satir eklendi
	
	/*
	 	10	MUHASABE	IST
		20	MUDURLUK	ANKARA
		30	SATIS		IZMIR
		40	ISLETME		BURSA
		50	DEPO		YOZGAT
		80	ARGE		ISTANBUL  bu satir SQL de eklendi...
	 */

		ResultSet rs = st.executeQuery("SELECT * FROM bolumler");
        
        while(rs.next()) {
            System.out.println("Bölüm ID:" + rs.getInt("bolum_id")+" "+"Bölüm Isim:" 
            					+ rs.getString("bolum_isim")+"\t"+"Konum:" + rs.getString("konum"));
       
           //  Bölüm ID:80 Bölüm Isim:ARGE	Konum:ISTANBUL
        }
        
        
      /*=======================================================================
		 ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
		 ========================================================================*/  
     
        // 1.YONTEM
        // -----------------------------------------------
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin 
        // yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
        // bu kotu bir yaklasimdir.
        
      //  String [] sorgular = {"INSERT INTO bolumler VALUES(95, 'YEMEKHANE', 'ISTANBUL')",
      //                     "INSERT INTO bolumler VALUES(85, 'OFIS','ANKARA')",
      //                       "INSERT INTO bolumler VALUES(75, 'OFIS2', 'VAN')"};
    
      /*  int satir =0;
        for(String each: sorgular) {
        	
        satir= satir+ st.executeUpdate(each);
        }
         System.out.println(satir + " satir eklendi"); //3 satir eklendi
         */
         
         // 2.YONTEM (addBath ve excuteBatch() metotlari ile)
		// ----------------------------------------------------
		// addBatch metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()
		// metodu ile veritabanina bir kere gonderilebilir.
		// excuteBatch() metodu bir int [] dizi dondurur.
        // Bu dizi her bir ifade sonucunda etkilenen satir sayisini gosterir. 
			
         String [] sorgular1 = {"INSERT INTO bolumler VALUES(81, 'YEMEKHANE2', 'MUGLA')",
                 				"INSERT INTO bolumler VALUES(82, 'OFIS2','ORDU')",
                 				"INSERT INTO bolumler VALUES(83, 'OFIS3', 'MUS')"};
         
         for(String each: sorgular1) {
        	 st.addBatch(each);
        	 
        	}		         

         int[] satr =st.executeBatch();  //satr bir array eleman sayisi (satir sayisi): length
         System.out.println(satr.length + " satir eklendi..");
         
         /*
          	10	MUHASABE	IST
			20	MUDURLUK	ANKARA
			30	SATIS		IZMIR
			40	ISLETME		BURSA
			50	DEPO		YOZGAT
			95	YEMEKHANE	ISTANBUL
			85	OFIS		ANKARA
			75	OFIS2		VAN
			81	YEMEKHANE2	MUGLA  	*
			82	OFIS2		ORDU	*
			83	OFIS3		MUS  	*  SON 3 satir SQL de eklendi...
          */
         
         // 3. YONTEM
 		//-----------------------------------------------------
 		// batch metoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
        // SQL de guvenligi icin basvurulan bir yontemdir. Unutulmamalidir...
 		// bir sonraki ornekte bunu gerceklestirecegiz.
         
        
        
         
	con.close();
	st.close();
	//rs.close();
	}
}
