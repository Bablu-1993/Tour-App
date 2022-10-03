package com.fmsfrontend.ltctours.adapterClass;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.modelClass.ItemModel;
import com.fmsfrontend.ltctours.modelClass.Movie;

import java.util.ArrayList;
import java.util.List;

public class FirstScreenAdapter extends RecyclerView.Adapter<FirstScreenAdapter.ViewHolder> {
    private Context context;
    private List<ItemModel> modelList;
    List<Integer> positionList=new ArrayList<>();

    public FirstScreenAdapter(Context context, List<ItemModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_first_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     ItemModel itemModel=modelList.get(position);
     holder.imageView.setImageResource(itemModel.getImage());
     holder.txtTitle.setText(itemModel.getTitle());
     holder.bind(itemModel);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
             boolean expanded = itemModel.isExpanded();
             itemModel.setExpanded(!expanded);
             notifyItemChanged(position);
             positionList.add(position);
         }
     });
       /* holder.itemView.setOnClickListener(v -> {

        });*/
    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtTitle;
        private TextView sub_item_title;
        private TextView sub_item_desc;
        private  View layout_SubItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView_first);
            txtTitle=itemView.findViewById(R.id.txtTitle_first);
            sub_item_title=itemView.findViewById(R.id.sub_item_title);
            sub_item_desc=itemView.findViewById(R.id.sub_item_desc);
            layout_SubItem=itemView.findViewById(R.id.layout_Second);

        }

        private void bind(ItemModel itemModel) {
            int position = getAdapterPosition();
               if (position==position){
                   boolean expanded = itemModel.isExpanded();
                   layout_SubItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
                   txtTitle.setText(itemModel.getTitle());
                   sub_item_title.setText("Genre: " + itemModel.getItem_title());
                   sub_item_desc.setText("Year: " + itemModel.getItem_desc());
               }

        }
    }
}
