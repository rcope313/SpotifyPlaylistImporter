package models;

public class AccessToken {
    public final String value;

    public AccessToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
