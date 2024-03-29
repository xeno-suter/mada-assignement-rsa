import java.math.BigInteger;
import java.security.SecureRandom;

public class BigIntegerUtility {
    
    public static final int RSA_SIZE = 1024;
    
    public BigInteger generatePrime() {
        // Use SecureRandom so seed cant be determined by the date and time of execution
        return BigInteger.probablePrime(RSA_SIZE, new SecureRandom());
    }
    
    public BigInteger calculateN(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }
    
    public BigInteger calculatePhi(BigInteger p, BigInteger q) {
        // We can ignore the exponent here as it is ^1 and will result to 1
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }
    
    public BigInteger findE(BigInteger phiN) {
        // Begin with 2 as e because 1 is not always coprime with phiN
        BigInteger e = BigInteger.valueOf(2);
        while (e.compareTo(phiN) < 0) {
            // E is found when e and phiN are coprime ("teilerfremd")
            if (isCoprime(phiN, e)) {
                break;
            }
            
            // Check the next number
            e = e.add(BigInteger.ONE);
        }
        return e;
    }
    
    public BigInteger findD(BigInteger e, BigInteger phiN) {
        BigInteger x0 = BigInteger.ONE;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger a = phiN;
        BigInteger b = e;
        
        // Apply the extended Euclidean algorithm
        while (!b.equals(BigInteger.ZERO)) {
            // Calculate quotient and remainder
            BigInteger q = a.divide(b); // q = a / b
            BigInteger r = a.mod(b);    // r = a % b

            // Update a' and b' for the next iteration
            a = b;
            b = r;

            // Update coefficients
            var oldx0 = x0;
            var oldx1 = x1;
            x0 = x1;
            x1 = oldx0.subtract(q.multiply(oldx1));
            
            var oldy0 = y0;
            var oldy1 = y1;
            y0 = y1;
            y1 = oldy0.subtract(q.multiply(oldy1));
        }

        // Ensure d is positive
        if (y0.compareTo(BigInteger.ZERO) < 0) {
            y0 = y0.add(phiN);
        }

        return y0;
    }

    // Find the greatest common divisor
    private BigInteger ggT(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger t = b;
            // Find the remainder of a divided by b
            b = a.mod(b);
            // and assign it to a
            a = t;
        }
        
        // a is now the greatest common divisor
        return a;
    }

    private boolean isCoprime(BigInteger phiN, BigInteger e) {
        return ggT(e, phiN).equals(BigInteger.ONE);
    }

    // Fast exponentiation algorithm (based on the slides from the lecture)
    public BigInteger fastExponentiation(BigInteger x, BigInteger l, BigInteger m) {
        BigInteger i = l; // l is the exponent
        BigInteger h = BigInteger.ONE; // h is the result
        BigInteger k = x; // k is the base

        while (i.compareTo(BigInteger.ZERO) > 0) { // While exponent is greater than 0
            if (i.testBit(0)) {
                // if exponent is odd
                h = h.multiply(k).mod(m); // h = h * k mod m
            }
            k = k.multiply(k).mod(m); // square the base and take modulo m
            i = i.shiftRight(1); // divide i by 2
        }

        return h;
    }
}
