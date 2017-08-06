package yahnenko.ua.iceandfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView goCharacter;
    private TextView goBooks;
    private TextView goHouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goCharacter = (TextView) findViewById(R.id.button_name);
        goBooks = (TextView) findViewById(R.id.button_books);
        goHouses = (TextView) findViewById(R.id.button_houses);

        goCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CharactersActivity.class);
                startActivity(intent);
            }
        });

        goBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });
        goHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HousesActivity.class);
                startActivity(intent);
            }
        });

    }
}
