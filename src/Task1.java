public class Task1 {
    
    public static void main(String[] args) {
        BigIntegerUtility bigIntegerUtility = new BigIntegerUtility();
        
        // Generate p and q as primes
        var p = bigIntegerUtility.generatePrime();
        var q = bigIntegerUtility.generatePrime();
        
        // Assert that p not equal to q
        while(p.equals(q)) {
            q = bigIntegerUtility.generatePrime();
        }
        
        var n = bigIntegerUtility.calculateN(p, q);
        var phiN = bigIntegerUtility.calculatePhi(p, q);
        var e = bigIntegerUtility.findE(phiN);

        System.out.println("p " + p + " p.bitLength() " + p.bitLength());
        System.out.println("q " + q + " q.bitLength() " + q.bitLength());
        System.out.println("n " + n);
        System.out.println("phiN " + phiN);
        System.out.println("e " + e);
    }
}
