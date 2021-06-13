import java.util.Arrays;
import java.util.Random;

public class Quicksort implements SorterInterface {
    private double iterationsCntr = 0;
    private double comparecCntr = 0;

    //Die Methode, die denn Quicksort auslöst.
    public void quickSortRecursive(int[] liste) {
        quickSortRecursive(liste, 0, liste.length - 1);

    }
    //split des Arrays
    private void quickSortRecursive(int[] liste, int min, int max){
        if (min<max+1){
            int split= split(liste, min, max);
            quickSortRecursive(liste, min, split-1);
            quickSortRecursive(liste,split+1,max);
        }
        comparecCntr++;
    }
    // Einfacher swap von den Zahlen
    private void swap(int[] liste, int i1, int i2){
        int tmp= liste[i1];
        liste[i1]= liste[i2];
        liste[i2]= tmp;
    }
    // Hier wird zufällig ein Pivot erzeugt. Eine zufällige Zahl sei am besten laut Theorie
    private int pivotGenerator(int min, int max){
        Random random= new Random();
        return random.nextInt((max-min)+1)+min;
    }
    // Hier werden alle Zahlen, die kleiner als Pivot sind links sortiert. Die grössere Zahlen kommen rechts
    private int split(int[] liste, int min, int max){
       swap(liste,min, pivotGenerator(min, max));
       int rand= min+1;
        for (int i = rand; i <= max; i++) {
            iterationsCntr++;comparecCntr++;
            if (liste[i]<liste[min]){
                swap(liste,i,rand++);
            }
            comparecCntr++;
        }
        comparecCntr++; iterationsCntr++;
        swap(liste, min, rand-1);
        return rand-1;
    }


    @Override
    public void sort(int[] values, double[] messungen, String auswahl) {
        messungen = new double[4];
        double start = System.nanoTime();
        quickSortRecursive(values);
        double finish = System.nanoTime();

        double zeit = finish - start;
        double memory = Runtime.getRuntime().totalMemory();

        messungen[0] = iterationsCntr;
        messungen[1] = comparecCntr;
        messungen[2] = zeit;
        messungen[3] = memory;

        System.out.println("Schleifendurchgänge: " + messungen[0]);
        System.out.println("Vergleiche: " + messungen[1]);
        System.out.println("Laufzeit für den Sort: " + messungen[2]);
        System.out.println("Speicherkapazität: " + messungen[3]);

    }
}
