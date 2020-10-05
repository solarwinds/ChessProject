using System;

public class InvalidPositioningException : Exception
{
    public InvalidPositioningException()
    {
    }

    public InvalidPositioningException(string message)
        : base(message)
    {
    }

    public InvalidPositioningException(string message, Exception inner)
        : base(message, inner)
    {
    }
}
