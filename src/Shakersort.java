/**
 * Die Klasse sortiert ein Array mit einem Shakersort
 *
 * @author Kevin Bertolini
 * @verison 1
 * @since 19.01.2021
 *
 *
 * Was ist ein Shakersort?
 * Der Begriff Shakersort bezeichnet einen stabilen Sortieralgorithmus, der eine Menge von linear angeordneten Elementen (z. B. Zahlen) der Grösse nach sortiert.
 * https://de.wikipedia.org/wiki/Shakersort
 *
 *
 * Hilfe beim Programmieren
 * Ich hab einen Teil des Codes von hier abgeguckt, da ich Schwierigkeiten habe.
 * https://javabeginners.de/Algorithmen/Sortieralgorithmen/Shakersort.php
 */





// Implementiert von der Klasse SorterInterface von Arbias
public class Shakersort implements SorterInterface {

    // loop und comp Counter
    private double loopCnt = 0;
    private double compCnt = 0;
    private int shak;







    // Überschreibung der Methoden des SorterInterfaces

    @Override
    // Sort Methode von Sorterinterface
    public void sort(int[] values, double[] MessArray, String auswahl) {
        MessArray = new double[4];
        int i = 0, l = values.length;

        double start = System.nanoTime();
        while (i < l) {
            loopCnt++; compCnt++;
            for (int j = i; j < l - 1; j++) {
                loopCnt++; compCnt++;

                if (values[j] > values[j + 1]) {

                    shak = values[j];

                    values[j] = values[j + 1];

                    values[j + 1] = shak;

                }
                compCnt++;
            }
            loopCnt++; compCnt++;
            l--;
        }
        compCnt++;

        double finish = System.nanoTime();
        double zeit = finish - start; // Zeiteffizenz
        double memory = Runtime.getRuntime().totalMemory(); // benötigter Speicher

        MessArray[0] = loopCnt; // hier werden die Endresultate ins Array gespeichert
        MessArray[1] = compCnt;
        MessArray[2] = zeit;
        MessArray[3] = memory;


        System.out.println("Schleifendurchgänge: " + MessArray[0]); // ab hier die Ausgabe der Werte
        System.out.println("Vergleiche: " + MessArray[1]);
        System.out.println("Laufzeit für den Sort: " + MessArray[2]);
        System.out.println("Speicherkapazität: " + MessArray[3]);


    }

}

