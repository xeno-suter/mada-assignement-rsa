public class Task1 {
    
    public static void main(String[] args) {
        PrimeUtility primeUtility = new PrimeUtility();
        
        // Generate p and q as primes
        var p = primeUtility.generatePrime();
        var q = primeUtility.generatePrime();
        
        // Assert that p not equal to q
        while(p.equals(q)) {
            q = primeUtility.generatePrime();
        }

        System.out.println("p " + p + " p.bitLength() " + p.bitLength());
        System.out.println("q " + q + " q.bitLength() " + q.bitLength());
    }
}
