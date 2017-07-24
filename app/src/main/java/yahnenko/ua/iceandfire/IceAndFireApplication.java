package yahnenko.ua.iceandfire;



import android.app.Application;

import yahnenko.ua.iceandfire.api.ApiManager;

public class IceAndFireApplication extends Application{
    private static ApiManager apiManager;


    public static ApiManager getApiManager() {
        return apiManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        apiManager = new ApiManager();
    }
}
