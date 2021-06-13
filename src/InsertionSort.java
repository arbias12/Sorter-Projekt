/**
 * Diese Klasse sortiert ein Array mit einem InsertionSort
 *
 * @author Assvin Shanmuganathan
 * @version 2
 * @since 23.01.2021
 * <p>
 *
 * Der InsertionSort nimmt den ersten Wert in einem Array als Schlüssel. Danach vergleicht der KeyVal mit der linken Zahl.
 * Wenn der KeyVal kleiner ist als die linke Zahl, dann wird ein einfacher Swap durchgeführt.
 * Wenn es links vom Key keinen Wert mehr hat (oder KeyVal grösser als die linke Zahl ist), dann ist der neue KeyVal die nächste Zahl,
 * das noch nicht verglichen wurde.
 * <p>
 *
 * Worst-Case: Θ(n^2)
 * Worst-Case ist dem Fall, wenn das Array umgekehrt sortiert vorliegt. Dann
 * wird jeder Wert verklichen und die KeyVal ändert sich jedes Mal. Das linke Element vom KeyVal ist immer kleiner.
 * adurch ist die Laufzeit Θ(n^2)
 * <p>
 *
 * Average-Case: Θ(n^2)
 * Der Average-Case ist gleich wie der Worst-Case. Bei einer unsortierten Liste kann man schätzen, dass die Hälfte der
 * Zahlen auf der Linken Seite kleiner als der keyVal ist.
 * <p>
 *
 * Best-Case: Θ(n)
 * est-Case ist dann vorhanden, wenn bereits einige Zahlen sortiert sind. Dadurch muss der keyVal weniger Vergleiche
 * machen. Der schnellst mögliche Fall ist, wenn das Array ganz sortiert ist. Dadurch ist die Gesamtzeit nur (n-1) durläufe machen.
 * Schlussendlich ist bei beiden eine Laufzeit von Θ(n) vorhanden.
 * <p>
 *
 * Stabilität:
 * Der InsertionSort ist ein stabiles Sortierverfahren. Aber es könnte Probleme geben, wenn gleiche Zahlen mehrmals vorkommen.
 * <p>
 *
 * Strukturelemente:
 * Die For-Schleife ist da, um durch das Array zu gehen. Zudem speichert sie die Zahl in ein erstelltes Array.
 * Die While-Schleife sortiert solange, bis der linke Wert kleiner ist als der keyVal.
 */
public class InsertionSort implements SorterInterface {
    /**
     * QUELLEN:
     * https://dzone.com/articles/stability-insertion-sort
     * Theorie: https://de.khanacademy.org/computing/computer-science/algorithms/insertion-sort/a/analysis-of-insertion-sort
     * YT: https://www.youtube.com/watch?v=lCDZ0IprFw4&t=101s&ab_channel=JoeJames
     * Ich brauchte Hilfe bei der While-Schleife, weil ich nicht wusste, wie man den neuen keyVal bestimmt.
     *
     * <p>
     */


    public InsertionSort() {

    }

    /**
     * Durch die Implementation des Sorterinterfaces, muss man diese Methode implementierten. Hier wird das Array sortiert.
     * @param liste
     * @param MessArray
     * @param auswahl
     * @Return MessArray mit den 4 verlangten Werten
     */

    @Override
    public void sort(int[] liste, double[] MessArray, String auswahl) {
        double memoryCtn = 0;       // Memorycounter
        double timeCtn = 0;         // verbrauchte Zeit
        double iterationCtn = 0;    // Anzahl Durchgänge der Schleifen
        double compCtn = 0;         // Anzahl benötigte Vergleiche

        MessArray = new double[4]; // Ein Array, der die 4 Messwerte speichert.

        double watchBegin = System.nanoTime();


        int a = liste.length;

        for (int i = 1; i < a; i++) {
            int keyVal = liste[i];
            int b = i - 1;

            iterationCtn++;
            compCtn++;


            while (b >= 0 && liste[b] > keyVal) {
                liste[b + 1] = liste[b]; //das ist ein einfacher swap
                b = b - 1;
                iterationCtn++;
                compCtn++;

            }
            liste[b + 1] = keyVal;


        }
        iterationCtn++;
        compCtn++;

        double watchEnd = System.nanoTime();
        timeCtn = watchEnd - watchBegin; //Zeiteffizienz

        memoryCtn = Runtime.getRuntime().totalMemory(); //benötigter Speicher

        MessArray[0] = iterationCtn; // hier werden die Endresultate ins Array gespeichert
        MessArray[1] = compCtn;
        MessArray[2] = timeCtn;
        MessArray[3] = memoryCtn;


        System.out.println("Schleifendurchgänge: " + MessArray[0]); // ab hier die Ausgabe der Werte
        System.out.println("Vergleiche: " + MessArray[1]);
        System.out.println("Laufzeit für den Sort: " + MessArray[2]);
        System.out.println("Speicherkapazität: " + MessArray[3]);

    }

}
