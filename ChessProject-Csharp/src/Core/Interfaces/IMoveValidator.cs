using SolarWinds.MSP.Chess.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace src.Core.Interfaces
{
    // Currently unused
    public interface IMoveValidator
    {
        bool IsValidWhiteMove(int newX, int newY);

        bool IsValidBlackMove(int newX, int newY);
    }
}
