import java.math.BigInteger;

public class Task1 {
    
    public static void main(String[] args) {
        BigIntegerUtility bigIntegerUtility = new BigIntegerUtility();
        
        // Generate p and q as primes
        var p = bigIntegerUtility.generatePrime();
        var q = bigIntegerUtility.generatePrime();
//        var p = BigInteger.valueOf(3);
//        var q = BigInteger.valueOf(11);
        
        // Assert that p not equal to q
        while(p.equals(q)) {
            q = bigIntegerUtility.generatePrime();
        }
        
        var n = bigIntegerUtility.calculateN(p, q);
        var phiN = bigIntegerUtility.calculatePhi(p, q);
        var e = bigIntegerUtility.findE(phiN);
        var d = bigIntegerUtility.findD(e, phiN);

        System.out.println("p " + p + " p.bitLength() " + p.bitLength());
        System.out.println("q " + q + " q.bitLength() " + q.bitLength());
        System.out.println("n " + n);
        System.out.println("phiN " + phiN);
        System.out.println("e " + e);
        System.out.println("d " + d);
    }
}
