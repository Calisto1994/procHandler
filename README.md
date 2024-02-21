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

### Example 2 ###

If you only need `stdOut` (or `stdErr`, would work the same way), check out `Main2.java`