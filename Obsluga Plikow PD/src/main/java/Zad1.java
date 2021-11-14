import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Zad1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);


        int[] tab = new int[5];
        String[] imiona = {"marcin", "paweł", "mateusz"};


        System.out.println("Podaj liczby aby umieścić je w tablicy");

        for (int i = 0; i < tab.length; i++) {
            System.out.print("Podaj liczbę: ");
            int inputNum = scanner.nextInt();
            tab[i] = inputNum;
        }

        System.out.print("Tablica zawiera następujace liczby: ");
        for (int num:tab) {
            System.out.print(num + " ");
        }

        String fileName = "Liczby.txt";
        File file = new File(fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        for (int i = 1; i <= tab.length; i++) {
            printWriter.write("Liczba numer " + i + ":" + tab[i-1] + "\n");
            for (int j = 0; j < imiona.length; j++) {
                printWriter.write("Podano imie: " + imiona[j] + "\n");
                continue;
            }
        }



        printWriter.close();





    }


}
