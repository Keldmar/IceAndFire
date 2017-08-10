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
import yahnenko.ua.iceandfire.response.ByBooks;


public class BooksInformationActivity extends AppCompatActivity{
    private TextView informBooks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        informBooks = (TextView) findViewById(R.id.info_books);

        final Intent intent = getIntent();
        IceAndFireApplication.getApiManager().getByBooks((HashMap<String, String>)intent.getSerializableExtra("key")).enqueue(new Callback<List<ByBooks>>() {
            @Override
            public void onResponse(Call<List<ByBooks>> call, Response<List<ByBooks>> response) {
                List<ByBooks> byBookses = response.body();
                informBooks.setText(byBookses.get(0).name);
                //                Log.d("TAG", "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<ByBooks>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
        ByBooks byBooks = (ByBooks) getIntent().getSerializableExtra("obj");
        if (byBooks != null) {
            informBooks.setText(byBooks.name);
        }
    }
}
