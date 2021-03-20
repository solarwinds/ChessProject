using NUnit.Framework;
using src.Extensions;

namespace tests
{
    [TestFixture]
    public class UtilsTests
    {
        [Test]
        public void IsWithinRange_True()
        {
            Assert.IsTrue(1.IsWithinRange(0, 7));
            Assert.IsTrue(7.IsWithinRange(0, 7));

        }

        [Test]
        public void IsWithinRange_False()
        {

            Assert.IsFalse(8.IsWithinRange(0, 7));
            const int negative = -1;
            Assert.IsFalse(negative.IsWithinRange(0, 7));
        }
    }
}