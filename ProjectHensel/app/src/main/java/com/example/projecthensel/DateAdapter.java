package com.example.projecthensel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder>
                            implements OnDataItemClickListener{
    ArrayList<Data> items = new ArrayList<>();
    OnDataItemClickListener listener;

    public void addItem(Data item) {items.add(item);}

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.ViewHolder holder, int position) {
        Data item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(DateAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateText, count;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            dateText = itemView.findViewById(R.id.dateText);
            count = itemView.findViewById(R.id.countText);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        Data item = items.get(position);
                        Intent intent = new Intent(v.getContext(), DetailRouteActivity.class);
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }

        public void setItem(Data item){
            dateText.setText(item.getData());
            count.setText(Integer.toString(item.getCount()));
        }
    }
}

