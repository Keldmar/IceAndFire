package yahnenko.ua.iceandfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yahnenko.ua.iceandfire.adapter.RecyclerViewAdapterHouses;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackHouses;
import yahnenko.ua.iceandfire.response.ByHouses;


public class HousesActivity extends AppCompatActivity implements ItemClickCallbackHouses {
    private Integer nextPage = 1;
    private EditText searchByHouses;
    private String saveFieldSpinnerExtinct;
    private String saveFieldspinnerAncestralWeapons;
    private RecyclerView recyclerView;
    final RecyclerViewAdapterHouses recyclerViewAdapterHouses = new RecyclerViewAdapterHouses(this);
    final HashMap<String, String> queriesHouses = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_houses);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(recyclerViewAdapterHouses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final Spinner spinnerDiedOutFilters = (Spinner) findViewById(R.id.spinner_has_ancestral_weapons_filters);
        final Spinner spinnerAncestralWeaponse = (Spinner) findViewById(R.id.spinner_extinct_houses_filter);

        spinnerAncestralWeaponse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFieldspinnerAncestralWeapons = spinnerAncestralWeaponse.getSelectedItem().toString();
                setInformQueries();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDiedOutFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFieldSpinnerExtinct = spinnerDiedOutFilters.getSelectedItem().toString();
                setInformQueries();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchByHouses = (EditText) findViewById(R.id.search_houses);
        searchByHouses.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    HashMap<String, String> searchHose = new HashMap<>();
                    searchHose.put("name", searchByHouses.getText().toString());
                    Intent intent = new Intent(HousesActivity.this, HouseInformationActivity.class);
                    intent.putExtra("key", searchHose);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    boolean wasPaging;

    private void setInformQueries() {

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    android.support.v7.widget.LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (recyclerView.getAdapter().getItemCount() - 1 == linearLayoutManager.findLastVisibleItemPosition()) {
                        nextPage++;
                        queriesHouses.put("page", String.valueOf(nextPage));
                        wasPaging = true;
                        setInformQueries();
                    } else {
                        wasPaging = false;
                    }
                }
            }
        });

        queriesHouses.put("hasAncestralWeapons", saveFieldspinnerAncestralWeapons);
        queriesHouses.put("hasDiedOut", saveFieldSpinnerExtinct);
        IceAndFireApplication.getApiManager().getByHouses(queriesHouses).enqueue(new Callback<List<ByHouses>>() {
            @Override
            public void onResponse(Call<List<ByHouses>> call, Response<List<ByHouses>> response) {
                List<ByHouses> byHouses = response.body();
                if (wasPaging) {
                    recyclerViewAdapterHouses.addView(byHouses);
                } else {
                    recyclerViewAdapterHouses.addViewAfterFilters(byHouses);
                }


                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByHouses>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }


    @Override
    public void onHouseClick(ByHouses byHouses) {
        Intent intent = new Intent(HousesActivity.this, HouseInformationActivity.class);
        intent.putExtra("obj", byHouses);
        startActivity(intent);
    }
}
