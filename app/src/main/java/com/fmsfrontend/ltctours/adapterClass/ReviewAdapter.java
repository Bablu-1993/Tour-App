package com.fmsfrontend.ltctours.adapterClass;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.modelClass.ReviewModel;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context context;
    private List<ReviewModel> modelList;

    public ReviewAdapter(Context context, List<ReviewModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ReviewModel reviewModel=modelList.get(position);
      holder.txtReviewName.setText(reviewModel.getReview());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtReviewName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReviewName=itemView.findViewById(R.id.txtReviewName);
        }
    }
}
