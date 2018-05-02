package coursera;

import java.io.InputStream;
import java.io.PrintWriter;

import notsandbox.Problem;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;

public class ProblemRunner {

    private PrintWriter stdOutput = new PrintWriter(System.out, true);

    private final Problem sut;
    private StringBuilderWriter out;

    public ProblemRunner(Problem sut) {
        this.sut = sut;
        this.out = new StringBuilderWriter();
        setOutput();
    }

    private void setOutput() {
        sut.setOut(printWriter());
    }

    private PrintWriter printWriter() {
        return new PrintWriter(out, true);
    }

    public void run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sut.run();
        stopWatch.stop();

        stdOutput.println("Took " + stopWatch + " to launch the test");
        stdOutput.println();
    }

    public ProblemRunner noDebuggingStdOutput() {
        stdOutput = new PrintWriter(new NullOutputStream(), false);
        return this;
    }

    public void given(String input) {
        stdOutput.println("Input:");
        stdOutput.println(input);
        stdOutput.println();
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
        sut.setInput(input);
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
        stdOutput.println("Expected:");
        stdOutput.println(output);
        stdOutput.println();

        String actual = capturedOutput();
        stdOutput.println("Actual:");
        stdOutput.println(actual);

        stdOutput.println();
        stdOutput.println();

        Assert.assertArrayEquals(output.split("\\s+"), actual.split("\\s+"));
    }

    public String capturedOutput() {
        return out.toString();
    }

    public String getOutput() {
        return capturedOutput();
    }

    public PrintWriter outputStream() {
        return printWriter();
    }
}
