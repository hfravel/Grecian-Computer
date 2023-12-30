public class GrecianCalculator
{
    // A 0 indicates there is a hole in the disc
    // Index 0 of each array indicates the innermost row of the disc
    private static final int[][] bottomLayer = new int[][]
            {{14, 11, 14, 11, 14, 11, 11, 14, 11, 14 ,11, 14},
            {11, 12, 13, 14, 15, 4, 5, 6, 7, 8, 9, 10},
            {14, 21, 21, 9, 9, 4, 4, 6, 6, 3, 3, 14},
            {7, 16, 8, 7, 8, 8, 3, 4, 12, 2, 5, 10}};
    // The first disc is the bottom most disc
    private static final int[][] firstDisc = new int[][]
            {{7, 0, 9, 0, 7, 14, 11, 0 , 8, 0, 16, 2},
            {9, 20, 12, 3, 6, 0, 14, 12, 3, 8, 9, 0},
            {3, 26, 6, 0, 2, 13, 9, 0, 17, 19, 3, 12},
            {1, 0, 9, 0, 12, 0, 6, 0, 10, 0, 10, 0}};
    // The second disc is second from the bottom, and only extends 3 rows out
    private static final int[][] secondDisc = new int[][]
            {{9,13, 9, 7, 13, 21, 17, 4, 5, 0, 7, 8},
            {21, 6, 15, 4, 9, 18, 11, 26, 14, 1, 12, 0},
            {5, 0, 10, 0, 8, 0, 22, 0, 16, 0, 9, 0}};
    // The third disc is third from the bottom, and only extends 2 rows out
    private static final int[][] thirdDisc = new int[][]
            {{7, 3, 0, 6, 0, 11, 11, 6, 11, 0, 6, 17},
            {4, 0, 7, 15, 0, 0, 14, 0, 9, 0, 12, 0}};
    // The fourth disc is the top disc, and only extends 1 row out
    private static final int[] fourthDisc = new int[]
            {3, 0, 6, 0, 10, 0, 7, 0, 15, 0, 8, 0};

    private static int[] bruteForce()
    {
        int[] discPos = new int[4];

        for (discPos[0] = 0; discPos[0] < 12; discPos[0]++)
        {
            for (discPos[1] = 0; discPos[1] < 12; discPos[1]++)
            {
                for (discPos[2] = 0; discPos[2] < 12; discPos[2]++)
                {
                    for (discPos[3] = 0; discPos[3] < 12; discPos[3]++)
                    {
                        if (calculateColumnTotals(discPos))
                        {
                            return discPos;
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean calculateColumnTotals(int[] discPos)
    {
        for (int col = 0; col < 12; col++)
        {
            if (42 != sumValues(col, discPos))
            {
                return false;
            }
        }
        return true;
    }

    /** Sums the 4 values for the give column
     *
     * @param column 0-11 for which column we're calculating
     * @param discPos where each disc is positioned
     */
    private static int sumValues(int column, int[] discPos)
    {
        int total = 0;
        for (int row = 0; row < 4; row++)
        {
            total += determineValue(row, column, discPos);
        }
        return total;
    }

    /** Determines which number is visible for the row and column
     *
     * @param row 0 is calculating the outer row and 3 is calculating the inner row
     * @param column 0-11 based on which column we're calculating
     * @param discPos where each disc is positioned
     */
    private static int determineValue(int row, int column, int[] discPos)
    {
        int fourth = determineNumber(column, discPos[3]);
        int third = determineNumber(column, discPos[2]);
        int second = determineNumber(column, discPos[1]);
        int first = determineNumber(column, discPos[0]);

        if (row < 1 && fourthDisc[fourth] != 0)
            return fourthDisc[fourth];
        if (row < 2 && thirdDisc[row][third] != 0)
            return thirdDisc[row][third];
        if (row < 3 && secondDisc[row][second] != 0)
            return secondDisc[row][second];
        if (firstDisc[row][first] != 0)
            return firstDisc[row][first];
        return bottomLayer[row][column];
    }

    private static int determineNumber(int column, int discPos)
    {
        return (column + discPos) % 12;
    }

    private static void printSolution(int[] solution)
    {
        System.out.print("The outer row should like as follows:\n{");
        for (int i = 0; i < 12; i++)
        {
            if (firstDisc[3][solution[0]] != 0)
            {
                System.out.print(firstDisc[3][solution[0]]);
            }
            else
                System.out.print(bottomLayer[i]);
            System.out.print(", ");
        }
    }

    public static void main(String[] args)
    {
        int[] solution = bruteForce();
        if (solution == null)
        {
            System.out.println("No solution");
        }
        else
        {
            System.out.println("Solution: ");
            for (int i = 0; i < 4; i++)
            {
                System.out.println("\tDisc " + (i+1) + " rotation = " + solution[i]);
            }
        }
    }
}