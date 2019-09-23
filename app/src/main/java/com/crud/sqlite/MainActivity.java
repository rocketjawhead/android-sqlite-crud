package com.crud.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.crud.sqlite.adapter.BlogAdapter;
import com.crud.sqlite.adapter.BlogCustomAdapter;
import com.crud.sqlite.database.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    private SQLiteDatabase getdata;
    protected Cursor cursor;
    protected BlogCustomAdapter blogAdapter;
    private List<BlogAdapter> blogList;
    DataHelper dataHelper;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma = this;

        dataHelper = new DataHelper(this);
        getdata = dataHelper.getReadableDatabase();

        //
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        blogList = new ArrayList<>();
        blogAdapter = new BlogCustomAdapter(blogList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(blogAdapter);

        //

        getBlog();

        Button btn = (Button) findViewById(R.id.addData);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,AddBlog.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        blogList.clear();
        getBlog();
        blogAdapter.notifyDataSetChanged();
    }

    public void getBlog(){
        cursor     = getdata.rawQuery("SELECT id,title_blog,desc_blog FROM tb_blog ORDER BY id DESC", null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            BlogAdapter blog = new BlogAdapter(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            blogList.add(blog);



        }
    }
}
