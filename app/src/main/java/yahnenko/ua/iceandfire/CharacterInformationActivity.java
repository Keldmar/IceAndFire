package yahnenko.ua.iceandfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        IceAndFireApplication.getApiManager().getByName((HashMap<String, String>) intent.getSerializableExtra("key")).enqueue(new Callback<List<ByName>>() {
            @Override
            public void onResponse(Call<List<ByName>> call, Response<List<ByName>> response) {
                List<ByName> byName = response.body();
                if (byName.isEmpty()) {
                    Toast.makeText(getApplication(), "her", Toast.LENGTH_LONG).show();
                } else {
                    info.setText("culture: " + byName.get(0).culture + "\nborn: " + byName.get(0).born + "\ntitles: " + byName.get(0).titles + "\naliases: " + byName.get(0).aliases + "\ntvSeries: " + byName.get(0).tvSeries + "\nplayed By: " + byName.get(0).playedBy);
                    getSupportActionBar().setTitle(byName.get(0).name);
                    //                Log.d("TAG", "onResponse: ");
                }

            }

            @Override
            public void onFailure(Call<List<ByName>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
//                Toast.makeText(getApplication(),"her", Toast.LENGTH_LONG).show();
            }
        });

        ByName byName = (ByName) getIntent().getSerializableExtra("obj");
        if (byName != null) {
            info.setText("culture: " + byName.culture + "\nborn: " + byName.born + "\ntitles: " + byName.titles + "\naliases: " + byName.aliases + "\ntvSeries: " + byName.tvSeries + "\nplayed By: " + byName.playedBy);
//            info.setText(getString(R.string.information_person, byName.culture, byName.born));
            getSupportActionBar().setTitle(byName.name);
        }
    }

}
