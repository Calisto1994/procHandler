import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/*
 * Process Handler
 *
 * Allows PHP-like shell_exec() calls
 * returns the stdOut, stdErr, pid and exitCode of the process that was executed.
 *
 */
public class procHandler {
    private boolean waitFor = false;
    public String stdOut = new String();
    public String stdErr = new String();
    public long pid = 0;
    public int exitCode = 0;

    public procHandler shell_exec (String[] command) throws InterruptedException { // Run a command, return its stdOut
        String[] results = {new String(), new String()};
        String result = new String();
        try {
            Process process = Runtime.getRuntime().exec(command);
            if (waitFor) {   // Only if .waitFor(true) was declared, use a blocking process
                process.waitFor();
                this.exitCode = process.exitValue();
            }

            this.stdOut = scanStream(process.getInputStream());
            this.stdErr = scanStream(process.getErrorStream());
            this.pid = process.pid();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            this.pid = 0;
            this.exitCode = 0;
            this.stdOut = new String();
            this.stdErr = "Error: Process execution failed.";
        }
        return(this);
    }
    public procHandler shell_exec (String command) throws InterruptedException { // String instead of String Array;
        return shell_exec(StrtoStrA(command));
    }

    // INPUTSTREAM OPERATIONS

    public static String scanStream (InputStream inputStream) {
        String result = new String();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line != null)
                result += String.format("%s\n", line);
        }
        scanner.close();
        return(result);
    }

    // STRING OPERATIONS

    public static String StrAtoStr (String[] args) { // Converts a string array (like the command-line arguments) to a String.
        String Out = new String(); // Initialize string
        if (args.length > 0) { // Check if there's any arguments provided
            for (int i = 0; i < args.length; i++) { // Convert through a "for"-Loop
                Out += args[i] + " "; // concatenate arguments
                if (i != args.length - 1) Out += " "; // Add a whitespace, if it's not the last argument
            }

        }
        return(Out);
    }

    public static String[] StrtoStrA (String args) { // convert a String to a String Array (e.g. to use for shell_execute)
        return args.split(" ");
    }

    // BUILDER OPERATIONS (allows for default values for certain parameters which may be called like .waitFor(true))
    public procHandler waitFor (boolean waitFor) {
        this.waitFor = waitFor;
        return this;
    }
}