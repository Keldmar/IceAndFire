package yahnenko.ua.iceandfire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yahnenko.ua.iceandfire.response.FoTheName;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name;
        name = "Jon Snow";
        IceAndFireApplication.getApiManager().intrfname(name).enqueue(new Callback<List<FoTheName>>() {
            @Override
            public void onResponse(Call<List<FoTheName>> call, Response<List<FoTheName>> response) {
                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<FoTheName>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
}
