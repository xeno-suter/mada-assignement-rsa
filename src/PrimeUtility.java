import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeUtility {
    
    public static final int RSA_SIZE = 1024;
    
    public BigInteger generatePrime() {
        // Use SecureRandom so seed cant be determined by the date and time of execution
        return BigInteger.probablePrime(RSA_SIZE, new SecureRandom());
    }
}
