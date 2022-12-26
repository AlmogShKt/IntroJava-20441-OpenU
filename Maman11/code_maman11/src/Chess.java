// class name: Chess
// Author: Almog Shtaigmann
// Date: 17.02.2022
/*
 This class will recive 2 chessman types and positions and will check if there
 -> is any threats from one on the chessman against the other
 */
import java.util.Scanner;

public class Chess{
    public static void main (String [] args){

        //all the constant in the program  :
        final int HYPOTENUSE_LENGTH = 5; // The hypotenuse length in right-angel triangle will be always 5 

        final int START_R_C = 1; // The row/col1 starts at 1
        final int END_R_C = 8; // The row/col1 ends at 8

        final char KNIGHT_CHAR = 'k';
        final char BISHOP_CHAR = 'b';
        final char ROOK_CHAR = 'r';


        boolean firstIsThreat = false; // if the program will find a threats from the first chessman its will change to true
        boolean secondIsThreat = false; // if the program will find a threats from the second chessman its will change to true


        // Print instructions and recive the chessman type and position:
        Scanner scan = new Scanner (System.in);

        System.out.println("Please enter the type of the first chessman");
        char first = scan.next().charAt(0);

        System.out.println ("Please enter the number of row");
        int row1  = scan.nextInt();

        System.out.println ("Please enter the number of column");
        int col1 = scan.nextInt();


        System.out.println("Please enter the type of the second chessman");
        char second = scan.next().charAt(0);

        System.out.println ("Please enter the number of row"); 
        int row2 = scan.nextInt();

        System.out.println ("Please enter the number of column");
        int col2 = scan.nextInt();

        

        //checking if the input is valid by this conditions:
        //1. they cant be the same(b&b..)
        if(first == second) {
            System.out.println("Chessmen should be different from each other");
        
        //2. ther position must be valid (1-8)
        } else if((row1  < START_R_C || row1  > END_R_C) || (col1 < START_R_C || col1 > END_R_C) ||
                ((row2 < START_R_C || row2 > END_R_C) || (col2 < START_R_C || col2 > END_R_C))) {
            System.out.println("Position is not legal");
        
        //3. ther posotion cant be the same
        } else if((row1  == row2) && (col1 == col2)) {
            System.out.println("Chessmen positions should not be identical");
        
        // if all the 3 consitions are false(the inputs are legal) - we can check if there is any threats
        } else {
                        
            //first we will check if there is threats from the first chessman aginst the second chessman
            //if not we will check the second chessman against the first chessman

            //first we will find if there is a threat and in the end we will print against who(chesmann type)
            if(first == BISHOP_CHAR){
                if((row2 - row1 ) == (col2 - col1) || (row1 - row2 ) == (col2 - col1) ){
                    //there is treat by bishop
                    System.out.print("bishop threats ");
                    firstIsThreat = true;
                }
            } else if(first == ROOK_CHAR){
                //there is treat by rook
                if((row1  == row2) || (col1 == col2)){
                    System.out.print("rook threats ");
                    firstIsThreat = true;
                }
            } else if(first == KNIGHT_CHAR){
                //for every 2 follwing and legal position of knight, the position row and col will genarate a right-angel triangle
                //with legs of 2,1,5. so by using pitagoras formula we can check if the hypotenuse length is 5 when the 2 position is the
                // 2 chessman row&col.
                //we will keep all clac's in power 2 in(in order to keep the numbers on int type)
                
                //Pitagoras formulas -> (legA^2 + legB^2 = hypotenuse^2)

                //calc' legs A&B in order to find the hypotenuse length
                int legASq = (row2-row1)*(row2-row1);

                int legBSq = (col2-col1)*(col2-col1);

                // The hypotenuse^2 equal to legA^2 + legB^2
                int res = legASq + legBSq;

                //if the hypotenuse length equal to 5 there is a threat by the knight
                if (res == HYPOTENUSE_LENGTH){
                    System.out.print("knight threats ");
                    firstIsThreat = true;          
                }
                
               //in case that there is no threats from the first chessman - now we will from the second aagainst the first
            }
            if(!firstIsThreat){ // NOLY if there is no threat 

                //The explains are the same and discribe above (chessman 1 against 2)

                if(second == BISHOP_CHAR){
                    if((row2 - row1 ) == (col2 - col1) || (row1 - row2) == (col2 - col1)){
                        //there is treat by bishop
                        System.out.print("bishop threats ");
                        secondIsThreat = true;
                    }
                } else if(second == ROOK_CHAR){
                    //there is treat by rook
                    if((row1  == row2) || (col1 == col2)){
                        System.out.print("rook threats ");
                        secondIsThreat = true;
                    }
                } else if(second == KNIGHT_CHAR){
                    int legASq = (int) Math.pow((row2-row1),2);
                    int legBSq = (int) Math.pow((col2-col1),2);

                    int res = legASq + legBSq;

                    if (res == HYPOTENUSE_LENGTH){
                        System.out.print("knight threats ");
                        secondIsThreat = true;          
                    }      
                }
            }

            //checking what is chesmann 2 type and completing the print

            // in case that the first chessman threts the second chessman
            if(firstIsThreat){
                switch(second){

                    case BISHOP_CHAR:
                    System.out.println("bishop");
                    break;

                    case ROOK_CHAR:
                    System.out.println("rook");
                    break;
                    
                    case KNIGHT_CHAR:
                    System.out.println("knight");
                    break;
                }

            // in case that the second chessman threts the first chessman    
            } else if(secondIsThreat){
                switch(first){

                    case BISHOP_CHAR:
                    System.out.println("bishop");
                    break;

                    case ROOK_CHAR:
                    System.out.println("rook");
                    break;
                    
                    case KNIGHT_CHAR:
                    System.out.println("knight");
                    break;
                }
            } else // if firstIsThreat and secondIsThreat are false, there is no threats at all between the 2 chessmans
                System.out.println("no threat");
        }
        scan.close(); // closing scan class
    }// end of method main    
}//end of class Chess
 
 
