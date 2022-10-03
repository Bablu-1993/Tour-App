package com.fmsfrontend.ltctours.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.ReviewAdapter;
import com.fmsfrontend.ltctours.modelClass.ReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReviewsFragment extends Fragment {
     View view;
    private TextView txtReviewName;
    private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
   private List<ReviewModel> modelList;
   ReviewAdapter adapter;
   ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_reviews, container, false);
        txtReviewName=view.findViewById(R.id.txtReviewName);
        recyclerView=view.findViewById(R.id.recyclerview_review);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        progressBar=view.findViewById(R.id.progressBar);
        modelList=new ArrayList<>();
        getReviewAPICall();
        return view;
    }
    private void getReviewAPICall() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://bhdays.saumyainfo.com/html/data_base.php?review",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            JSONArray jsonArray=object.getJSONArray("result");
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject object1=jsonArray.getJSONObject(i);
                                String id=object1.getString("review_id");
                                String review=object1.getString("review");
                                String user_id=object1.getString("user_id");
                                String status=object1.getString("review_status");
                               ReviewModel reviewModel=new ReviewModel(id,review,user_id,status);
                               modelList.add(reviewModel);
                               adapter=new ReviewAdapter(getContext(),modelList);
                               recyclerView.setAdapter(adapter);
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}