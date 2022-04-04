import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main (String[] args) throws FileNotFoundException {
        Scanner overall = new Scanner(new File("input.txt"));
        //Take all randomly generated numbers
        List<Integer> inputs = new ArrayList<>();
        Scanner line = new Scanner(overall.nextLine());
        while(line.hasNext()){
            inputs.add(line.nextInt());
        }
        //Input all boards
        List<int[][]> boards = new ArrayList<>();
        while(overall.hasNext()){
            int[][] currBoard = new int[5][5];
            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++){
                    currBoard[x][y]=overall.nextInt();
                }
            }
            boards.add(currBoard);
        }
        //Find the shortest number required to get a row or column
        int index=4;
        boolean sufficient=false;
        ArrayList<int[][]> successfulBoardToRemove=new ArrayList<>();
        while(true){
            for(int[][] board : boards) {
                //For each column
                for(int x=0; x<5; x++){
                    if(inputs.subList(0, index).contains(board[x][0]) && inputs.subList(0, index).contains(board[x][1]) && inputs.subList(0, index).contains(board[x][2]) && inputs.subList(0, index).contains(board[x][3]) && inputs.subList(0, index).contains(board[x][4])){
                        System.out.println(Arrays.deepToString(board));
                        System.out.println(inputs.subList(0, index));
                        int tally = 0;
                        for(int[] currLine : board){
                            for(int currDig : currLine){
                                if(!inputs.subList(0,index).contains(currDig))
                                tally += currDig;
                            }
                        }
                        successfulBoardToRemove.add(board);
                        System.out.println(tally);
                    }
                }
                for(int y=0; y<5; y++){
                    if(inputs.subList(0, index).contains(board[0][y]) && inputs.subList(0, index).contains(board[1][y]) && inputs.subList(0, index).contains(board[2][y]) && inputs.subList(0, index).contains(board[3][y]) && inputs.subList(0, index).contains(board[4][y])){
                        System.out.println(Arrays.deepToString(board));
                        System.out.println(inputs.subList(0, index));
                        int tally = 0;
                        for(int[] currLine : board){
                            for(int currDig : currLine){
                                if(!inputs.subList(0,index).contains(currDig))
                                    tally += currDig;
                            }
                        }
                        successfulBoardToRemove.add(board);
                        System.out.println(tally);
                    }
                }
            }
            index++;
            boards.removeAll(successfulBoardToRemove);
        }
    }
}