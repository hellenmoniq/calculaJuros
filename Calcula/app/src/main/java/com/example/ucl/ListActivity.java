package com.example.ucl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by UCL on 24/04/2018.
 */
public class ListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new Helper(this);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        populateListWithDb();
    }

    private void populateListWithDb() {
        List<ItemCalcula> list = db.getAllItens();

        ItensAdapter attContactAdapter = new ItensAdapter(ListActivity.this, list);
        rvList.setAdapter(attContactAdapter);
        if (rvList != null) {
            rvList.invalidate();
            if (rvList.getAdapter().getItemCount() == 0) {
                rvList.setVisibility(GONE);
            } else {
                rvList.setVisibility(View.VISIBLE);
            }
        }
    }
}
