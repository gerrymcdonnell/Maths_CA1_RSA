/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testRSAEXample;

/**
 *
 * @author gerry
 */
    import java.math.BigInteger;
    import java.util.Random;


    public class RSA {

        private BigInteger p;
        private BigInteger q;
        private BigInteger N;
        private BigInteger phi;
        private BigInteger e;
        private BigInteger d;
        private int bitlength = 1024;
        private int blocksize = 256; //blocksize in byte

        private Random r;

        /**
         * Init public and private keys
         */
        public RSA() {
            r = new Random();

            // get two big primes
            p = BigInteger.probablePrime(bitlength, r);
            q = BigInteger.probablePrime(bitlength, r);

            //p=BigInteger.valueOf(43);
            //p.setBit(blocksize);

            //q=BigInteger.valueOf(59);
            //q.setBit(blocksize);

            System.out.println("p= "+ p+ " p's bitlength= " + p.bitLength());
            System.out.println("q= " + q + " q's bitlength= " + q.bitLength());

            

            // compute the exponent necessary for encryption (private key)
            //e=BigInteger.valueOf(13);           
            e = BigInteger.probablePrime(bitlength/2, r);
            System.out.println("e's bitlength= " + e.bitLength());

            // get the modulo
            N = p.multiply(q);

            // phi is needed to compute the exponent for encryption
            phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));



            while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {
                e.add(BigInteger.ONE);
            }

            // compute public key
            d = e.modInverse(phi);
            System.out.println("d = " + d);
        }

        public RSA(BigInteger e, BigInteger d, BigInteger N) {
            this.e = e;
            this.d = d;
            this.N = N;
        }

        public static void main (String[] args) {
            RSA rsa = new RSA();

            String teststring = "This is a test for RSA";
            System.out.println("Encrypting String: " + teststring);
            System.out.println("String in Bytes: " + bytesToString(teststring.getBytes()));

            // encrypt
            byte[] encrypted = rsa.encrypt(teststring.getBytes());
            System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));

            // decrypt
            byte[] decrypted = rsa.decrypt(encrypted);
            System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted));

            System.out.println("Decrypted String: " + new String(decrypted));

        }

        /**
         * Converts a byte array into its String representations
         * @param encrypted
         * @return
         */
        private static String bytesToString(byte[] encrypted) {
            String test = "";
            for (byte b : encrypted) {
                test += Byte.toString(b);
            }
            return test;
        }

        /**
         * encrypt byte array
         * @param message
         * @return
         */
        public byte[] encrypt(byte[] message) {
            return (new BigInteger(message)).modPow(e, N).toByteArray();
        }

        /**
         * decrypt byte array
         * @param message
         * @return
         */
        public byte[] decrypt(byte[] message) {
            return (new BigInteger(message)).modPow(d, N).toByteArray();
        }

    }  
