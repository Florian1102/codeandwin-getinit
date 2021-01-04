package app;

public class Hardware {

	private String name;

	private int maxBenoetigteAnzahlEinheiten;
	private double gewicht;
	private int nutzwert;
	private Double nutzwertJeKg;
	
	public Hardware(String name, int maxBenoetigteAnzahlEinheiten, double gewicht, int nutzwert) {
		this.name = name;
		this.maxBenoetigteAnzahlEinheiten = maxBenoetigteAnzahlEinheiten;
		this.gewicht = gewicht;
		this.nutzwert = nutzwert;
		this.nutzwertJeKg = nutzwert/gewicht;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getMaxBenoetigteAnzahlEinheiten() {
		return maxBenoetigteAnzahlEinheiten;
	}

	public void setMaxBenoetigteAnzahlEinheiten(int maxBenoetigteAnzahlEinheiten) {
		this.maxBenoetigteAnzahlEinheiten = maxBenoetigteAnzahlEinheiten;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public int getNutzwert() {
		return nutzwert;
	}

	public void setNutzwert(int nutzwert) {
		this.nutzwert = nutzwert;
	}

	public Double getNutzwertJeKg() {
		return nutzwertJeKg;
	}
	
	
}
