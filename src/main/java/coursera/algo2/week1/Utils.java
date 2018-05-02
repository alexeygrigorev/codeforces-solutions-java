package coursera.algo2.week1;

/**
 * @author Grigorev Alexey
 */
public class Utils {
    public static int compareInt(int a, int b) {
        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }
}
