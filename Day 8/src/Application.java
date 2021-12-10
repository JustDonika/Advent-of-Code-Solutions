import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(System.currentTimeMillis());
        ArrayList<String> numbers = new ArrayList<>();
        Scanner sc = new Scanner(new File("input.txt"));
        int sum = 0;
        while(sc.hasNext()){
            String s = sc.nextLine();
            Scanner innerScan = new Scanner(s);
            HashMap<Character, ArrayList<Integer>> mappings = new HashMap<>();
            mappings.put('a', new ArrayList<Integer>());mappings.put('b', new ArrayList<Integer>());mappings.put('c', new ArrayList<Integer>());mappings.put('d', new ArrayList<Integer>());mappings.put('e', new ArrayList<Integer>());mappings.put('f', new ArrayList<Integer>());mappings.put('g', new ArrayList<Integer>());
            ArrayList<String> savedFives = new ArrayList<>();
            ArrayList<String> savedSixes = new ArrayList<>();
            s = innerScan.next();
            while(!s.equals("|")){
                if(s.length()==2) {
                    for (char c : s.toCharArray())
                        mappings.get(c).add(1);
                }
                else if(s.length()==4)
                    for(char c : s.toCharArray())
                        mappings.get(c).add(4);
                else if(s.length()==3)
                    for(char c : s.toCharArray())
                        mappings.get(c).add(7);
                else if(s.length()==7)
                    for(char c : s.toCharArray())
                        mappings.get(c).add(8);
                //save 5 and 6 length ones for later
                if(s.length()==5)
                    savedFives.add(s);
                else if(s.length()==6)
                    savedSixes.add(s);
                s = innerScan.next();
            }

            //Handle saved with only knowledge of 8, 7, 4, and 1

            //Only 8 has a g, but 8 is also c exclusive; all four share an 'a' (but also sadly a 'b')
            //Firstly, we'll separate out the non-g
            ArrayList<String> savedGs = new ArrayList<>();
            for(String unclear : savedSixes){
                int numEightExclusiveMatches=0;
                for(char c : unclear.toCharArray()) {
                    if (mappings.get(c).contains(8) && !mappings.get(c).contains(7) && !mappings.get(c).contains(4) && !mappings.get(c).contains(1))
                        numEightExclusiveMatches++;
                }
                if(numEightExclusiveMatches!=2)
                    for (char c : unclear.toCharArray()) {
                        if (!mappings.get(c).contains(9))
                            mappings.get(c).add(9);
                    }
                else
                    savedGs.add(unclear);

            }
            //Now sorting out the 2 g's: one has an a, one has an f. 7 has an f and not an a; as such we can determine on this.
            for(String unclear : savedGs){
                int numInFour = 0;
                for(char c : unclear.toCharArray())
                    if(mappings.get(c).contains(7))
                        numInFour++;
                if(numInFour==3)
                    for (char c : unclear.toCharArray()) {
                        if (!mappings.get(c).contains(0))
                            mappings.get(c).add(0);
                    }
                else
                    for (char c : unclear.toCharArray())
                        if(!mappings.get(c).contains(6))
                            mappings.get(c).add(6);
            }
            ArrayList<String> savedNonGs = new ArrayList<>();
            //At this stage we have 0, 1, 4, 6, 7, 8, and 9. One of the fives has ag, be, and ba. As such, I would like to eliminate the g.
            for(String unclear : savedFives){
                //9 fully encapsulates the two non-g's
                int numInNine = 0;
                for(char c : unclear.toCharArray()) {
                    if (mappings.get(c).contains(9))
                        numInNine++;
                }
                if(numInNine!=5)
                    for (char c : unclear.toCharArray())
                        mappings.get(c).add(2);
                else
                    savedNonGs.add(unclear);
            }

            //Separate five and three. Two shares four letters with three and only three with five.
            for(String unclear : savedNonGs) {
                int numInTwo = 0;
                for (char c : unclear.toCharArray())
                    if (mappings.get(c).contains(2))
                        numInTwo++;
                if (numInTwo == 4)
                    for (char c : unclear.toCharArray())
                        mappings.get(c).add(3);
                else
                    for (char c : unclear.toCharArray())
                        mappings.get(c).add(5);
            }
            int currentNumber=0;
            //Now to translate output
            while(innerScan.hasNext()) {
                currentNumber*=10;
                s=innerScan.next();
                ArrayList<Integer> validPossibility = new ArrayList<>(){{add(0); add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);}};
                if(s.length()==2)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(0); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);}});
                else if(s.length()==3)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(0); add(2); add(3); add(4); add(5); add(6); add(1); add(8); add(9);}});
                else if(s.length()==4)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(0); add(2); add(3); add(1); add(5); add(6); add(7); add(8); add(9);}});
                else if(s.length()==7)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(0); add(2); add(3); add(4); add(5); add(6); add(7); add(1); add(9);}});
                else if(s.length()==5)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(0); add(1); add(4); add(6); add(7); add(8); add(9);}});
                else if(s.length()==6)
                    validPossibility.removeAll(new ArrayList<Integer>(){{add(1); add(2); add(3); add(4); add(5); add(7); add(8);}});
                for(char c : s.toCharArray()){
                    ArrayList<Integer> removeable = new ArrayList<>();
                    for(int i : validPossibility){
                        if(!mappings.get(c).contains(i))
                            removeable.add(i);
                    }
                    validPossibility.removeAll(removeable);
                }
                currentNumber+=validPossibility.get(0);
            }
            sum+=currentNumber;
            //Part One
            //while(!s.equals("|")){
            //    s= innerScan.next();
            //}
            //while(innerScan.hasNext()) {
            //    s=innerScan.next();
            //    numbers.add(s);
            //}


        }
        System.out.println(sum);
        //Also part one
        //System.out.println(numbers);
        //HashMap<Integer, Integer> frequency = new HashMap<>();
        //for(String s : numbers){
        //    if(!frequency.containsKey(s.length()))
        //        frequency.put(s.length(), 1);
        //    else
        //        frequency.put(s.length(), frequency.get(s.length())+1);
        //}
        //System.out.println(frequency);
        System.out.println(System.currentTimeMillis());
    }

}
