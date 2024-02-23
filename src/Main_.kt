import javax.swing.JOptionPane

object Main_ {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Start")
        while (true) commandAsk()
    }

    fun commandAsk() {
        val proc = procHandler()
                .waitFor(true)
        val command = JOptionPane.showInputDialog(null, "Befehl:", "uname -a")
        if (command == null) System.exit(1) // If cancel button is clicked, end program

        try {
            proc.shell_exec(command)
        } catch (ioe: Exception) {
            println("Error!")
            ioe.printStackTrace()
        }

        if (proc.pid != 0L) // Check if execution was successful
            println(String.format("Process %d exited with code %d", proc.pid, proc.exitCode))
        else println("Execution error: No Process ID returned.")

        if (!proc.stdOut.isEmpty()) JOptionPane.showMessageDialog(null, proc.stdOut, command, JOptionPane.INFORMATION_MESSAGE)
        if (!proc.stdErr.isEmpty()) JOptionPane.showMessageDialog(null, proc.stdErr, command, JOptionPane.ERROR_MESSAGE)
    }
}