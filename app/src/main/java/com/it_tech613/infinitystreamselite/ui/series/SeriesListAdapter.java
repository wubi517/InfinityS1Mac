package com.it_tech613.infinitystreamselite.ui.series;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.it_tech613.infinitystreamselite.R;
import com.it_tech613.infinitystreamselite.models.SeriesModel;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

class SeriesListAdapter extends RecyclerView.Adapter<SeriesListAdapter.CategoryViewHolder> {
    private List<SeriesModel> list;
    private int selected = 0;
    private Function2<SeriesModel,Integer, Unit> seriesModelIntegerUnitFunction2;

    SeriesListAdapter(List<SeriesModel> list, Function2<SeriesModel,Integer, Unit> seriesModelIntegerUnitFunction2) {
        this.list = list;
        this.seriesModelIntegerUnitFunction2 = seriesModelIntegerUnitFunction2;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                holder.card.setCardElevation(8f);
                holder.itemView.setBackgroundColor(Color.parseColor("#2962FF"));

            }else{
                holder.card.setCardElevation(0f);
                holder.itemView.setBackgroundColor(Color.parseColor("#000096a6"));
            }
        });
        holder.itemView.setOnClickListener(v -> {
            if(selected!=position){
                //clickeListenerFunction(list[position])
                seriesModelIntegerUnitFunction2.invoke(list.get(position),position);
                int previouslySelected = selected;
                selected = position;
                notifyItemChanged(previouslySelected);
                notifyItemChanged(selected);
            }
        });

        if(position==0) holder.itemView.requestFocus();
        if(selected==position) holder.itemView.setBackgroundColor(Color.parseColor("#602962FF"));
        else holder.itemView.setBackgroundColor(Color.parseColor("#002962FF"));

    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CardView card;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
        }
    }
}