import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.currentTimeMillis;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(currentTimeMillis());
        HashMap<ArrayList<Integer>, Integer> occupiedSquares = new HashMap<>();
        Set<ArrayList<Integer>> doubleOccupied = new HashSet<>();
        Scanner sc = new Scanner(new File("input.txt"));
        while(sc.hasNext()){
            int x1 = sc.nextInt(); int y1 = sc.nextInt(); sc.next(); int x2 = sc.nextInt(); int y2 = sc. nextInt();
            if(x1==x2){
                for(int y = Math.min(y1, y2); y<=Math.max(y1, y2); y++){
                    ArrayList<Integer> coords = new ArrayList<>(); coords.add(x1); coords.add(y);
                    if(occupiedSquares.containsKey(coords)){
                        doubleOccupied.add(coords);
                    }
                    else{
                        occupiedSquares.put(coords, 1);
                    }
                }
            }
            else if(y1==y2){
                for(int x = Math.min(x1, x2); x<=Math.max(x1, x2); x++){
                    ArrayList<Integer> coords = new ArrayList<>(); coords.add(x); coords.add(y1);
                    if(occupiedSquares.containsKey(coords)){
                        doubleOccupied.add(coords);
                    }
                    else{
                        occupiedSquares.put(coords, 1);
                    }
                }
            }
            //diagonals
            else{
                //top left to bottom right
                if(x1<x2 && y1<y2){
                    for(int x = 0; x<=Math.abs(x1-x2); x++){
                        ArrayList<Integer> coords = new ArrayList<>(); coords.add(x1+x); coords.add(y1+x);
                        if(occupiedSquares.containsKey(coords)){
                            doubleOccupied.add(coords);
                        }
                        else{
                            occupiedSquares.put(coords, 1);
                        }
                    }
                }
                else if(x2<x1 && y2<y1){
                    for(int x = 0; x<=Math.abs(x1-x2); x++){
                        ArrayList<Integer> coords = new ArrayList<>(); coords.add(x2+x); coords.add(y2+x);
                        if(occupiedSquares.containsKey(coords)){
                            doubleOccupied.add(coords);
                        }
                        else{
                            occupiedSquares.put(coords, 1);
                        }
                    }
                }
                //bottom left to top right
                else if(x1<x2 && y2<y1){
                    for(int x = 0; x<=Math.abs(x1-x2); x++){
                        ArrayList<Integer> coords = new ArrayList<>(); coords.add(x1+x); coords.add(y1-x);
                        if(occupiedSquares.containsKey(coords)){
                            doubleOccupied.add(coords);
                        }
                        else{
                            occupiedSquares.put(coords, 1);
                        }
                    }
                }
                else if(x2<x1 && y1<y2){
                    for(int x = 0; x<=Math.abs(x1-x2); x++){
                        ArrayList<Integer> coords = new ArrayList<>(); coords.add(x2+x); coords.add(y2-x);
                        if(occupiedSquares.containsKey(coords)){
                            doubleOccupied.add(coords);
                        }
                        else{
                            occupiedSquares.put(coords, 1);
                        }
                    }
                }
            }
        }
        System.out.println(doubleOccupied.size());
        System.out.println(currentTimeMillis());
    }
}
