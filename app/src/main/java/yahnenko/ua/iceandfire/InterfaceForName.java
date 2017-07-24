package yahnenko.ua.iceandfire;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yahnenko.ua.iceandfire.response.FoTheName;

public interface InterfaceForName {
    @GET("characters")
    Call<List<FoTheName>> intrfname(@Query("name") String name);
}
