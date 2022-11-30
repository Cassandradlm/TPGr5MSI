package com.example.tpgr5msi;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tpgr5msi.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

/*
        try {
            JSONObject json = new JSONObject();
            json.put("id",39);
            json.put("name","Pomme");
            json.put("type","Fruit");
            json.put("price",10.0);
            ConnectionRest connectionRest = new ConnectionRest();
            //connectionRest.setJsonObj(json);
            connectionRest.execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        ArrayList<Product> listData = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, listData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Product upload = (Product) o;
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("id", upload.getId());
                intent.putExtra("name", upload.getName());
                intent.putExtra("type", upload.getType());
                intent.putExtra("price", upload.getPrice());
                startActivity(intent);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Product> getListData(){
        try{
            ConnectionRest connectionRest = new ConnectionRest();
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