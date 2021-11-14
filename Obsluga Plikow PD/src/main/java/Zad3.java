import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;

public class Zad3 {


    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("Numbers.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String readOneLineFromFile = bufferedReader.readLine();
        int sizeArray = Integer.parseInt(readOneLineFromFile);

        int[] tab = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            tab[i] = Integer.parseInt(bufferedReader.readLine());
        }

        bufferedReader.close();


        int sum = 0;

        for (int j = 0; j < tab.length; j++) {
            sum += tab[j];
        }
        System.out.println("Suma liczb w tablicy to : " + sum);



    }
}
