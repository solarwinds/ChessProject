namespace src.Extensions
{
    public static class Utilities
    {
        public static bool IsWithinRange(this int value, int minimum, int maximum)
        {
            return value >= minimum && value <= maximum;
        }
    }
}