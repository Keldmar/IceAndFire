package yahnenko.ua.iceandfire;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import yahnenko.ua.iceandfire.response.ByBooks;
import yahnenko.ua.iceandfire.response.ByHouses;
import yahnenko.ua.iceandfire.response.ByName;

public interface IceAndFireAPI {
    @GET("characters")
    Call<List<ByName>> getByName(@QueryMap HashMap<String, String> params);
    @GET("books")
    Call<List<ByBooks>> getByBooks(@QueryMap HashMap<String, String> params);
    @GET("houses")
    Call<List<ByHouses>> getByHouses(@QueryMap HashMap<String, String> params);
}
