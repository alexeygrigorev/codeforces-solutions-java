package codeforces;

import java.io.*;


public class Template implements Runnable {

    private PrintWriter out = new PrintWriter(System.out, true);
    private BufferedReader reader;

    @Override
    public void run() {
        try {
            reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.flush();
    }

    public Template setInput(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        return this;
    }

    public static void main(String[] args) {
        new Template().setInput(System.in).run();
    }

}