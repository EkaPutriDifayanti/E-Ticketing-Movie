package app.tiket.bioskop.src.Database;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class dbFilmNowPlaying {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "cf36402b78ccc9e2b3a7bb06c037a49";

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?include_adult=false&language=en-US&page=1&api_key=" + apiKey)
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