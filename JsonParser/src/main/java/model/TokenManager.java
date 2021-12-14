package model;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TokenManager {
    public File tempFile;

    public TokenManager() {}

    public static void main (String[] args) throws IOException {
        TokenManager tm = new TokenManager();

        tm.setTempFile();
        tm.storeToken("abc");

        System.out.print(tm.getToken());

        tm.removeTempFile();
        System.out.print(tm.tempFile);

    }

    public void setTempFile() throws IOException {
        tempFile = File.createTempFile("tokenFile", null);
    }

    public File getTempFile() {
        if (tempFile == null) {
            throw new IllegalStateException("Temporary file not set or deleted");
        } else {
            return tempFile;
        }
    }

    public void storeToken (String token) {
        try (var os = new FileOutputStream(tempFile)) {
            String content = token;
            os.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken () throws IOException {

        InputStream inStream = new FileInputStream(tempFile);
        return new String(inStream.readAllBytes(), StandardCharsets.UTF_8);

    }

    public void removeTempFile() {
        if (tempFile == null) {
            throw new IllegalStateException("Temporary file has not been created");
        } else {
            tempFile.delete();
            tempFile = null;
        }
    }



}
