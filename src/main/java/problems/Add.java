package problems;

public class Add {

    public static int add(int i, int j) {
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        return add(i ^ j, (i & j) << 1);
    }

    public static int add2(int i, int j) {
        if (j == 0) {
            return i;
        }
        return add2(i ^ j, (i & j) << 1);
    }

    public static int add3(int i, int j) {
        return (j == 0) ? i : add3(i ^ j, (i & j) << 1);
    }

}
