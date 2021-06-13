/**
 * ProjektMaurizi
 *
 * @author Arbias imeri
 * @version 1.2
 * @since 26.01.2021
 *
 */

public class Bubblesort implements SorterInterface {

    public Bubblesort() {}


        @Override
        public void sort ( int[] values, double[] MessArray, String auswahl){
            MessArray = new double[4];
            double iterationCtn = 0;
            double compCtn = 0;

            int cntr = 0;
            int max = values.length;
            int tmp;

            boolean sortiert = false;
            double start = System.nanoTime();
            while (sortiert == false) {
                sortiert = true;
                for (int i = 0; i < max - 1; i++) {
                    compCtn++;
                    iterationCtn++;
                    if (values[i] > values[i + 1]) {
                        tmp = values[i];
                        values[i] = values[i + 1];
                        values[i + 1] = tmp;
                        sortiert = false;
                        cntr++;
                    }
                    compCtn++;
                }
                iterationCtn++;
                compCtn++;
            }
            double finish = System.nanoTime();
            double timeCtn = finish - start;
            double memoryCtn = Runtime.getRuntime().totalMemory();

            System.out.println("");

            MessArray[0] = iterationCtn;
            MessArray[1] = compCtn;
            MessArray[2] = timeCtn;
            MessArray[3] = memoryCtn;

            System.out.println("Schleifendurchgänge: " + MessArray[0]);
            System.out.println("Vergleiche: " + MessArray[1]);
            System.out.println("Laufzeit für den Sort: " + MessArray[2]);
            System.out.println("Speicherkapazität: " + MessArray[3]);



        }




}
