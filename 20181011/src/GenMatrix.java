import jbook.util.Input;

public class GenMatrix {

    private static final int NUM_ROW = 5;
    private static final int NUM_COL = 3;

    public static void main(String[] args) {

        int[][] matrix = generateMatrix(NUM_ROW, NUM_COL);
        printMatrix(matrix);

    }

    private static void printMatrix(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    private static int[][] generateMatrix(int numRow, int numCol) {
        int[][] matrix =  new int[numRow][numCol];
        for(int i = 0; i < numRow; i++)
            for(int j = 0; j<numCol; j++)
                matrix[i][j] = Input.readInt("Inserisci l'elemento ["+i+"]["+j+"] della matrice \n> ");
        return matrix;
    }


}
