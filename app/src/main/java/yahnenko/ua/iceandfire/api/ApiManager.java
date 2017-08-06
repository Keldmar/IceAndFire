package yahnenko.ua.iceandfire.api;


import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yahnenko.ua.iceandfire.IceAndFireAPI;
import yahnenko.ua.iceandfire.response.ByBooks;
import yahnenko.ua.iceandfire.response.ByHouses;
import yahnenko.ua.iceandfire.response.ByName;

public class ApiManager {
    public static final String NAMES_ON_THR_PAGE = "20";
    private final String URL = "https://anapioficeandfire.com/api/";

    private IceAndFireAPI service;

    public ApiManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttUrl = original.url();
                HttpUrl url = originalHttUrl.newBuilder()
                        .addQueryParameter("pageSize", NAMES_ON_THR_PAGE)
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(URL)
                .client(httpClient.build())
                .build();

        service = retrofit.create(IceAndFireAPI.class);
    }

    public Call<List<ByName>> getByName(HashMap<String, String> params) {
        return service.getByName(params);
    }
    public Call<List<ByBooks>> getByBooks(HashMap<String, String> params) {
        return service.getByBooks(params);
    }
    public Call<List<ByHouses>> getByHouses(HashMap<String, String> params) {
        return service.getByHouses(params);
    }

}
