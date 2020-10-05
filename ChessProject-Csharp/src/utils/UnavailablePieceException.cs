using System;

public class UnavailablePieceException : Exception
{
    public UnavailablePieceException()
    {
    }

    public UnavailablePieceException(string message)
        : base(message)
    {
    }

    public UnavailablePieceException(string message, Exception inner)
        : base(message, inner)
    {
    }
}
