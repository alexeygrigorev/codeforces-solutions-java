package codeforces;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;

public class TestComparer {

    private final Runnable sut;
    private StringBuilderWriter out;

    public TestComparer(Runnable sut) {
        this.sut = sut;
        this.out = new StringBuilderWriter();
        setOutput();
    }

    private void setOutput() {
        try {
            Field field = sut.getClass().getDeclaredField("out");
            field.setAccessible(true);
            field.set(sut, printWriter());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PrintWriter printWriter() {
        return new PrintWriter(out, true);
    }

    public void run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sut.run();
        stopWatch.stop();

        System.out.println("Took " + stopWatch + " to launch the test");
        System.out.println();
    }

    public void given(String input) {
        System.out.println("Input:");
        System.out.println(input);
        System.out.println();
        setInput(new CharSequenceInputStream(input, Charsets.UTF_8));
    }

    public void inputFromFile(String name) {
        try {
            InputStream input = sut.getClass().getResourceAsStream(name);
            given(IOUtils.toString(input));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setInput(InputStream input) {
        try {
            Method method = sut.getClass().getDeclaredMethod("setInput", InputStream.class);
            method.invoke(sut, input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyFromFile(String name) {
        try {
            InputStream expected = sut.getClass().getResourceAsStream(name);
            verifyOutput(IOUtils.toString(expected));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyOutput(String output) {
        System.out.println("Expected:");
        System.out.println(output);
        System.out.println();

        String actual = capturedOutput();
        System.out.println("Actual:");
        System.out.println(actual);

        System.out.println();
        System.out.println();

        Assert.assertArrayEquals(output.split("\\s+"), actual.split("\\s+"));
    }

    public PrintWriter outputStream() {
        return printWriter();
    }

    public String capturedOutput() {
        return out.toString();
    }

}
