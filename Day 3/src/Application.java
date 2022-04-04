import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    //Part One
    //public static void main (String[] args) throws FileNotFoundException {
    //  Scanner sc = new Scanner(new File("input.txt"));
    //  char[] currBinary = sc.next().toCharArray();
    //  int[] cumulative = new int[currBinary.length];
    //    while(sc.hasNext()) {
    //        for (int i=0; i<cumulative.length; i++) {
    //            if(currBinary[i]=='0')
    //                cumulative[i]--;
    //            else
    //                cumulative[i]++;
    //        }
    //        currBinary=sc.next().toCharArray();
    //    }
    //    //Negative values are majority zero, positve majority 1.
    //    System.out.println(Arrays.toString(cumulative));
    //    100100101010
    //  }

    //Part Two
    public static void main (String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        List<String> allBinaries = new ArrayList<>();
        while(sc.hasNext())
            allBinaries.add(sc.next());
        //Find Oxygen
        List<String> subset = new ArrayList<>(allBinaries);
        int index=0;
        int balance=0;
        while(subset.size()>1){
            for(String s : subset){
                if(s.charAt(index)=='0')
                    balance--;
                else
                    balance++;
            }
            if(balance>=0) {
                int finalIndex = index;
                subset.removeIf(s -> s.charAt(finalIndex) == '0');
            }
            else {
                int finalIndex = index;
                subset.removeIf(s -> s.charAt(finalIndex) == '1');
            }
            balance=0;
            index++;
        }
        System.out.println(Integer.parseInt(subset.get(0),2));

        //Find CO2
        subset = new ArrayList<>(allBinaries);
        index=0;
        balance=0;
        while(subset.size()>1){
            for(String s : subset){
                if(s.charAt(index)=='0')
                    balance--;
                else
                    balance++;
            }
            if(Math.abs(balance)<subset.size()) { //prevents removing all remaining values
                System.out.println(subset);
                if (balance >=0) {
                    int finalIndex = index;
                    subset.removeIf(s -> s.charAt(finalIndex) == '1');
                } else {
                    int finalIndex = index;
                    subset.removeIf(s -> s.charAt(finalIndex) == '0');
                }
            }
            balance=0;
            index++;
        }
        System.out.println(Integer.parseInt(subset.get(0),2));
    }
}