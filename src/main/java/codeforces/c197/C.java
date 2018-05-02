package codeforces.c197;

import java.io.*;

public class C implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private BufferedReader reader;

    @Override
    public void run() {
        try {
            String line = reader.readLine();
            String result = calc(line);
            out.print(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.flush();
    }

    public String calc(String inputString) {
        char[] line = inputString.toCharArray();
        int n = line.length;

        StringBuilder result = new StringBuilder(n);
        
        int indexLast = -1;
        
        for (char current = 'z'; current >= 'a'; current--) {
            for (int i = indexLast + 1; i < n; i++) {
                if (line[i] == current) {
                    result.append(current);
                    indexLast = i;
                }
            }
        }
        
        return result.toString();
    }

    public C setInput(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    public static void main(String[] args) {
        new C().setInput(System.in).run();
    }
}