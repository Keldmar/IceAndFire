package yahnenko.ua.iceandfire.api;


import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yahnenko.ua.iceandfire.InterfaceForName;
import yahnenko.ua.iceandfire.response.FoTheName;

public class ApiManager {
    private final String URL = "https://anapioficeandfire.com/api/";

    private InterfaceForName service;

    public ApiManager() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(URL)
                .client(httpClient.build())
                .build();

        service = retrofit.create(InterfaceForName.class);
    }

    public Call<List<FoTheName>> intrfname(String name) {
        return service.intrfname(name);
    }

}
