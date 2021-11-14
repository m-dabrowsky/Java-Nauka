import javax.security.auth.login.CredentialException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zad4 {

    public static void main(String[] args) throws IOException {

        List<String> imieNazwisko = new ArrayList<String>();

        FileReader fileReader = new FileReader("Names.txt");
        BufferedReader reader = new BufferedReader(fileReader);

        String readOneLineFromFile = reader.readLine();
        int numOfNames = 0;
        while (readOneLineFromFile != null){
            imieNazwisko.add(readOneLineFromFile);
            readOneLineFromFile = reader.readLine();
            numOfNames++;
        }

        reader.close();

        System.out.println("Znaleziono imion: " + numOfNames);
        for (String name: imieNazwisko) {
            System.out.println(name);
        }

    }



}
