package com.example.adamerikdominik_bevasarlasbeadando;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edittextName;
    private EditText edittextOnePrice;
    private EditText editTextCount;
    private EditText editTextMertek;
    private Button addButton;
    private Button listaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    public void init(){
        edittextName= findViewById(R.id.edit_textName);
        edittextOnePrice= findViewById(R.id.edit_textOnePrice);
        editTextCount= findViewById(R.id.edit_textCount);
        editTextMertek= findViewById(R.id.edit_textMertek);
        addButton= findViewById(R.id.addButton);
        listaButton= findViewById(R.id.listaButton);
        RetrofitApiService apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTermek(apiService);
            }
        });
        listaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void addTermek(RetrofitApiService apiService) {
        String name = edittextName.getText().toString().trim();
        String onePrice = edittextOnePrice.getText().toString().trim();
        String count = editTextCount.getText().toString().trim();
        String mertek = editTextMertek.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "A név mező nem lehet üres!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (onePrice.isEmpty()) {
            Toast.makeText(this, "Az egységár mező nem lehet üres!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!onePrice.matches("\\d+")) {
            Toast.makeText(this, "Az egységárnak pozitív egész számnak kell lennie!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (count.isEmpty()) {
            Toast.makeText(this, "A mennyiség mező nem lehet üres!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!count.matches("\\d+(\\.\\d+)?")) {
            Toast.makeText(this, "A mennyiségnek pozitív számnak kell lennie!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mertek.isEmpty()) {
            Toast.makeText(this, "A mértékegység mező nem lehet üres!", Toast.LENGTH_SHORT).show();
            return;
        }
        Termekek newTermek = new Termekek(
                name,
                Integer.parseInt(onePrice),
                Float.parseFloat(count),
                mertek
        );
        apiService.createTermekek(newTermek).enqueue(new Callback<Termekek>() {
            @Override
            public void onResponse(Call<Termekek> call, Response<Termekek> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Sikeres hozzáadás!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nem sikerült hozzáadni a terméket!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Termekek> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Hiba történt a termék hozzáadása közben!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}