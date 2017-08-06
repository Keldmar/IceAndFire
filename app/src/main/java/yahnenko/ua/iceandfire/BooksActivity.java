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
import yahnenko.ua.iceandfire.adapter.RecyclerViewAdapterBooks;
import yahnenko.ua.iceandfire.interfaceForclick.ItemClickCallbackBooks;
import yahnenko.ua.iceandfire.response.ByBooks;


public class BooksActivity extends AppCompatActivity implements ItemClickCallbackBooks {
    private EditText search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_books);
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
        queries.put("fromReleaseDate", "all ");
        final RecyclerViewAdapterBooks recyclerViewAdapterBooks = new RecyclerViewAdapterBooks(this);
        IceAndFireApplication.getApiManager().getByBooks(queries).enqueue(new Callback<List<ByBooks>>() {
            @Override
            public void onResponse(Call<List<ByBooks>> call, Response<List<ByBooks>> response) {
                List<ByBooks> byBooks = response.body();
                recyclerViewAdapterBooks.addView(byBooks);
                recyclerView.setAdapter(recyclerViewAdapterBooks);
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByBooks>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
        search = (EditText) findViewById(R.id.search);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String name = search.getText().toString();
                    HashMap<String, String> searchBook = new HashMap<>();
                    searchBook.put("name", name);
                    Intent intent = new Intent(BooksActivity.this, BooksInformationActivity.class);
                    intent.putExtra("key", searchBook);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBooksClick(ByBooks byBooks) {
        Intent intent = new Intent(BooksActivity.this , BooksInformationActivity.class);
        intent.putExtra("obj", byBooks);
        startActivity(intent);
    }
}
