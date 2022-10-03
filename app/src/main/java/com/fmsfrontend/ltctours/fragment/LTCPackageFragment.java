package com.fmsfrontend.ltctours.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.TourAdapter;
import com.fmsfrontend.ltctours.modelClass.TourModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LTCPackageFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    TourAdapter tourAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<TourModel> tourModelList;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_l_t_c_package, container, false);
        recyclerView=view.findViewById(R.id.recyclerview_ltc);
        progressBar=view.findViewById(R.id.progressBar);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        tourModelList=new ArrayList<>();
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
                                recyclerView.setAdapter(tourAdapter);
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