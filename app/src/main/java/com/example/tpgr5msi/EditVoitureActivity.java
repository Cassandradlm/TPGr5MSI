package com.example.tpgr5msi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class EditVoitureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voiture);

        final int id = getIntent().getIntExtra("id", 0);
        String modele = getIntent().getStringExtra("modele");
        String Marque = getIntent().getStringExtra("Marque");
        String Carburant = getIntent().getStringExtra("Carburant");
        double prix = getIntent().getDoubleExtra("prix", 1.0);

        final EditText modeleEditTxt = (EditText) findViewById(R.id.modeleEditTxt);
        final EditText marqueEditTxt = (EditText) findViewById(R.id.marqueEditTxt);
        final EditText carburantEditTxt = (EditText) findViewById(R.id.carburantEditTxt);
        final EditText prixEditTxt = (EditText) findViewById(R.id.prixEditTxt);
        TextView idTxt = (TextView) findViewById(R.id.textview_id);

        Button buttonCancel = (Button) findViewById(R.id.button_cancel);
        Button buttonOk = (Button) findViewById(R.id.button_ok);

        if(id!=0){
            idTxt.setText(""+id);
            modeleEditTxt.setText(modele);
            marqueEditTxt.setText(Marque);
            carburantEditTxt.setText(Carburant);
            prixEditTxt.setText(String.valueOf(prix));
            buttonCancel.setText("Supprimer");
            buttonOk.setText("Valider");
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
                    try {
                        Connection_REST_API connectionRest = new Connection_REST_API();
                        JSONObject voiture = new JSONObject();
                        voiture.put("id", id);
                        connectionRest.setJsonObj(voiture);
                        connectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(EditVoitureActivity.this, com.example.tpgr5msi.MainActivity.class);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Connection_REST_API connectionRest = new Connection_REST_API();
                    JSONObject voiture = new JSONObject();
                    if(id!=0){
                        voiture.put("id", id);
                    }
                    voiture.put("modele", modeleEditTxt.getText().toString());
                    voiture.put("Marque", marqueEditTxt.getText().toString());
                    voiture.put("Carburant", carburantEditTxt.getText().toString());
                    voiture.put("prix", Double.parseDouble(prixEditTxt.getText().toString()));
                    connectionRest.setJsonObj(voiture);
                    if(id!=0){
                        connectionRest.execute("PUT"); // Modification
                    }else {
                        connectionRest.execute("POST"); // Création
                    }

                    Intent intent = new Intent(EditVoitureActivity.this, com.example.tpgr5msi.MainActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}