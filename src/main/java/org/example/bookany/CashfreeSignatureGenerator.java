package org.example.bookany;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

public class CashfreeSignatureGenerator {

    public static void main(String[] args) throws Exception {
        // Step 1: Define your client_id
        String clientId = "CF742034D012F84OQC2C73FLI2E0";

        // Step 2: Get current UNIX timestamp
        long timestamp = System.currentTimeMillis() / 1000;

        // Step 3: Concatenate clientId and timestamp
        String dataToEncrypt = clientId + "." + timestamp;
        System.out.println("🔗 Data to encrypt: " + dataToEncrypt);

        // Step 4: Cashfree Public Key in PEM format (with real line breaks)
        String publicKeyPem = "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtFiUZjR8Wmr0T8tjKPqX\ngpZPHbPS4AujUR+WiyRPaJdAqMyftgCqRst2Jdib7g5a8UZEj3v80eRicoaKuS08\nL92Ht/uJAFTp3dDYthVji42YN5YYEdzCKmOKngOLsB5lwlr8k+lAeGZtkeEiokXx\nei425vnp5hUsaX93Y//cfafVK0+7zQrYZffQmx1HNLIDfrEv8JEeig83yPfzXUu2\nGpptCRgjcTxyb36dVs0pOGmja7kolEc+3tCRyDcJmsXAtmHcqxzDrCjdObR0+G35\nFHClDEuGL90++KM3qNRe2svQeXy9uQ4Bzfkw6aelK+GkTb+yqN6NUMfb12nNGUiO\nlwIDAQAB\n-----END PUBLIC KEY-----";

        // Step 5: Convert PEM to DER format (strip headers/footers and decode)
        publicKeyPem = publicKeyPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPem);

        // Step 6: Load public key using KeyFactory
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);

        // Step 7: Encrypt the token using RSA with PKCS1 Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // PKCS#1 v1.5
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(dataToEncrypt.getBytes());

        // Step 8: Base64 encode the encrypted result
        String signature = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("✅ X-Cf-Signature:");
        System.out.println(signature);
    }
}
