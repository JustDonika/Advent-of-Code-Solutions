import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public static void main (String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("test.txt"));
        int tally = 0;
        int first = sc.nextInt();
        int second = sc.nextInt();
        int third = sc.nextInt();
        int current=0;
        while (sc.hasNext()) {
            current = sc.nextInt();
            if(current>first)
                tally++;
            first=second; second=third; third=current;
        }
        System.out.println(tally);
    }
}
