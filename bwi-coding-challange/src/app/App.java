package app;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class App {

	private static Fahrer fahrerEins;
	private static Fahrer fahrerZwei;
	
	private static Transporter transporterEins;
	private static Transporter transporterZwei;
	private static int nutzwertTransporterEins;
	private static int nutzwertTransporterZwei;
	private static double verfuegbareKapazitaetTransporterEins;
	private static double verfuegbareKapazitaetTransporterZwei;
	
	private static Map<Hardware, Integer> mapMitHardware;
	private static Map<String, Integer> mapTransporterEinsMitHardware;
	private static Map<String, Integer> mapTransporterZweiMitHardware;
	
	
	public static void main(String[] args) {

		initialisierung();
		
		berechnung();
		
		ausgabe();
	}
	
	private static void initialisierung() {
		// Fahrer initialisieren
		fahrerEins = new Fahrer(72.4);
		fahrerZwei = new Fahrer(85.7);
		
		// Transporter initialisieren
		transporterEins = new Transporter(1100);
		transporterZwei = new Transporter(1100);
		mapTransporterEinsMitHardware = new HashMap<String, Integer>();
		verfuegbareKapazitaetTransporterEins = transporterEins.getKapazitaet() - fahrerEins.getGewicht();
		mapTransporterZweiMitHardware = new HashMap<String, Integer>();
		verfuegbareKapazitaetTransporterZwei = transporterZwei.getKapazitaet() - fahrerZwei.getGewicht();
		
		// Nutzwert initialisieren
		nutzwertTransporterEins = 0;
		nutzwertTransporterZwei = 0;
		
		// Hardware initialisieren
		mapMitHardware = new TreeMap<Hardware, Integer>(new NutzwertComperator());
		mapMitHardware.put(new Hardware("Notebook B�ro 13", 205, 2.451, 40), 205);
		mapMitHardware.put(new Hardware("Notebook B�ro 14", 420, 2.978, 35), 420);
		mapMitHardware.put(new Hardware("Notebook outdoor", 450, 3.625, 80), 450);
		mapMitHardware.put(new Hardware("Mobiltelefon B�ro", 60, 0.717, 30), 60);
		mapMitHardware.put(new Hardware("Mobiltelefon Outdoor", 157, 0.988, 60), 157);
		mapMitHardware.put(new Hardware("Mobiltelefon Heavy Duty", 220, 1.220, 65), 220);
		mapMitHardware.put(new Hardware("Tablet B�ro klein", 620, 1.405, 40), 620);
		mapMitHardware.put(new Hardware("Tablet B�ro gro�", 250, 1.455, 40), 250);
		mapMitHardware.put(new Hardware("Tablet outdoor klein", 540, 1.690, 45), 540);
		mapMitHardware.put(new Hardware("Tablet outdoor gro�", 370, 1.980, 68), 370);
	}

	private static void berechnung() {

		// �ber jede Art von Produkt iterieren
		for (Map.Entry<Hardware, Integer> hardware : mapMitHardware.entrySet()) {
			int anzahlVonEinerHardware = hardware.getValue();
			// F�r jedes St�ck Hardware soll gepr�ft werden, ob es noch im Transporter Platz hat
			for (int i = 0; i < anzahlVonEinerHardware; i++) {
				boolean isHardwareSchonBenutzt = false;
				// Nur, wenn wenn die maximal ben�tigte Anzahl noch nicht �berschritten ist und es noch in den Transporter 1 passt
				if (hardware.getValue() > 0 && (verfuegbareKapazitaetTransporterEins - hardware.getKey().getGewicht()) >= 0 && !isHardwareSchonBenutzt){
					//...soll die Hardware dem Transporter hinzugef�gt werden
					mapTransporterEinsMitHardware.merge(hardware.getKey().getName(), 1, Integer::sum);
					verfuegbareKapazitaetTransporterEins -= hardware.getKey().getGewicht();
					nutzwertTransporterEins += hardware.getKey().getNutzwert();
					// und der ben�tigten Hardware abgezogen werden
					hardware.setValue(hardware.getValue() - 1);
					isHardwareSchonBenutzt = true;
				}
				// Falls der erste Transporter voll ist, wird hier gepr�ft, ob die Hardware noch im 2. Transporter transportiert werden kann
				if (hardware.getValue() > 0 && (verfuegbareKapazitaetTransporterZwei - hardware.getKey().getGewicht()) >= 0 && !isHardwareSchonBenutzt){
					// Hardware dem Transporter hinzuf�gen
					mapTransporterZweiMitHardware.merge(hardware.getKey().getName(), 1, Integer::sum);
					verfuegbareKapazitaetTransporterZwei -= hardware.getKey().getGewicht();
					nutzwertTransporterZwei += hardware.getKey().getNutzwert();
					// und der ben�tigten Hardware abziehen
					hardware.setValue(hardware.getValue() - 1);
					isHardwareSchonBenutzt = true;
				}
			}
		}
	}
		
	private static void ausgabe() {
		// Ausgabe auf der Konsole
		System.out.println("Transporter 1 beladen mit:");
		for (Map.Entry<String, Integer> hardware : mapTransporterEinsMitHardware.entrySet()) {
			System.out.println(hardware.getValue() + " x " + hardware.getKey());
		}
		System.out.println("Der Nutzwert vom Transporter 1 betr�gt: " + nutzwertTransporterEins + ". Das freie Gewicht betr�gt: " + verfuegbareKapazitaetTransporterEins);
		
		System.out.println();
		System.out.println("Transporter 2 beladen mit:");
		for (Map.Entry<String, Integer> hardware : mapTransporterZweiMitHardware.entrySet()) {
			System.out.println(hardware.getValue() + " x " + hardware.getKey());
		}
		System.out.println("Der Nutzwert vom Transporter 2 betr�gt: " + nutzwertTransporterZwei + ". Das freie Gewicht betr�gt: " + verfuegbareKapazitaetTransporterZwei);
		System.out.println();
		System.out.println("Der gesamte Nutzwert betr�gt: " + (nutzwertTransporterEins + nutzwertTransporterZwei));
		System.out.println();
		System.out.println("Folgende Hardware muss bei Folgefahrten noch verteilt werden:");
		for (Map.Entry<Hardware, Integer> hardware : mapMitHardware.entrySet()) {
			System.out.println(hardware.getValue() + " x " + hardware.getKey().getName() + " " + hardware.getKey().getNutzwertJeKg());
		}
	}
	
}
