package com.example.tpgr5msi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Voiture_class> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListAdapter(Context aContext, ArrayList<Voiture_class> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_custom_list_view, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textId);
            holder.modele = (TextView) convertView.findViewById(R.id.textModele);
            holder.Marque = (TextView) convertView.findViewById(R.id.textMarque);
            holder.Carburant = (TextView) convertView.findViewById(R.id.textCarburant);
            holder.prix = (TextView) convertView.findViewById(R.id.textPrix);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(150,245,170));
        }

        Voiture_class product = this.listData.get(position);
        holder.id.setText(""+product.getId());
        holder.modele.setText(product.getModele());
        holder.Marque.setText(product.getMarque());
        holder.Carburant.setText(product.getCarburant());
        holder.prix.setText(String.valueOf(product.getPrix()));

        return convertView;
    }

    static class ViewHolder {
        TextView id, modele, Marque, Carburant, prix;
    }

    public int getCount() {
        return (listData!=null)?listData.size():0;
    }
    public Object getItem(int position) {
        return listData.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
}