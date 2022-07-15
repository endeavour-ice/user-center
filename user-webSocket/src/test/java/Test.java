import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ice
 * @date 2022/7/13 21:20
 */

public class Test {
    public static void main(String[] args) {
        /**
         * [1,1,1],
         * [1,0,1],
         * [1,1,1],
         * [0,0,0]
         */
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1},{0,0,0}};
        setZeroes(matrix);
    }

    public static void setZeroes(int[][] matrix) {
//
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(row));
        System.out.println(Arrays.toString(col));
        System.out.println(Arrays.deepToString(matrix));
    }


}
