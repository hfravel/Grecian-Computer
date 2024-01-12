public class GrecianCalculator
{
    // The first disc is the top disc, and only extends 1 row out
    // A 0 indicates there is a hole in the disc
    private static final int[] firstDisc = new int[]
            {3, 0, 6, 0, 10, 0, 7, 0, 15, 0, 8, 0};
    // The second disc is third from the bottom, and only extends 2 rows out
    // Index 0 of each array indicates the innermost row of the disc
    private static final int[][] secondDisc = new int[][]
            {{7, 3, 0, 6, 0, 11, 11, 6, 11, 0, 6, 17},
            {4, 0, 7, 15, 0, 0, 14, 0, 9, 0, 12, 0}};
    // The third disc is second from the bottom, and only extends 3 rows out
    private static final int[][] thirdDisc = new int[][]
            {{9,13, 9, 7, 13, 21, 17, 4, 5, 0, 7, 8},
            {21, 6, 15, 4, 9, 18, 11, 26, 14, 1, 12, 0},
            {5, 0, 10, 0, 8, 0, 22, 0, 16, 0, 9, 0}};
    // The fourth disc is the bottom disc
    private static final int[][] fourthDisc = new int[][]
            {{7, 0, 9, 0, 7, 14, 11, 0 , 8, 0, 16, 2},
            {9, 20, 12, 3, 6, 0, 14, 12, 3, 8, 9, 0},
            {3, 26, 6, 0, 2, 13, 9, 0, 17, 19, 3, 12},
            {1, 0, 9, 0, 12, 0, 6, 0, 10, 0, 10, 0}};
    private static final int[][] bottomLayer = new int[][]
            {{14, 11, 14, 11, 14, 11, 11, 14, 11, 14 ,11, 14},
            {11, 12, 13, 14, 15, 4, 5, 6, 7, 8, 9, 10},
            {14, 21, 21, 9, 9, 4, 4, 6, 6, 3, 3, 14},
            {7, 16, 8, 7, 8, 8, 3, 4, 12, 2, 5, 10}};
    private static final String[] printRowsStrings = new String[]
            {"1st (Inner) Row:\t", "2nd Row:\t\t\t", "3rd Row:\t\t\t", "4th (Outer) Row:\t"};

    /**
     * Cycles through all 12 positions of each disc until the solution is found
     * @return 4 integers of range 0-11 where index 0 is the first disc's rotation
     */
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
                        if (isValidSolution(discPos))
                        {
                            return discPos;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns true if all the columns in the discPos total to 42
     * @param discPos 4 integers of range 0-11 where index 0 is the first disc's rotation
     */
    private static boolean isValidSolution(int[] discPos)
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

    /**
     * Sums the 4 values for a give column
     * @param column 0-11 for which column we're calculating
     * @param discPos 4 integers of range 0-11 where index 0 is the first disc's rotation
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

    /**
     * Determines which number is visible for the row and column
     * @param row 0 is calculating the inner row and 3 is calculating the outer row
     * @param column 0-11 indicate which column we're calculating
     * @param discPos 4 integers of range 0-11 where index 0 is the first disc's rotation
     */
    private static int determineValue(int row, int column, int[] discPos)
    {
        int first = calculateIndex(column, discPos[0]);
        int second = calculateIndex(column, discPos[1]);
        int third = calculateIndex(column, discPos[2]);
        int fourth = calculateIndex(column, discPos[3]);

        if (row < 1 && firstDisc[first] != 0)
            return firstDisc[first];
        if (row < 2 && secondDisc[row][second] != 0)
            return secondDisc[row][second];
        if (row < 3 && thirdDisc[row][third] != 0)
            return thirdDisc[row][third];
        if (fourthDisc[row][fourth] != 0)
            return fourthDisc[row][fourth];
        return bottomLayer[row][column];
    }

    /**
     * Determines the index in the disc's array for the given column number
     * @param column The column on the disc we're calculating for
     * @param discPos integer for the disc's rotation of range 0-11
     */
    private static int calculateIndex(int column, int discPos)
    {
        return (column + discPos) % 12;
    }

    private static String solutionToString(int[] discPos)
    {
        StringBuilder solution = new StringBuilder();
        int currVal;

        for (int row = 3; row >= 0; row--)
        {
            solution.append(printRowsStrings[row]).append("{ ");
            for (int col = 0; col < 12; col++)
            {
                currVal = determineValue(row, col, discPos);
                if (currVal < 10)
                    solution.append(" ");
                solution.append(currVal);

                if (col != 11)
                    solution.append(", ");
            }
            solution.append("}\n");
        }
        return solution.toString();
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
            System.out.println(solutionToString(solution));
        }
    }
}