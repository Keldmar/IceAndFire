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
import yahnenko.ua.iceandfire.adapter.RecyclerViewAdapterCharacters;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackCharacters;
import yahnenko.ua.iceandfire.response.ByName;

public class CharactersActivity extends AppCompatActivity implements ItemClickCallbackCharacters {
    private Integer nextPage = 1;
    private EditText searchByName;
    private String saveFieldSpinnerIsAlive;
    private String saveFieldSpinnerName;
    private RecyclerView recyclerView;
    final RecyclerViewAdapterCharacters recyclerViewAdapterCharacters = new RecyclerViewAdapterCharacters(CharactersActivity.this);

    final HashMap<String, String> queriesName = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_characters);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(recyclerViewAdapterCharacters);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Spinner spinnerGenderFilters = (Spinner) findViewById(R.id.spinner_gender_filters);
        final Spinner spinnerIsAliveFilters = (Spinner) findViewById(R.id.spinner_isAlive_filters);

        spinnerIsAliveFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFieldSpinnerIsAlive = spinnerIsAliveFilters.getSelectedItem().toString();
                setInformQueries();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGenderFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFieldSpinnerName = spinnerGenderFilters.getSelectedItem().toString();
                setInformQueries();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        searchByName = (EditText) findViewById(R.id.search);
        searchByName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    HashMap<String, String> searchName = new HashMap<>();
                    searchName.put("name", searchByName.getText().toString());
                    Intent intent = new Intent(CharactersActivity.this, CharacterInformationActivity.class);
                    intent.putExtra("key", searchName);
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
                        queriesName.put("page", String.valueOf(nextPage));
                        wasPaging = true;
                        setInformQueries();
                    } else {
                        wasPaging = false;
                    }
                }
            }
        });

        queriesName.put("gender", saveFieldSpinnerName);
        queriesName.put("isAlive", saveFieldSpinnerIsAlive);

        IceAndFireApplication.getApiManager().getByName(queriesName).enqueue(new Callback<List<ByName>>() {
            @Override
            public void onResponse(Call<List<ByName>> call, Response<List<ByName>> response) {
                List<ByName> byName = response.body();
                if (wasPaging) {
                    recyclerViewAdapterCharacters.addView(clippingOfEmpty(byName));
                } else {
                    recyclerViewAdapterCharacters.addViewAfterFilters(clippingOfEmpty(byName));
                }
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByName>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }

    private List<ByName> clippingOfEmpty(List<ByName> byName) {
        for (int i = 0; i < byName.size(); i++) {
            if (byName.get(i).name.isEmpty()) {
                byName.remove(i);
                i--;
            }
        }
        return byName;
    }

    @Override
    public void onPersonClick(ByName byName) {
        Intent intent = new Intent(CharactersActivity.this, CharacterInformationActivity.class);
        intent.putExtra("obj", byName);
        startActivity(intent);
    }
}

