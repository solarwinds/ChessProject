using System;

public class DuplicatePositioningException : Exception
{
    public DuplicatePositioningException()
    {
    }

    public DuplicatePositioningException(string message)
        : base(message)
    {
    }

    public DuplicatePositioningException(string message, Exception inner)
        : base(message, inner)
    {
    }
}
