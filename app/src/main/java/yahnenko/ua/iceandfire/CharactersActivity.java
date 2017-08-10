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
    private EditText searchByName;
    private String saveFieldSpinnerIsAlive;
    private String saveFieldSpinnerName;

    final RecyclerViewAdapterCharacters recyclerViewAdapterCharacters = new RecyclerViewAdapterCharacters(CharactersActivity.this);

    final HashMap<String, String> queriesName = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_characters);

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

    private void setInformQueries() {

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        queriesName.put("gender", saveFieldSpinnerName);
        queriesName.put("isAlive", saveFieldSpinnerIsAlive);

        IceAndFireApplication.getApiManager().getByName(queriesName).enqueue(new Callback<List<ByName>>() {
            @Override
            public void onResponse(Call<List<ByName>> call, Response<List<ByName>> response) {
                List<ByName> byName = response.body();

                recyclerViewAdapterCharacters.addView(clippingOfEmpty(byName));
                recyclerView.setAdapter(recyclerViewAdapterCharacters);
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
            if (byName.get(i).name.isEmpty()){
                byName.remove(i);
                i--;
            }
        }
////        for (ByName characters : byName) {
////            if (characters.name.isEmpty()){
////                byName.remove(characters);
////            }
////        }
////
////
//        Stream.of(byName)
//            .filterNot(byName::isEmpty);
////
//////        Iterator it = byName.iterator();
//////        while (it.hasNext())
//////        {
//////
//////            ByName item = (ByName) it.next();
//////            it.remove(item);
//////        }
        return byName;
    }

    @Override
    public void onPersonClick(ByName byName) {
        Intent intent = new Intent(CharactersActivity.this, CharacterInformationActivity.class);
        intent.putExtra("obj", byName);
        startActivity(intent);
    }
}

