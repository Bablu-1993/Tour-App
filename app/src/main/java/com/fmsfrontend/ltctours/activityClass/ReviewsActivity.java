package com.fmsfrontend.ltctours.activityClass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewsActivity extends AppCompatActivity {
  private TextView txtReviewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        txtReviewName=findViewById(R.id.txtReviewName);
        getReviewAPICall();
    }

    private void getReviewAPICall() {
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
                                txtReviewName.setText(review);
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}