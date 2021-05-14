package com.example.roombasic1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    private List<User> listName;
    private Context ctx;
    private LayoutInflater inflater;
    public static View selectedView;

    public Adapter(List<User> listName, Context ctx) {
        this.listName = listName;
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item1, parent, false);
        return new viewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvName.setText(listName.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvName;
        public final Adapter adt;
        public viewHolder(@NonNull View itemView, Adapter adt) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.adt = adt;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println(((TextView)v.findViewById(R.id.tvName)).getText().toString());
            if(selectedView == null){
                selectedView = v;
                v.setBackgroundColor(Color.parseColor("#5fe8b7"));
                return;
            }
            if(v == selectedView){
                return;
            }
            v.setBackgroundColor(Color.parseColor("#5fe8b7"));
            selectedView.setBackgroundColor(Color.parseColor("#6FBDA1"));
            selectedView = v;
            User entity = listName.get(getLayoutPosition());
            Act1.idSelected = entity.getUid();
        }
    }
}
