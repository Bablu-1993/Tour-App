package com.fmsfrontend.ltctours.activityClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.RecAdapter;
import com.fmsfrontend.ltctours.adapterClass.TourAdapter;
import com.fmsfrontend.ltctours.modelClass.Movie;
import com.fmsfrontend.ltctours.modelClass.TourModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondScreenActivity extends AppCompatActivity {
    RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    TextView txtTitle,txtCall,txtDownload;
    String UserId;
    ImageView tour_image;
    private TextView txtHeader,txtDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("Big Holidays ");
        toolbar=findViewById(R.id.toolbar);
        txtHeader=findViewById(R.id.txtHeader);
        txtDescription=findViewById(R.id.txtDescription);
        txtDownload=findViewById(R.id.txtDownload);
        txtCall=findViewById(R.id.txtCall);
        tour_image=findViewById(R.id.tour_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        UserId=intent.getStringExtra("idKey");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SecondScreenActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, Integer.parseInt("123"));
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:1800111555")));
                }
            }
        });

        getSecondPageAPICall();

    }


    private void download(Context context, String Filename, String FileExtension, String DesignationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, DesignationDirectory, Filename + FileExtension);
        assert downloadManager != null;
        downloadManager.enqueue(request);
        Snackbar snackbar = (Snackbar) Snackbar
                .make(findViewById(android.R.id.content), "Downloading...", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void getSecondPageAPICall() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://bhdays.saumyainfo.com/html/data_base.php?homeltc&keyword&id="+UserId,
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
                                Picasso.get().load("http://bhdays.saumyainfo.com/"+ltc_images).into(tour_image);
                                txtHeader.setText(heading);
                                txtDescription.setText(ltc_days);
                                txtDownload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String uri = "http://bhdays.saumyainfo.com/"+ltc_pdf;
                                        download(getApplicationContext(), "ltc", ".pdf", "Downloads", uri.trim());
                                    }
                                });

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