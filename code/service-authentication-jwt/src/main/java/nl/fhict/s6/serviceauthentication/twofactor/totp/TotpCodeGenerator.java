package nl.fhict.s6.serviceauthentication.twofactor.totp;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

public class TotpCodeGenerator {
    public String generateSecretKey()
    {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[20];
        secureRandom.nextBytes(bytes);
        return new Base32().encodeToString(bytes);
    }
    public String getTotpCode(String secretKey)
    {
        byte[] bytes = new Base32().decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

}
