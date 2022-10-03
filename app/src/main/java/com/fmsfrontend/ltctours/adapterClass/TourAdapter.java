package com.fmsfrontend.ltctours.adapterClass;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.activityClass.FirstScreenActivity;
import com.fmsfrontend.ltctours.activityClass.SecondScreenActivity;
import com.fmsfrontend.ltctours.modelClass.TourModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> implements Filterable {
   private Context context;
   List<TourModel> modelList;
    List<TourModel> filterModelList;

    public TourAdapter(Context context, List<TourModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        filterModelList = new ArrayList<>(modelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_tour_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourModel tourModel=modelList.get(position);
        Picasso.get().load("http://bhdays.saumyainfo.com/"+tourModel.getLtc_images()).into(holder.imageView);
        holder.txtTitle.setText(tourModel.getHeading());
        holder.txtDescription.setText(tourModel.getLtc_days());
        holder.layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SecondScreenActivity.class);
                intent.putExtra("idKey",tourModel.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TourModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (TourModel item : filterModelList) {
                    if (item.getHeading().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelList.clear();
            modelList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
       TextView txtTitle,txtDescription;
       LinearLayout layoutButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            layoutButton=itemView.findViewById(R.id.layoutButton);
        }
    }
}
