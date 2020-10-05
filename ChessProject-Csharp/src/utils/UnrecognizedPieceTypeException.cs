using System;

public class UnrecognizedPieceTypeException : Exception
{
    public UnrecognizedPieceTypeException()
    {
    }

    public UnrecognizedPieceTypeException(string message)
        : base(message)
    {
    }

    public UnrecognizedPieceTypeException(string message, Exception inner)
        : base(message, inner)
    {
    }
}
