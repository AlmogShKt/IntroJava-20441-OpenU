
/**
 * Maman 14, 
 * the answer for Q1(A) is in commet.
 * @author Almog Shtaigmann
 * @version 4.5.22
 */
public class Ex14{
    //-----Q1-----\\
        //A. -> The true sentence is: 1,3,6
        
        
        //B. ->
        /**
         * Return true if {@code val} is in {@code m}  
         * The method will search for value in 2 demantion array({@code int[][]}), in {@code O(n)}.
         * @param m 2 demantion array {@code int[][]}
         * @param val the value to search in {@code m}
         * @return true if {@code val} exist in {@code m}, false if not
         * Time complexity is O(n) space complexity is O(1)
         */
        public static boolean findValWhat(int[][] m, int val){
            int n = m.length;
            int r = 0, c = n - 1 ;
           
            //In order to keep O(n), we will use 2 indexes , one for the column(the values in each array), and r for each array
            //We will 'cut' each time the column or the row until we will find val/ not found
            
            //only if we are still within the array boundaries - if not -> val was not found or m is null[][]
            while(r < n && c >= 0){
                
                if(m[r][c] == val){
                    return true; //val is found
                }

                //if val is grather then the valve is place[r][c] val is not in array r, and we can check the next array -> i++
                //if val is not grather then the value in place[r][c] so there is option that val is in array r, -> j--
                //In the end of this procces we will find if val is in m, in O(n), the worst senario is n + n-1 -> O(n).
                //We creat 3 varibels from type int, so the is constant -> O(1) as well.

                //Time complexity is O(n)

                //if val was not found(yet), 'delete' row/col to reduce the options
                if(m[r][c] > val) 
                    c--;
                else 
                    r++;    
            }
            return false; // val is not found in 'm'
        }


        /**
         * Return true if {@code val} is in {@code m}.
         * The method will search for value in 2 demantion array({@code int[][]}), in {@code O(n)}.
         * @param m 2 demantion array {@code int[][]}
         * @param val the value to search in {@code m}
         * @return true if {@code val} exist in m, false if not
         * Time complexity is O(n) and space complexity is O(1)
         */
        public static boolean findValTest(int[][] m, int val){
            int n = m.length;
            int r = 0, c = 0;

            //The array is sortaed in way that for every row r, all the numbers in r+1 are greater than all the numbers in r
            //So if we find a number that bigger then val, we can stop the scanning and search val in the r, and r-1.

            //First - find the row that  contain bigger value than val.
            
            for(int i = 0;i < n ;i++){ // In the worst case n times.

                if(m[i][c] == val){
                    return true;
                }
                else if(m[i][c] > val){
                    r = i; // Now we need to scan this row and one before(r,r-1).
                    i = n-1; // In order to exit the loop, set i to the lasl iteration value.
                } else // in case that the loop exit becuase the i arrived to the last value. whats mean that r need to be in the value of n-1.
                    r = i;

            }

            //Second search for val in r, and r-1
            for(int i =0; i < 2 && r >= 0 ;r--,i++){ 
                // In the worst case the upper loop will run 2 times
                // In worst case the lower loop will run n times
                // In total 2*n -> O(n)

                //Search for val fron 0 -> n
                for(c = 0 ;c < n ;c++){ 
                    if(m[r][c] == val)
                        return true;
                }
            }
            // in total the time complexity is: n + 2n = O(3n).

            //In case that val was not found in the array.
            return false;      
        }

        //---End of Q1---\\


        //-----Q2-----\\

        /**
         * Return how many sort sub-arrays there is in sort array in size {@code count}
         * @param count the size of the sort array
         * @return the number of sort sub-arrays in sort array. 
         * @example:
         * array = [1,2,3], count = 3, the sub sorted arrays is [1,2],[2,3],[1,2,3] - return 3
         * for every sort array of size 3, there is 3 sub sort arrays
         * Time complexity is O(n) and space complexity is O(1)
         */
         private static int howManySubArr(int count){
            int total = 0;
            int j = 1;
            for(int i = count; i > 1; i-- , j++){//In the Worst case will run n times
                total += j;
            }
            return total;
        }//End of methos howManySubArr

        
        /**
         * Return the amount of sub-sorted-arrays(each number is *bigger* then the previous) in array
         * @param a the original array
         * @return the amount of sub-sorted-arrays in the original array
         * Time complexity is O(n) and space complexity is O(1). detailed expnalation in the method body
         */
        public static int strictlyIncreasing (int[] a){

            //If a is null or in size of 1 - there is 0 sub-sorted-arrays
            if (a == null || a.length <= 1) 
                return 0;

            
            int n = a.length;
            int countCurrntArr = 0;  //Counting how many sub-sotred-array there is in a-one-row ([1,2,3,3,4], for 1-3 is 3, the 3-4 is 2)
            int countTotalArr = 0; //Counting how many sub arrays tjere is in total 

            //There is only 3, constant varibels, so the space compexity is O(1)


            //In the worst case the loop will run n times
            for(int k = 0; k < n-1 ;k++){
                
                //Checking if the number in place k+1 > k, if yes count++ then check the next numbers
                //If yes count how many
                if(a[k] < a[k+1]){
                    countCurrntArr++;
                } else {
                    //If ther number in place k+1 <= k -> check how many sub arrays there in in sorted array in size countCurrentArr
                    //Then add this amount in the total amount if sub arrays
                    //The current count = 0, in order to find a new sub-array
                    //++countCurrntArr becuase we need to add 1 for the first number(for sorted array in size 2, the count will be only 1, so ++ will fix this )

                    //In the worst case, that all the array is sorted and in size of n, the for loop will run n time + n times in howManySubArr, in total 2n -> O(n)
                    countTotalArr +=  howManySubArr(++countCurrntArr);
                    countCurrntArr = 0;
                }

                //For the last iteration, calc' and add the current count.
                if(k == n-2) {
                    countTotalArr +=  howManySubArr(++countCurrntArr);
                    countCurrntArr = 0;
                }
           }
           return countTotalArr;
        }//End of method strictlyIncreasing
     
        //---End of Q2---\\


        //-----Q3-----\\

    
    
        /**
         * Return the lenght of sub array flat sequence from index i, for 2 consequtive numbers {@code a} and {@code a2}.
         * @param arr the original array to check for flat sequence.
         * @param i search for flat sequence from index {@code i}.
         * @param a the first number in consequtive pair.
         * @param a2 the second number in consequtive pair.
         * @return the lenght of the sub-array which is flat sequence.
         */
        private static int findFlatLenght(int[] arr, int i, int a, int a2){
            if(i == arr.length)
                return 0;
    
            // Only if this number is one of the consequtive pair; equal to a or a2.
            if(arr[i] == a || arr[i] == a2)
                //If yes, check the next step, and add 1 to the count.
                return findFlatLenght(arr, i +1, a, a2) + 1;
            else
                //If not, we can stop the search (by return 0 and stop call to the method) and return the accumulator count.
                return 0;
        }//End of method findFlatLenght

        /**
         * Return the lenght of longest sub array that flat-sequence in the array.
         * @param arr the array 
         * @return lenght of the longest flat sequence in the array.
         */
        public static int longestFlatSequence(int[] arr){
            return longestFlatSequence(arr, 0);
        }//Enf of method longestFlatSequence

        private static int longestFlatSequence(int[] arr, int i){
            if(i == arr.length){
                return 0;
            }
    
            //get the max sub-array for a,a+1
            int tempCountP = findFlatLenght(arr, i, arr[i],arr[i] + 1);
    
            //get the max sub-array for a,a-1
            int tempCountM = findFlatLenght(arr, i, arr[i],arr[i] - 1);
            
            //Check for the longest sub-array
            int maxCount = Math.max(tempCountM,tempCountP);
    
            //
            if(maxCount >= longestFlatSequence(arr, i+1))
                return maxCount;
    
             
            return longestFlatSequence(arr, i+1);   

        }//End of method longFlatSequence


    //---End of Q3---\\

    //-----Q4-----\\

    public static int findMaximum(int[][] mat){
        if(mat[0][0] == -1)
            return -1;
        else
            return findMaximum(mat,0,0);
    }//End of method findMaximum

    private static int findMaximum(int[][] mat, int r, int c){
        if(r == mat.length || c == mat[0].length || c == -1 || mat[r][c] == -1)
            return 0;

        if(r % 2 == 0){
            int tmpScrRit = findMaximum(mat,r,c+1) + mat[r][c];

            int tmpScrDwn = findMaximum(mat,r+1,c) + mat[r][c];

            return tmpScrRit > tmpScrDwn ? tmpScrRit:tmpScrDwn;

        } else {
            int tmpScrLft = findMaximum(mat,r,c-1) + mat[r][c];

            int tmpScrDwn = findMaximum(mat,r+1,c) + mat[r][c];

            return tmpScrLft > tmpScrDwn ? tmpScrLft:tmpScrDwn;

        }
    }//End of method findMaximum
        

}//End of class Ex14


    


            
            


        
