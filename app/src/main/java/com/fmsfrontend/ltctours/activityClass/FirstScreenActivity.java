package com.fmsfrontend.ltctours.activityClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.FirstScreenAdapter;
import com.fmsfrontend.ltctours.modelClass.ItemModel;

import java.util.ArrayList;

public class FirstScreenActivity extends AppCompatActivity {
private RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
FirstScreenAdapter adapter;
ArrayList<ItemModel> modelList;
Toolbar toolbar;
TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        recyclerView=findViewById(R.id.recyclerview_first);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        toolbar=findViewById(R.id.toolbar);
        txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setText("Big Holidays ");
        modelList=new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onBackPressed();
          }
      });
        modelList.add(new ItemModel(R.drawable.ltcpackage,"TRENDING LTC TOUR PACKAGES","dfsdfsghth","fhdfgsdgfgfhsd"));
        modelList.add(new ItemModel(R.drawable.ltcpackage,"TRENDING LTC TOUR PACKAGES","dfsdfsghth","fhdfgsdgfgfhsd"));
        modelList.add(new ItemModel(R.drawable.ltcpackage,"TRENDING LTC TOUR PACKAGES","dfsdfsghth","fhdfgsdgfgfhsd"));
         adapter=new FirstScreenAdapter(this,modelList);
        recyclerView.setAdapter(adapter);


    }
}








































































































































