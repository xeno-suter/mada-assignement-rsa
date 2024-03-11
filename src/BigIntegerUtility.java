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
}
