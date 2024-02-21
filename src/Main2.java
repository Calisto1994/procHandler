import javax.swing.*;

/* shortened version — example — handles only stdOut */

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Start");
        while (true)
            commandAsk();
    }
    public static void commandAsk() {
        String stdOut = new String();

        procHandler proc = new procHandler()
                .waitFor(true);
        String command = JOptionPane.showInputDialog(null, "Befehl:", "uname -a");
        if (command == null) System.exit(1); // If cancel button is clicked, end program
        try {
            stdOut = proc.shell_exec(command).stdOut;
        } catch (Exception ioe) {
            System.out.println("Error!");
            ioe.printStackTrace();
        }

        if (!stdOut.isEmpty())
            JOptionPane.showMessageDialog(null, stdOut);
    }
}