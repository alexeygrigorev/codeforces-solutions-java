package notsandbox;

public class Utils {
    public static int[] parseIntArray(String[] line) {
        int length = line.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = Integer.parseInt(line[i]);
        }

        return result;
    }

}
