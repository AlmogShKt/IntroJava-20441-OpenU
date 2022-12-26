// class name: Knight
// Author: Almog Shtaigmann
// Date: 15.02.2022
/* 
This class will recive an knight position and will calculate and
-> print all the possible moves
*/
import java.util.Scanner; public class Knight{
public static void main (String [] args)
{   
    // All the constant in the program:

    // The knight movment is 2 steps up/down or right/left and then 1 step right/left or up/down - respectively.
    final int MOVE_2_STEPS = 2; // right/left; up/down
    final int MOVE_1_STEP = 1; // right/left; up/down

    final int START_R_C = 1; // The row/col starts at 1(R=row,C=Column)
    final int END_R_C = 8; // The row/col ends at 8 (R=row,C=Column)

    // Print instructions and recive the knight position:
    Scanner scan = new Scanner (System.in);
    System.out.println ("This program reads two integers which represent the knight's location on the chess board: "); 

    System.out.println ("Please enter the number of row");
    int row = scan.nextInt(); // row is right->left on the chess board

    System.out.println ("Please enter the number of column");
    int col = scan.nextInt(); //col is up->down on the chess board

    //First - the programm will check if the position is legal (row&col is within the range 1-8)
    if((row >= START_R_C && row <= END_R_C) && (col >= START_R_C && col <= END_R_C)){
        //if the input "pass" the check, we can start to calc' the moves
        System.out.println("Moves:");

        // The algorithm to find the possible position:
        //1. check if you can move 2 steps right/left or up/down -> respectively
        //1.1. if you can - check if you can move 1 step up/down or right/left -> respectively
        //1.1.1. if you can - print the position (row/col +- 2 -> col/row +- 1) -> respectively
        //1.2. if not check the next condition

        //step 1. -> checking 2 step right and 1 step up/down
        if(row + MOVE_2_STEPS <= END_R_C){
            if(col + MOVE_1_STEP <= END_R_C){ // step 1.1.
                System.out.println((row + MOVE_2_STEPS) + " " + (col + MOVE_1_STEP)); // step 1.1.1.
            }
            if(col - MOVE_1_STEP >= START_R_C){ 
                System.out.println((row + MOVE_2_STEPS) + " " + (col - MOVE_1_STEP));
            }
        }

        //step 1. -> checking 2 step left and 1 step up/down
        if(row - MOVE_2_STEPS >= START_R_C){ 
            if(col + MOVE_1_STEP <= END_R_C){ 
                System.out.println((row - MOVE_2_STEPS) + " " + (col + MOVE_1_STEP));
            }
            if(col - MOVE_1_STEP >= START_R_C){ 
                System.out.println((row - MOVE_2_STEPS) + " " + (col - MOVE_1_STEP));
            }
        }

        //step 1. -> 2 steps up and 1 step right/left
        if(col - MOVE_2_STEPS >= START_R_C){
            if(row + MOVE_1_STEP <= END_R_C){
                System.out.println((row + MOVE_1_STEP) + " " + (col - MOVE_2_STEPS));
            }
            if(row - MOVE_1_STEP >= START_R_C){
                System.out.println((row - MOVE_1_STEP) + " " + (col - MOVE_2_STEPS));
            }
        }

        //step 1. -> 2 steps down and 1 step right/left
        if(col + MOVE_2_STEPS <= END_R_C){
            if(row + MOVE_1_STEP <= END_R_C){
                System.out.println((row + MOVE_1_STEP) + " " + (col + MOVE_2_STEPS));
            }
            if(row - MOVE_1_STEP >= START_R_C){
                System.out.println((row - MOVE_1_STEP) + " " + (col + MOVE_2_STEPS));
            }
        }

    } else  // the input is illegal (the value is not within the range of 1-8)
        System.out.println("input is illegal");
    
        scan.close(); // closing scan
   } // end of method main
} //end of class Knight