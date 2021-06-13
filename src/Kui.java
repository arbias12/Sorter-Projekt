import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Programmieraufgaben
 *
 * @author Arbias imeri
 * @version 1.2
 * @since 20.01.2021
 * Das ist die KUI - Klasse. Diese Klasse ist dafür zuständig, dass man auswählen kann, wie viele Zahlen man sortieren möchte und mit welcher Sortmethode.
 * Diese Sortermethode wird dann ausgeführt. Das Array wird sortiert und es werden die Messwerte widergegeben.
 */
public class Kui implements ActionListener, SorterInterface {
    private String name;   //Ist der Name des KUI's.
    private Scanner scanner;  //Ist der Scanner der Klasse, mit dem man die Zahlen einscannt.
    private int[] arrayFuerZahlen;  //Ist das Array, in dem die Zahlen gespeichert werden.
    private double[] arrayFuerMessungen; //Ist das Array für die Messungen, die hier gespeichert werden.
    private JFrame jFrame;  //Ist das Hauptbauobjekt des GUI.
    private JComboBox jComboBoxDerZahlen;  //Diese Combobox gibt dir die Möglichkeit, im Gui zwischen den Zahlen zu wählen
    private JComboBox jComboBoxDerSorter;  //Diese Combobox gibt dir die Möglichkeit, im Gui zwischen den Sortmethoden zu wählen zu wählen
    private JButton computeBTN;  //Der JButton ist der "absenden" Button, mit dem du die Aktion bestätigen kannst.
    private  static int i = 0;  //Wird für die While-Schleie für die Speicherung der Zahlen für das Array verwendet.
    private SorterInterface sorterInterface;  //Wird für das Factoring zwischen den Sortklassen verwendet.

    /**
     * Wird verwendet, um Methoden in der Main Klasse ausführen zu können.
     * @param name ist der Name des KUI's
     */
    public Kui(String name){
        this.name = name;
    }

    /**
     * Ist die Main-Klasse, in der alles ausgeführt wird.
     * @param args ist der Paramter der Main-Klasse
     */

    public static void main(String[] args) {
        Kui kui = new Kui("Arbias");

        String[] waehlen = {"Bitte wähle aus", "10", "100", "1000"};  //Wahl der Zahlen
        String[] wahl2 = {"Bitte wähle aus" , "Quicksort" , "Bubblesort", "InsertionSort" , "Shakersort" , "MergeSort"}; //Wahl der Sorter
        kui.setjFrame(new JFrame("Sorter"));  //Ein neues JFrame mit dem Namen "KUI" wird erzeugt
        kui.setBoxZahlen(new JComboBox(waehlen));  //Neue Combobox wird erstellt
        kui.setBoxSort(new JComboBox(wahl2));
        kui.setButton(new JButton("absenden"));  //Neuer Button wird erzeugt.


        kui.getjFrame().setSize(300, 300);  //Definition der Grösse des GUI's
        kui.getjFrame().getContentPane().add(kui.getBoxZahlen(), BorderLayout.CENTER);   //Combobox wird dem Gui hinzugefügt.
        kui.getjFrame().getContentPane().add(kui.getBoxSort(), BorderLayout.EAST);
        kui.getjFrame().getContentPane().add(kui.getButton(), BorderLayout.AFTER_LAST_LINE);  //Button wird dem Gui hinzugefügt
        kui.getjFrame().pack();

        kui.getjFrame().setVisible(true);   //Sichtbarkeit wird gewährleistet

        kui.getButton().addActionListener(new ActionListener() {

            /**
             * Diese Methode ist da, dass wenn man den Button drückt, eine entsprechende Tätigkeit ausgeüfhrt wird.
             * @param e ist der Parameter von der ActionEvent Klasse.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e){
                if (kui.getBoxSort().getSelectedIndex()!=0) {
                    String wahl = String.valueOf(kui.getBoxZahlen().getSelectedItem());  //Die Wahl der Zahl, die man auswählt
                    String wahlsort = String.valueOf(kui.getBoxSort().getSelectedItem());   //Die Wahl der Sortmöglichkeit, die man ausgewählt hat.
                    if ("10".equals(wahl)){
                        kui.setArrayFuerZahlen(new int[10]);  //Erzeugung neues Arrays mit 10 Plätzen
                        File file = new File("./files/10Digits.dat");  //Erzeugung neues File und Holung des Files mit einem relativen Pfad.
                        kui.scan(file, wahlsort);  //Speicherung der Zahlen im Array
                        kui.getjFrame().dispose();  //Schliessung des GUI's
                    }else if ("100".equals(wahl)){
                        kui.setArrayFuerZahlen(new int[100]);
                        File file = new File("./files/100Digits.dat");
                        kui.scan(file, wahlsort);
                        kui.getjFrame().dispose();
                    }else if ("1000".equals(wahl)){
                        kui.setArrayFuerZahlen(new int[1000]);
                        File file = new File("./files/1000Digits.dat");
                        kui.scan(file, wahlsort);
                        kui.getjFrame().dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Wähl eine Sortiermöglichkeit aus"); //Falls keine Sortermöglichkeit ausgewählt wird, bekommt der User eine Meldung.
                    }
            }
        });

    }


    /**
     * Diese Sort Methode wird mit Hilfe eines Interfaces implementiert. Diese Methode wird von allen Sorter dieses Projektes implementiert. Das ist von Wichtigkeit
     * da man sich zusammen abstimmen muss und die Sortierung bei jedem mit den gleichen Vorgaben ausgeführt wird.
     * @param zahlenArray ist das Array, das die gespeicherten Zahlen beinhaltet.
     * @param MessArray ist das Array, das die Messungen beinhaltet.
     * @param wahl ist die Wahl der Sortiermöglichkeit.
     */
    @Override
    public void sort(int[] zahlenArray, double[] MessArray, String wahl)
    {
       if ("Bubblesort".equals(wahl)){
           sorterInterface = new Bubblesort();
           sorterInterface.sort(zahlenArray, MessArray, wahl);

       }else if ("Quicksort".equals(wahl)){
           sorterInterface = new Quicksort();
           sorterInterface.sort(zahlenArray, MessArray, wahl);

        }else if("InsertionSort".equals(wahl)){
            sorterInterface = new InsertionSort();
            sorterInterface.sort(zahlenArray, MessArray, wahl);

        }else if ("MergeSort".equals(wahl)){
           sorterInterface = new MergeSort();
           sorterInterface.sort(zahlenArray, MessArray, wahl);
       }else if ("Shakersort".equals(wahl)){
           sorterInterface = new Shakersort();
           sorterInterface.sort(zahlenArray, MessArray, wahl);
       }


    }

    /**
     * Set-Methode für das aRRAY
     * @param arrayFuerZahlen Array der gespeicherten Zahlen
     */
    public void setArrayFuerZahlen(int[] arrayFuerZahlen){
        this.arrayFuerZahlen = arrayFuerZahlen;
    }

    /**
     * Ist dafür da, dass man das Array zurückgibt.
     * @return Das Zahlen-Array
     */
    public int[] getArrayFuerZahlen(){
        return arrayFuerZahlen;
    }

    /**
     * Wird verwendet, damit man die Forderungen des Interfaces ActionListener erfüllt, nämlich die Implementation dieser Methode.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Wird verwendet, damit man das JFrame zurückgeben kann.
     * @return das JFrame
     */
    public JFrame getjFrame(){
        return jFrame;
    }

    /**
     * Ist die Set-Methode des JFrame, wird im Programm für die Erstellung des JFrames benutzt.
     * @param jFrame JFrame des Gui's
     */
    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    /**
     * Ist die Set-Methode für die Combobox
     * @param jComboBox ist die Combobox für die Zahlen
     */
    public void setBoxZahlen(JComboBox jComboBox) {
        this.jComboBoxDerZahlen = jComboBox;
    }

    /**
     * Ist die Set-Methode für die Combobox
     * @param jComboBox2 ist die Combobox für die Sortierungsmöglichkeiten
     */
    public void setBoxSort(JComboBox jComboBox2) {
        this.jComboBoxDerSorter = jComboBox2;
    }

    /**
     * Wird verwendet, um die Combobox der Sorter zurückzugeben
     * @return die Combobox der Sortierungsmöglichkeiten
     */
    public JComboBox getBoxSort() {
        return jComboBoxDerSorter;
    }

    /**
     * Ist die Geter-Methode für die Combobox der Zahlen
     * @return die Combobox der Zahlen
     */
    public JComboBox getBoxZahlen() {
        return jComboBoxDerZahlen;
    }

    /**
     * Ist die Set-Methode des Buttons. Wird im Programm für die Erzeugung des Buttons benutzt.
     * @param computeBTN ist der Button des JFrames.
     */
    public void setButton(JButton computeBTN) {
        this.computeBTN = computeBTN;
    }

    /**
     * Ist die Methode, die den Button returnt.
     * @return den Button
     */
    public JButton getButton() {
        return computeBTN;
    }

    /**
     * Diese Methode wird für die Speicherung der Zahlen verwendet. Hierbei ist die Variable i von bedeutung, die in der Klasse initalisiert ist. Auch
     * wird in dieser Methode die Methode "sort* aufgerufen. Das hat den Vorteil, dass man den Code "kui.sort" nicht mehrmals wiederholend codieren muss.
     * @param file ist das File, in dem die Zahlen gespeichert sind
     * @param wahl ist die Wahl der Sortierungsmöglichkeit
     */
    public void scan(File file, String wahl){
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextInt()){
                int zahl = scanner.nextInt();
                arrayFuerZahlen[i++] = zahl;
            }
            sort(arrayFuerZahlen, arrayFuerMessungen, wahl);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


}
