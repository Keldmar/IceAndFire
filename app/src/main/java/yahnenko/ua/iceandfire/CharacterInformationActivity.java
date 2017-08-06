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
import yahnenko.ua.iceandfire.response.ByName;



public class CharacterInformationActivity extends AppCompatActivity {
    private TextView info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        info = (TextView) findViewById(R.id.info);

        final Intent intent = getIntent();
        IceAndFireApplication.getApiManager().getByName((HashMap<String, String>)intent.getSerializableExtra("key")).enqueue(new Callback<List<ByName>>() {
            @Override
            public void onResponse(Call<List<ByName>> call, Response<List<ByName>> response) {
                List<ByName> byName = response.body();
                info.setText(byName.get(0).name + "\nculture:" +byName.get(0).culture + "\ntitles:" +byName.get(0).titles);
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByName>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });

        ByName byName = (ByName) getIntent().getSerializableExtra("obj");
        if (byName != null) {
            info.setText(byName.name + "\nculture:" +byName.culture + "\ntitles:" +byName.titles);
        }
    }

}
