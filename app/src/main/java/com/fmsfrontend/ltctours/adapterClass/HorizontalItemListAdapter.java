package com.fmsfrontend.ltctours.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.fragment.EntitlementFragment;
import com.fmsfrontend.ltctours.fragment.LTCPackageFragment;
import com.fmsfrontend.ltctours.fragment.ReviewsFragment;
import com.fmsfrontend.ltctours.fragment.RulesPoliciesFragment;
import com.fmsfrontend.ltctours.modelClass.ItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalItemListAdapter extends RecyclerView.Adapter<HorizontalItemListAdapter.ViewHolder> {
    private Context context;
    List<ItemModel> modelList;

    public HorizontalItemListAdapter(Context context, List<ItemModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_horizontal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ItemModel itemModel=modelList.get(position);
     holder.txtItemName.setText(itemModel.getTitle());
        Picasso.get().load(itemModel.getImage()).resize(150,150)
                .into(holder.txtItemImage);

      /*  holder.layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FirstFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.flFragment, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });*/
        if (position==0){
            holder.layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new ReviewsFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.flFragment, fragment);
                    fragmentTransaction.addToBackStack(null).commit();
                }
            });
        }

        else if (position==1){
            holder.layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new RulesPoliciesFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.flFragment, fragment);
                    fragmentTransaction.addToBackStack(null).commit();
                }
            });

        }
        else if (position==2){
            holder.layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new LTCPackageFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.flFragment, fragment);
                    fragmentTransaction.addToBackStack(null).commit();
                }
            });

        }
        else if (position==3){
            holder.layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new EntitlementFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.flFragment, fragment);
                    fragmentTransaction.addToBackStack(null).commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName;
        CircleImageView txtItemImage;
        ConstraintLayout layoutButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemImage=itemView.findViewById(R.id.txtItemImage);
            txtItemName=itemView.findViewById(R.id.txtItemName);
            layoutButton=itemView.findViewById(R.id.layoutButton);
        }
    }
}
