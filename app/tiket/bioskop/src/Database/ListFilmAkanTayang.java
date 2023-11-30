package app.tiket.bioskop.src.Database;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListFilmAkanTayang {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = ApiRequestUtil.getClient();
        String apiKey = ApiRequestUtil.getApiKey();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/upcoming?include_adult=false&language=en-US&page=1&api_key=" + apiKey)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        // Check if the request was successful (status code 200)
        if (response.isSuccessful()) {
            // Process the response here
            System.out.println(response.body().string());
        } else {
            // Handle the error
            System.out.println("Error: " + response.code() + " - " + response.message());
        }
    }
}