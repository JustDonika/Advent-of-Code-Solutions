import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> fish = new ArrayList<>();
        Scanner sc = new Scanner(new File("test.txt"));
        while(sc.hasNextInt()){
            fish.add(sc.nextInt());
        }
        //Part One
        //for(int cycle=0; cycle<256; cycle++){
        //    for(int i=0; i<fish.size(); i++){
        //        if(fish.get(i)==0){
        //            fish.set(i, 6);
        //            //will decrement so 9 not 8
        //            fish.add(9);
        //        }
        //        else
        //            fish.set(i, fish.get(i)-1);
        //    }
        //}
        //System.out.println(fish.size());

        //Part Two
        long output = 0;
        HashMap<Integer, Long> fishTypes = new HashMap<>();
        for(int indFish : fish){
            if(fishTypes.containsKey(indFish)){
                fishTypes.put(indFish, fishTypes.get(indFish)+1);
            }
            else
                fishTypes.put(indFish, 1L);
        }
        System.out.println(fishTypes.values());
        for(int cycle=0; cycle<256; cycle++){
            HashMap<Integer, Long> fishTypesNext = new HashMap<>();
            for(int i : fishTypes.keySet()){
                if(i==0){
                    fishTypesNext.put(8, fishTypes.get(i));
                    fishTypesNext.put(6, fishTypes.get(i));
                }
                else
                    if(i>1 && fishTypesNext.containsKey(i-1))
                        fishTypesNext.put(i-1, fishTypes.get(i)+fishTypesNext.get(i-1));
                    else
                        fishTypesNext.put(i-1, fishTypes.get(i));
            }

            System.out.println(fishTypesNext);
            fishTypes=fishTypesNext;
        }
        for(Long l : fishTypes.values()){
            output+=l;
        }
        System.out.println(output);
    }

}
