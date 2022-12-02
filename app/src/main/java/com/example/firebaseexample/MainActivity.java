package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton tambah;
    AdapterPemain adapterPemain;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelPemain> listPemain;
    RecyclerView tv_tampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambah = findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

        tv_tampil = findViewById(R.id.tv_tampil);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tv_tampil.setLayoutManager(mLayout);
        tv_tampil.setItemAnimator(new DefaultItemAnimator());
        tampilData();
    }

    private void tampilData() {
        database.child("Pemain").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPemain = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    ModelPemain pmn = item.getValue(ModelPemain.class);
                    pmn.setKey(item.getKey());
                    listPemain.add(pmn);
                }
                adapterPemain = new AdapterPemain(listPemain, MainActivity.this);
                tv_tampil.setAdapter(adapterPemain);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}