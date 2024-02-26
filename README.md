# Java procHandler class #

If you want to spawn a process and fetch `pid`, `stdOut` and `stdErr`, you may use the following method:
```java
    procHandler proc = new procHandler();
    proc.shell_execute(put_your_command_here);
    
    String stdOut = proc.stdOut; String stdErr = proc.stdErr;
    Long pid = proc.pid;
```

But if you want to get the `exitCode`, too, you'll have to declare the process as a blocking one by using `.waitFor(true)` like this:

```java
    procHandler proc = new procHandler()
        .waitFor(true);
    proc.shell_execute(put_your_command_here);
    
    String stdOut = proc.stdOut; String stdErr = proc.stdErr;
    Long pid = proc.pid; int exitCode = proc.exitCode;
```

### ProcessID `pid` problem ###

The current release of Java that is recommended to end-users is Java 8 (at the time of writing this line, it was Java 8 Update 401, to be exact);
Java 8 does **not** support to query the process id of a task that it has launched — therefore, `this.pid = process.pid();` query has been *quoted* in `procHandler.java` in order to maintain compatibility with Java 8.

If you're using a more recent release of Java (e.g. OpenJDK 21 (including Java 21 Runtime)), you may unquote the line to get access to the process id when using `procHandler` in your project.

## Using the Class ##
To use the `procHandler.java` class, you'll have to use `try` and `catch` or include `throws InterruptedException` to your function, like this:

```java
    public static void Main () throws InterruptedException {
        procHandler proc = new procHandler();
        proc.shell_execute(put_your_command_here);

        String stdOut = proc.stdOut; String stdErr = proc.stdErr;
        Long pid = proc.pid;        
    }
```
or like this:
```java
    public static void Main () {
        procHandler proc = new procHandler();
        try {
            proc.shell_execute(put_your_command_here);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stdOut = proc.stdOut; String stdErr = proc.stdErr;
        Long pid = proc.pid;
    }
```

### Example ###

The `Main.java` class file contains an example use-case for `procHandler.java`. Feel free to try it.
The `Main_.kt` class file is written in _Kotlin_ language to showcase the difference between using the Java class with
other _Java_ code or to use it with _Kotlin_ code.

### Inclusion of Shells (bash, zsh) ###
You may also use the `procHandler` class to include Shell scripts to your Java application or
to call commands that would only be available from within a shell like `Bash` or `zsh`:

```java
public static voic Main (String[] args) {
    procHandler proc = new procHandler();
    String[] command = {"bash", "-c", String.format("\"%s\"", "/path/to/your/shellscript.sh")};
    proc.shell_exec(command);
    JOptionPane.showMessageDialog(null, proc.stdOut, null, JOptionPane.INFORMATION_MESSAGE);
}  
```