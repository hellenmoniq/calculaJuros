package com.example.ucl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by UCL on 24/04/2018.
 */
public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.CardViewHolder> {

    private final List<ItemCalcula> mList;
    private Context context;

    public ItensAdapter(Context ctx, @NonNull List<ItemCalcula> attList) {
        mList = attList;
        context = ctx;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTotal;

        CardViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTotal = itemView.findViewById(R.id.tv_total);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list,
                        parent,
                        false)
        );
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        final ItemCalcula item = mList.get(pos);

        holder.tvName.setText("Nome: " + item.getName());
        holder.tvTotal.setText("Valor Total " + String.valueOf(item.getValorfinal()));

        holder.itemView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}