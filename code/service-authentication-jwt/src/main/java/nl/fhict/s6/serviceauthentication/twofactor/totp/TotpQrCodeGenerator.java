package nl.fhict.s6.serviceauthentication.twofactor.totp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TotpQrCodeGenerator {
    public String generateBarCodeString(String secretKey,String account,String issuer)
    {
        String utf = java.nio.charset.StandardCharsets.UTF_8.toString();
        try
        {
            return "otpauth://totp/" +
                    URLEncoder.encode("issuer"+ ":"+ account,utf).replace("+","%20")+
                    "?secret=" +URLEncoder.encode(secretKey,utf).replace("+","%20")+
                    "&issuer=" +URLEncoder.encode(issuer,utf).replace("+","%20");
        }
        catch (UnsupportedEncodingException encodingException)
        {
            throw new IllegalStateException();
        }
    }
}
