package id.ac.polinema.bukuresep;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.polinema.bukuresep.helper.dataHelper;

public class isiBaruActivity extends AppCompatActivity {

    Cursor cursor;
    dataHelper dbResep;
    Button btnSimpan, btnBatal;
    EditText etAddNama, etAddAlat, etAddBahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_baru);

        dbResep = new dataHelper(this);
        etAddNama = findViewById(R.id.etAddNama);
        etAddAlat = findViewById(R.id.etAddAlat);
        etAddBahan = findViewById(R.id.etAddBahan);

        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SQLiteDatabase db = dbResep.getWritableDatabase();
                db.execSQL("INSERT INTO resep(nama, alat, bahan)values ('" +etAddNama.getText().toString() +"','"+etAddAlat.getText().toString() +"','"+etAddBahan.getText().toString() +"')");
                Toast.makeText(getApplicationContext(), "resep berhasil ditambah", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void RefreshList() {
    }


}
