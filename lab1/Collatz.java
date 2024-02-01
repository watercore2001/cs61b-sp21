/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Given number n, return 1 if n==1,
     * return n/2 if n is even,
     * return 3n+1 if n is odd.
     *
     * @param n given number
     * @return 1 or n/2 or 3n+1
     */
    public static int nextNumber(int n) {
        if (n  == 1) {
            return 1;
        }

        if (n % 2 == 1) {
            return 3 * n + 1;
        }

        return n / 2;

    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

