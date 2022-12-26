public class App {
    public static boolean test(int [][] m)
{
   int n=m.length;
   for(int r=0; r<(n-1); r++)
     for (int c=0; c<n; c++)
           for (int i=0; i<n; i++)
                if(m[r][c] > m[r+1][i]) return false;
   return true;
}
    public static void main(String[] args) throws Exception {
        App ap = new App();
        int aד[][] = {   { 10, 20, 30, 40 },
                        { 15, 25, 35, 45 },
                        { 27, 29, 37, 48 },
                        { 32, 33, 39, 50 } };

        int bד[][] = {   { 15, 10, 20,23 },
                        { 26, 28, 35 ,38 },
                        { 55, 56, 57, 59 },
                        { 60, 66, 77 , 79 } };

        int[][] mat = { {1,1,-1,1,1},
                        {1,0,0,-1,1},
                        {1,1,1,1,-1},
                        {-1,-1,1,1,1},
                        {1,1,-1,-1,1} };

        int[] a11 = {1,2,3,4};
        int[] arr = {1,2,1,2};
        int val = 79;
        int r = -1;


        // System.out.println(e.findMaximum(mat));
      //  System.out.println(ap.test(b));
        //System.out.println(e.findValTest(b, val));
        //System.out.println(e.longFlatSequence(arr));
        //System.out.println(e.strictlyIncreasing(a1));
        //System.out.println(e.howManySubArr(5));

        


   

        
    }
}
