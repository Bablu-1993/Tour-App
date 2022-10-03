package com.fmsfrontend.ltctours.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.HorizontalItemListAdapter;
import com.fmsfrontend.ltctours.adapterClass.TourAdapter;
import com.fmsfrontend.ltctours.modelClass.ItemModel;
import com.fmsfrontend.ltctours.modelClass.TourModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    HorizontalItemListAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<ItemModel> modelList;
    TourAdapter tourAdapter;
    RecyclerView recyclerView2;
    RecyclerView.LayoutManager layoutManager2;
    List<TourModel> tourModelList;
    ProgressBar progressBar;
    SearchView searchView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView2=view.findViewById(R.id.recyclerview_tour);
        progressBar=view.findViewById(R.id.progressBar);
        searchView=view.findViewById(R.id.searchView);
        layoutManager2=new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager2);
      //  et_Email=view.findViewById(R.id.et_Email);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,
                true);
        recyclerView.setLayoutManager(layoutManager);
        modelList=new ArrayList<>();
        tourModelList=new ArrayList<>();
        modelList.add(new ItemModel(R.drawable.calc,"Entitlement \n Calculation","",""));
        modelList.add(new ItemModel(R.drawable.ltcpackage,"LTC/HTC \n Packages","",""));
        modelList.add(new ItemModel(R.drawable.review,"Rules & Policies","",""));
        modelList.add(new ItemModel(R.drawable.like,"Guest Reviews","",""));

        Collections.reverse(modelList);
        adapter=new HorizontalItemListAdapter(getContext(),modelList);
        recyclerView.setAdapter(adapter);
        searchView.setGravity(Gravity.START);
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
                tourAdapter.getFilter().filter(query);
                return false;
            }
        });
        ListAPICall();
        return view;
    }

    private void ListAPICall() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://bhdays.saumyainfo.com/html/data_base.php?homeltc&&keyword=&id",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            JSONArray jsonArray=object.getJSONArray("result");
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject object1=jsonArray.getJSONObject(i);
                                String id=object1.getString("id");
                                String heading=object1.getString("ltc_heading");
                                String ltc_days=object1.getString("ltc_days");
                                String ltc_images=object1.getString("ltc_images");
                                String ltc_pdf=object1.getString("ltc_pdf");
                                String  ltc_file=object1.getString("ltc_file");
                                tourModelList.add(new TourModel(id,heading,ltc_days,ltc_images,ltc_pdf,ltc_file));
                                tourAdapter=new TourAdapter(getContext(),tourModelList);
                                recyclerView2.setAdapter(tourAdapter);
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