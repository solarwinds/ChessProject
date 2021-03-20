using src.Enums;

namespace src.Consts
{
    public static class PieceTypeLimit
    {
        public const ushort Pawn = 8;
        public const ushort Knight = 2;
        public const ushort Bishop = 2;
        public const ushort Rook = 2;
        public const ushort Queen = 1;
        public const ushort King = 1;

        public static ushort GetPieceLimit(PieceType pieceType)
        {
            switch (pieceType)
            {
                case PieceType.Pawn:
                    return Pawn;
                case PieceType.Knight:
                    return Knight;
                case PieceType.Bishop:
                    return Bishop;
                case PieceType.Rook:
                    return Rook;
                case PieceType.Queen:
                    return Queen;
                case PieceType.King:
                    return King;
                default:
                    return 0;
            }
        }
    }
}