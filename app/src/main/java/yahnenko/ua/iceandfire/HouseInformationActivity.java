package yahnenko.ua.iceandfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yahnenko.ua.iceandfire.response.ByHouses;


public class HouseInformationActivity extends AppCompatActivity{
    private TextView house_info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        house_info = (TextView) findViewById(R.id.houses_info);

        final Intent intent = getIntent();
        IceAndFireApplication.getApiManager().getByHouses((HashMap<String, String>) intent.getSerializableExtra("key")).enqueue(new Callback<List<ByHouses>>() {
            @Override
            public void onResponse(Call<List<ByHouses>> call, Response<List<ByHouses>> response) {
                List<ByHouses> byHouses = response.body();
                house_info.setText(byHouses.get(0).name );
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByHouses>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });

        ByHouses byHouses = (ByHouses) getIntent().getSerializableExtra("obj");
        if (byHouses != null) {
            house_info.setText(byHouses.name);
        }
    }

}
