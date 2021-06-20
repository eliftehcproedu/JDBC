package techProMaven.jdbcExamples;

public class Urun {
	//POJO class
	private int id;
	private String isim;
	private double fiyat;
	// source menusunden Constructor using fiels sec generate et...
	// sonra ayni yerden getter setter sec ve toString sec...
	
	public Urun(int id, String isim, double fiyat) {
		//super(); gerek yok super class yok 
		this.id = id;
		this.isim = isim;
		this.fiyat = fiyat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	@Override
	public String toString() {
		return "id=" + id + ", isim=" + isim + ", fiyat=" + fiyat;  // urun ve koseli parantezleri silindi gerek yok diye
	}
	
	
	

}
