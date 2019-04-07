package com.klarna.weather.Views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klarna.weather.R;

import java.util.HashMap;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.SelectableViewHolder> {

    private HashMap<String, String> mData;
    private String[] mKeys;

    public WeatherAdapter(HashMap<String, String> data) {
        mData = data;
        mKeys = mData.keySet().toArray(new String[data.size()]);
    }


    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @NonNull
    @Override
    public SelectableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_weather, viewGroup, false);

        return new SelectableViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectableViewHolder holder, int position) {
       holder.label.setText(mKeys[position]);
       holder.value.setText(mData.get(mKeys[position]));
    }

    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        else return 0;
    }

    public void updateData(HashMap<String, String> value) {
        mData=value;
        notifyDataSetChanged();
    }

    public class SelectableViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView value;

        public SelectableViewHolder(View view) {
            super(view);
            label = view.findViewById(R.id.label);
            value = view.findViewById(R.id.value);
        }
    }


}
