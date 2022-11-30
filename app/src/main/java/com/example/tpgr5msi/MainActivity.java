package com.example.tpgr5msi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tpgr5msi.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Voiture_class> listData = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, listData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Voiture_class upload = (Voiture_class) o;
                Intent intent = new Intent(MainActivity.this, EditVoitureActivity.class);
                intent.putExtra("id", upload.getId());
                intent.putExtra("modele", upload.getModele());
                intent.putExtra("Marque", upload.getMarque());
                intent.putExtra("Carburant", upload.getCarburant());
                intent.putExtra("prix", upload.getPrix());
                startActivity(intent);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditVoitureActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Voiture_class> getListData(){
        try{
            Connection_REST_API connectionRest = new Connection_REST_API();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            if(listJsonObjs != null) {
                return connectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}