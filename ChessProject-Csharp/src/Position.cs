using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class representing X and Y Cordinates
    /// </summary>
    public struct Position : IEquatable<Position>
    {
        /// <summary>
        /// X Coordinate
        /// </summary>
        public int XCoordinate
        {
            get;
        }

        /// <summary>
        /// Y Coordinate
        /// </summary>
        public int YCoordinate
        {
            get;
        }

        /// <summary>
        /// Initializes Position
        /// </summary>
        /// <param name="xValue">XCoordinate</param>
        /// <param name="yValue">YCoordinate</param>
        public Position(int xValue, int yValue)
        {
            XCoordinate = xValue;
            YCoordinate = yValue;
        }

        public override string ToString()
        {
            return XCoordinate.ToString() + ',' + YCoordinate.ToString();
        }

        #region Equality and HashCode

        /// <summary>
        /// Determines whether <paramref name="obj"/> is equal to this instance.
        /// </summary>
        /// <param name="obj">The <see cref="System.Object" /> to compare with this instance.</param>
        /// <returns>
        ///   <c>true</c> if the <paramref name="obj"/> is equal to this instance; otherwise, <c>false</c>.
        public override bool Equals(object obj)
        {
            Position? other = obj as Position?;

            if (!other.HasValue)
                return false;

            return this == other.Value;
        }

        /// <summary>
        /// Determines whether <paramref name="other"/> is equal to this instance.
        /// </summary>
        /// <param name="other">The Position to compare with this instance.</param>
        /// <returns>
        ///   <c>true</c> if the <paramref name="other"/> is equal to this instance; otherwise, <c>false</c>.
        /// </returns>
        public bool Equals(Position other)
        {
            return this == other;
        }

        /// <summary>
        /// Determines whether <paramref name="left"/> and <paramref name="right"/> are considered not equal.
        /// </summary>
        /// <param name="left">The left Position to compare.</param>
        /// <param name="right">The right Position to compare.</param>
        /// <returns>False if left is equal to right, else true.</returns>
        public static bool operator !=(Position left, Position right)
        {
            return !(left == right);
        }

        /// <summary>
        /// Determines whether <paramref name="left"/> and <paramref name="right"/> are considered equal.
        /// </summary>
        /// <param name="left">The left Position to compare.</param>
        /// <param name="right">The right Position to compare.</param>
        /// <returns>true if left is equal to right, else false.</returns>
        public static bool operator ==(Position left, Position right)
        {
            return left.XCoordinate == right.XCoordinate && left.YCoordinate == right.YCoordinate;
        }

        /// <summary>
        /// Returns a hash code for this instance.
        /// </summary>
        /// <returns>
        /// A hash code for this instance, suitable for use in hashing algorithms and data structures like a hash table. 
        /// </returns>
        public override int GetHashCode()
        {
            unchecked // Overflow is fine, just wrap
            {
                int hash = 17;

                hash = hash * 29 + this.XCoordinate.GetHashCode();
                hash = hash * 29 + this.YCoordinate.GetHashCode();

                return hash;
            }
        }

        #endregion
    }
}
