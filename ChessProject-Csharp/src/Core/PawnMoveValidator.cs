using SolarWinds.MSP.Chess.Core.Interfaces;
using src.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace src.Core
{
    // Currently unused
    public class PawnMoveValidator : IMoveValidator
    {
        public bool IsValidBlackMove(int newX, int newY)
        {
            throw new NotImplementedException();
        }

        public bool IsValidWhiteMove(int newX, int newY)
        {
            throw new NotImplementedException();
        }
    }
}
