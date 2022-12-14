package com.example.recyclerviewtest.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.RetrieveImage;
import com.example.recyclerviewtest.model.ChildItem;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;

    ChildItemAdapter(List<ChildItem> childItemList) {
        this.ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater .from(viewGroup.getContext()).inflate( R.layout.child_item,viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull ChildViewHolder childViewHolder, int position) {
        ChildItem childItem = ChildItemList.get(position);
        childViewHolder.ChildItemTitle.setText(childItem.getChildItemTitle());
        childViewHolder.ChildItemRate.setText("Nota: " + String.valueOf(childItem.getChildItemRate()));
        //childViewHolder.ChildItemImage.setImageResource(R.drawable.titan);
        if(!childItem.isImageChanged()){
            childItem.setImageChanged(true);
            new RetrieveImage(childViewHolder.ChildItemImage)
                    .execute(childItem.getChildItemImageName());
        }
    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView ChildItemTitle, ChildItemRate;
        ImageView ChildItemImage;
        ChildViewHolder(View itemView) {
            super(itemView);
            ChildItemTitle  = itemView.findViewById( R.id.child_item_title);
            ChildItemRate = itemView.findViewById(R.id.child_item_rate);
            ChildItemImage = itemView.findViewById(R.id.img_child_item);
        }
    }
}