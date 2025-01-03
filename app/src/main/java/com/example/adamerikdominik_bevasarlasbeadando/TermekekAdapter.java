package com.example.adamerikdominik_bevasarlasbeadando;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TermekekAdapter extends BaseAdapter {

    private List<Termekek> termekekList;
    private Context context;

    public TermekekAdapter(List<Termekek> termekekList, Context context) {
        this.termekekList = termekekList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return termekekList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView nameTextWiew = view.findViewById(R.id.NameTextView);
        TextView countTextView = view.findViewById(R.id.countTextView);
        TextView priceTextView = view.findViewById(R.id.priceTextView);

        TextView bruttonPriceTextView = view.findViewById(R.id.bruttonPriceTextView);
        Termekek termekek = termekekList.get(i);
        nameTextWiew.setText(termekek.getName());
        priceTextView.setText(termekek.getPrice());
        countTextView.setText(termekek.getCount()+ " " +termekek.getMertekegyseg());
        bruttonPriceTextView.setText(termekek.getBruttoPrice()+ "Ft");

        return view;
    }
}
