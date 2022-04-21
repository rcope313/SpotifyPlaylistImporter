package models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PostRequest {
    private final String code;

    public PostRequest(String code) {
        this.code = code;
    }

    public String buildJsonBodyParameter() throws IOException {
        InputStream inputStream = null;
        String[] strArr = new String[4];
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            strArr[0] = prop.getProperty("grantType");
            strArr[1] = prop.getProperty("redirectUri");
            strArr[2] = prop.getProperty("clientId");
            strArr[3] = prop.getProperty("clientSecret");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return "grant_type=" + strArr[0]
                + "&redirect_uri=" + strArr[1]
                + "&client_id=" + strArr[2]
                + "&client_secret=" + strArr[3]
                + "&code=" + code;
    }

}
