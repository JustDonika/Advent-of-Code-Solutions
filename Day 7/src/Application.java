import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> crabs = new ArrayList<>();
        Scanner sc = new Scanner(new File("input.txt")).useDelimiter(",");
        while(sc.hasNextInt()){
            crabs.add(sc.nextInt());
        }
        //skips 1000th so
        crabs.add(107);
        System.out.println(crabs);
        //Part One
        //The answer should be the median
        //Collections.sort(crabs);
        //System.out.println(crabs);
        //int median = 0;
        //if (crabs.size() % 2 == 1)
        //    median = (crabs.get((crabs.size() + 1) / 2 - 1));
        //else {
        //    double lower = crabs.get(crabs.size() / 2 - 1);
        //    double upper = crabs.get(crabs.size() / 2);
        //    median = (int) ((lower + upper) / 2.0);
        //}
        //Part Two
        for(int o = 400; o<1000; o++) {
            int fuel = 0;
            for (int i : crabs) {
                for(int fuelUsed = 1; fuelUsed<Math.abs(i-o)+1; fuelUsed++) {
                    fuel += fuelUsed;
                }
            }
            System.out.println(o);
            System.out.println(fuel);
        }
    }

}
