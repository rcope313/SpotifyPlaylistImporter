package manager;

public class TokenManager {
    private static final ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();

    public static void setToken (String auth) {
        ACCESS_TOKEN.set(auth);
    }

    public static String getToken() {
        return ACCESS_TOKEN.get();
    }

    public static void clear(){
        ACCESS_TOKEN.remove();
    }
}


