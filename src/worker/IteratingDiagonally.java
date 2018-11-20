package worker;

public class IteratingDiagonally {

    public static void main(String[] args) {


        int size = 5;
        int[][] grid = new int[size][size];

        for(int i=0; i<size; i++) {
            //Generate the initial structures for the nth element in the split here.
            //grid[i][i] = ...
        }


        int diagonalSize = size - 1;
        for(int delta = 1; delta <= size - 1; delta++) {
            for(int i=0; i<diagonalSize; i++) {
                int target = grid[i][i+delta];

                for(int j=0; j<delta; j++) {
                    //Generate Structures from cells grid[i][i+j] and grid[i+j+1][i+delta].
                    //Structures with the lowest energy levels should be added to target.
                }

            }
            diagonalSize--;
        }

        //Cell grid[0][size-1] should now contain the optimal structure.

    }

}
