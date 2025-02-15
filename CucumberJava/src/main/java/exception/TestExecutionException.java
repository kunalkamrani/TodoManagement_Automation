package exception;

public class TestExecutionException extends Exception{

    public TestExecutionException(String message)
    {
        super(message);
    }
    public TestExecutionException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
