package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerviewtest.adapter.ParentItemAdapter;
import com.example.recyclerviewtest.model.ChildItem;
import com.example.recyclerviewtest.model.ParentItem;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(ParentItemList());

        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList = new ArrayList<>();
        ParentItem item0;
        // album da beyonce
        String[] faixas = {"I'm that girl", "Cozy", "Alien Superstar", "Cuff it"
                            , "Energy (feat. beam)", "Break my soul", "Church girl"
                            , "Plastic off the sofa", "Virgo's groove"};

        for(int i = 0;i<faixas.length;i++){
            item0 = new ParentItem( faixas[i], ChildItemList());
            itemList.add(item0);
        }

        return itemList;
    }

    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();
        // pessoinhas
        String[] person = {"Zequinha Tatu", "Camila Caipora", "Jay B. Cullen"
                            , "Alexandrinho NoMilk", "Waltinho", "Renão"
                            , "Tove Lo", "Charlottinha", "Biribinha"
                            , "Default Fov"};

        for(int i = 0;i<person.length;i++){
            ChildItemList.add(new ChildItem("Curtido por " + person[i],
                    (int)(1 + (Math.random() * (6 - 1)))));
        }

        return ChildItemList;
    }
}