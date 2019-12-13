package id.ac.polinema.bukuresep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import id.ac.polinema.bukuresep.helper.dataHelper;

public class IsiActivity extends AppCompatActivity {

    public static IsiActivity ma;
    String[] daftarResep;
    ListView listResep;
    Menu menu;
    protected Cursor cursor;
    dataHelper dbResep;
    private dataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi);

        Button btnTambahResep = (Button)findViewById(R.id.btnTambahResep);

        btnTambahResep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IsiActivity.this, isiBaruActivity.class);
                startActivity(i);
            }
        });
        ma = this;
        dbCenter = new dataHelper(this);
        RefreshList();
    }

    private void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM resep", null);
        daftarResep = new String [cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarResep[cc] = cursor.getString(1).toString();
        }
        listResep = findViewById(R.id.listResep);
        listResep.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarResep));
        listResep.setSelected(true);
        listResep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                final String selection = daftarResep[i];
                final CharSequence[] dialogItem = {"Resep"};
                AlertDialog.Builder builder = new AlertDialog.Builder(IsiActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), editIsi.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listResep.getAdapter()).notifyDataSetInvalidated();
    }
}
