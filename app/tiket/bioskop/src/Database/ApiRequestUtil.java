package app.tiket.bioskop.src.Database;
public class ApiRequestUtil {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = "cf36402b78ccc9e2b3a7bb06c037a49";

    public static OkHttpClient getClient() {
        return client;
    }

    public static String getApiKey() {
        return apiKey;
    }