package manager;

public class TokenManager {
    private static String ACCESS_TOKEN = "";

    public static void setToken (String auth) {
        ACCESS_TOKEN = auth;
    }

    public static String getToken() {
        return ACCESS_TOKEN;
    }


}


