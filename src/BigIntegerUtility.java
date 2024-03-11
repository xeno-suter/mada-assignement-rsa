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
            if (ggT(e, phiN).equals(BigInteger.ONE)) {
                break;
            }
            
            // Check the next number
            e = e.add(BigInteger.ONE);
        }
        return e;
    }
    
    // Find the greatest common divisor
    public BigInteger ggT(BigInteger a, BigInteger b) {
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
}
