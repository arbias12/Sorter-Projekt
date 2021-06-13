/**
 * Die Klasse MergeSort sortiert ein Array mit MergeSort.
 * Der MergeSort teilt das zu sortierende Array in kleinere Arrays bis jeder einzelne Wert ein Array ist.
 * Dann werden diese miteinander verglichen und wieder zusammengefügt bis es wieder ein Array ist.
 * @author Patrick Fässler
 * @version 1.0
 * @since 2021-01-26
 */

public class MergeSort implements SorterInterface {
    /**
     * Theorie: https://de.wikipedia.org/wiki/Mergesort#Implementierung
     * YT: https://www.youtube.com/watch?v=iMT7gTPpaqw&t=389s
     */

    private double loopCnt;
    private double compCnt;
    private double time;
    private double memory;

    public MergeSort(){}

    /**
     * Sortiert ein Array mit dem MergeSort Algorythmus
     * @param liste Das zu sortierende Array
     * @param length länge des zu sortierenden Arrays
     */
    public void mergeSort(int[] liste, int length) {
        //prüft ob das Array schon sortiert ist
        if(length <= 1) {
            compCnt++;
            return;
        } else {
            int mid = length / 2;
            int[] left = new int[mid];
            int[] right = new int[length - mid];

            //Erstellt die Linke Hälfte des vorherigen Arrays
            for(int i = 0; i < mid; i++) {
                left[i] = liste[i];
                loopCnt++;
                compCnt++;
            }

            //Erstellt die rechte Hälfte des vorherigen Arrays
            for(int i = mid; i < length; i++) {
                right[i - mid] = liste[i];
                loopCnt++;
                compCnt++;
            }

            mergeSort(left, left.length);
            mergeSort(right, right.length);

            //gibt das zusammengefügte sortierte Array zurück
            merge(liste, left, right, mid, length - mid);
            compCnt++;
        }
    }

    /**
     * Fügt die sortierten kleinen Listen zusammen
     * @param liste Ursprüngliche Liste die sortiert werden soll
     * @param left linke Hälfte des vorherigen Arrays
     * @param right rechte Hälfte des vorherigen Arrays
     * @param l Länge der linken Hälfte im ursprünlichen Array
     * @param r Länge der rechten Hälfte im urspünglichen Array
     */
    public void merge(int[] liste, int[] left, int[] right, int l, int r){

        int i = 0, j = 0, k = 0;

        //Vergleicht die kleineren Arrays mteinander
        while(i < l && j < r) {
            if (left[i] <= right[j]) {
                liste[k] = left[i];
                k++;
                i++;
                compCnt++;
            } else {
                liste[k] = right[j];
                k++;
                j++;
                compCnt++;
            }
            loopCnt++;
            compCnt++;
        }

        //Fügt das Linke Array in das grössere Array
        while(i < l) {
            liste[k] = left[i];
            k++;
            i++;
            loopCnt++;
            compCnt++;
        }

        //Fügt das Rechte Array in das grössere Array
        while(j < r) {
            liste[k] = right[j];
            k++;
            j++;
            loopCnt++;
            compCnt++;
        }
    }

    @Override
    public void sort (int[] liste, double[] MessArray, String auswahl) {

        MessArray = new double[4];

        double watchBegin = System.nanoTime();

        mergeSort(liste, liste.length);

        double watchEnd = System.nanoTime();
        time = watchEnd - watchBegin;
        memory = Runtime.getRuntime().totalMemory();

        MessArray[0] = loopCnt;
        MessArray[1] = compCnt;
        MessArray[2] = time;
        MessArray[3] = memory;

        System.out.println("Schleifendurchgänge: " + MessArray[0]);
        System.out.println("Vergleiche: " + MessArray[1]);
        System.out.println("Laufzeit für den Sort: " + MessArray[2]);
        System.out.println("Speicherkapazität: " + MessArray[3]);

    }
}
