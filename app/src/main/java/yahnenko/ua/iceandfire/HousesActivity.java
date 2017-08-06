package yahnenko.ua.iceandfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yahnenko.ua.iceandfire.adapter.RecyclerViewAdapterHouses;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackHouses;
import yahnenko.ua.iceandfire.response.ByHouses;



public class HousesActivity extends AppCompatActivity implements ItemClickCallbackHouses{
    private EditText searchByHouses;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_houses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        HashMap<String, String> queries = new HashMap<>();
        queries.put("hasWords", "all ");
        final RecyclerViewAdapterHouses recyclerViewAdapterHouses = new RecyclerViewAdapterHouses(this);
        IceAndFireApplication.getApiManager().getByHouses(queries).enqueue(new Callback<List<ByHouses>>() {
            @Override
            public void onResponse(Call<List<ByHouses>> call, Response<List<ByHouses>> response) {
                List<ByHouses> byHouses = response.body();
                recyclerViewAdapterHouses.addView(byHouses);
                recyclerView.setAdapter(recyclerViewAdapterHouses);
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByHouses>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
        searchByHouses = (EditText) findViewById(R.id.search_houses);
        searchByHouses.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String name = searchByHouses.getText().toString();
                    HashMap<String, String> searchName = new HashMap<>();
                    searchName.put("name", name);
                    Intent intent = new Intent(HousesActivity.this , HouseInformationActivity.class);
                    intent.putExtra("key" , searchName);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onHouseClick(ByHouses byHouses) {
        Intent intent = new Intent(HousesActivity.this , HouseInformationActivity.class);
        intent.putExtra("obj", byHouses);
        startActivity(intent);
    }
}
