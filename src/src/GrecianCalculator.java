public class GrecianCalculator
{
    private static final int[][] bottomLayer = new int[][]
            {{7, 16, 8, 7, 8, 8, 3, 4, 12, 2, 5, 10},
            {14, 21, 21, 9, 9, 4, 4, 6, 6, 3, 3, 14},
            {11, 12, 13, 14, 15, 4, 5, 6, 7, 8, 9, 10},
            {14, 11, 14, 11, 14, 11, 11, 14, 11, 14 ,11, 14}};
    private static final int[][] firstDisc = new int[][]
            {{1, 0, 9, 0, 12, 0, 6, 0, 10, 0, 10, 0},
            {3, 26, 6, 0, 2, 13, 9, 0, 17, 19, 3, 12},
            {9, 20, 12, 3, 6, 0, 14, 12, 3, 8, 9, 0},
            {7, 0, 9, 0, 7, 14, 11, 0 , 8, 0, 16, 2}};
    private static final int[][] secondDisc = new int[][]
            {{5, 0, 10, 0, 8, 0, 22, 0, 16, 0, 9, 0},
            {21, 6, 15, 4, 9, 18, 11, 26, 14, 1, 12, 0},
            {9,13, 9, 7, 13, 21, 17, 4, 5, 0, 7, 8}};
    private static final int[][] thirdDisc = new int[][]
            {{4, 0, 7, 15, 0, 0, 14, 0, 9, 0, 12, 0},
            {7, 3, 0, 6, 11, 11, 6, 11, 0, 6, 17}};
    private static final int[] fourthDisc = new int[]
            {3, 0, 6, 0, 10, 0, 7, 0, 15, 0, 8, 0};

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}