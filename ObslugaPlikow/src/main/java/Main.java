import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        /**
         * Utworzenie i zapis do pliku (Z NADPISYWANIEM)
         */
        // Nazwa pliku
        String fileName = "dane.txt";

        // Stworzenie obiektu klasy PrintWriter umieszczając w konstruktorze nazwę pliku
        // jeśli plik nie istnieje to zostanie on utworzony
        /*PrintWriter printWriter = new PrintWriter(fileName);

        // Dodanie tekstu do pliku (NADPISYWANIE)
        printWriter.write("Witaj Mój Pliku. ");
        printWriter.write("Obsługuje trudne pliki");

        // Zamknięcie pliku
        printWriter.close();*/



        /**
         * Dopisywanie treści do pliku
         */

        // Treść dopisywana
        int[] numbers = {1,2,4,5,7};

        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true); // true - treść do pliku będzie dopisywana, false - nadpisywanie całego pliku

        PrintWriter printWriter1 = new PrintWriter(fileOutputStream);

        for (int i = 0; i < numbers.length; i++) {
            printWriter1.write("Jestem liczbą numer: " + i + ", a moja wartość to: " + numbers[i] + "\n");
        }
        printWriter1.close();


        /**
         * Odczyt pliku
         */


        // Pośrednik do odczytu pliku
        FileReader fileReader = new FileReader(fileName);


        //
        BufferedReader reader = new BufferedReader(fileReader);

        // Zapis tekstu do zmiennej (Odczyt tylko pierwszej linii)
        String readOneLineFromFile = reader.readLine();


        // Odczyt wielu linii tekstu, a nie tylko jednej linii (jeżeli pierwsza linia jest różna od nulla to wejdzie w pętle)
        while(readOneLineFromFile != null){
            // Wyświetlenie tekstu na konsoli
            System.out.println("Wczytałem plik o tekście: " + readOneLineFromFile);
            readOneLineFromFile = reader.readLine();
        }
        
        // Zamknięcie pliku
        reader.close();




    }

}
