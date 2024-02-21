import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        while (true)
                commandAsk();
    }
    public static void commandAsk() {
        procHandler proc = new procHandler()
                .waitFor(true);
        String command = JOptionPane.showInputDialog(null, "Befehl:", "uname -a");
        if (command == null) System.exit(1); // If cancel button is clicked, end program
        try {
            proc.shell_exec(command);
        } catch (Exception ioe) {
            System.out.println("Error!");
            ioe.printStackTrace();
        }

        if (proc.pid != 0)  // Check if execution was successful
            System.out.println(String.format("Process %d exited with code %d", proc.pid, proc.exitCode));
        else
            System.out.println("Execution error: No Process ID returned.");

        if (!proc.stdOut.isEmpty())
            JOptionPane.showMessageDialog(null, proc.stdOut, command, JOptionPane.INFORMATION_MESSAGE);
        if (!proc.stdErr.isEmpty())
            JOptionPane.showMessageDialog(null, proc.stdErr, command, JOptionPane.ERROR_MESSAGE);
    }
}