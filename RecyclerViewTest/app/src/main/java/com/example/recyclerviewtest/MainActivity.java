package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewtest.adapter.ParentItemAdapter;
import com.example.recyclerviewtest.model.ChildItem;
import com.example.recyclerviewtest.model.ParentItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public ParentItemAdapter parentItemAdapter;
    int row, index;
    ArrayList<Integer> col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        row = index = 0;
        col = new ArrayList<Integer>();

        RecyclerView ParentRecyclerViewItem = findViewById(R.id.parent_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        parentItemAdapter = new ParentItemAdapter(ParentItemList());

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
        Random random = new Random();
        int aleat = random.nextInt(10) + 1;
        col.add(aleat);

        for(int i = 0;i<aleat;i++){
            HashMap<String, String> v = jsonParse();
            System.out.println(v.get("name") + " " + v.get("email"));
            ChildItemList.add(new ChildItem("Curtido por " + v.get("name"),
                    (int)(1 + (Math.random() * (6 - 1)))));
        }

        return ChildItemList;
    }

    private HashMap<String, String> jsonParse(){
        //https://jsonplaceholder.typicode.com/
        String url = "https://randomuser.me/api/?inc=name,email,picture";
        HashMap<String, String> ans = new HashMap<>();

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jArray;
                        JSONObject jObject,jNome;
                        String title = null;
                        String status = null;
                        String picture = null;
                        try {
                            jArray = response.getJSONArray("results");

                            /*for(int i = 0; i < jArray.length();i++) {
                                JSONObject innerObj = jArray.getJSONObject(i);
                                for(Iterator it = innerObj.keys(); it.hasNext(); ) {
                                    String key = (String)it.next();
                                    System.out.println(key + ":" + innerObj.get(key));
                                }
                            }*/

                            jObject =  jArray.getJSONObject(0);
                            jNome = (jObject.getJSONObject("name"));
                            title = jNome.getString("first") +" "+jNome.getString("last");
                            status = jObject.getString("email");
                            // large e medium ??
                            picture = (jObject.getJSONObject("picture")).getString("thumbnail");

                            ans.put("name", title);
                            ans.put("email", status);
                            ans.put("picture", picture);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(parentItemAdapter.getItemCount());
                        if(index >= col.get(row)){
                            row++;
                            index = 0;
                        }
                        parentItemAdapter.getPos(row).getChildItemList().get(index++).setChildItemTitle(title);
                        parentItemAdapter.getPos(row).getChildItemList().get(index-1).setChildItemImageName(picture);
                        parentItemAdapter.notifyDataSetChanged();
                        //textView.setText("Title:  "+title + "\n " +
                        //        "Status: " + status);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        //textView.setText(error.toString());
                        Log.d("Plaintext", "Deu ruim");
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(request);
        return ans;
    }
}