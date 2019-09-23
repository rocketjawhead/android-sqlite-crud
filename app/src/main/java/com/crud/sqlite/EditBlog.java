package com.crud.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.sqlite.database.DataHelper;

public class EditBlog extends AppCompatActivity {

    DataHelper dataHelper;
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText id_blog,edt_title,edt_desc;
    Button updateData,deleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_blog);

        //detail data
        dataHelper = new DataHelper(this);
        id_blog = (EditText) findViewById(R.id.id_blog);
        Intent mIntent = getIntent();
        id_blog.setText(mIntent.getStringExtra("id"));

        SQLiteDatabase db = dataHelper.getReadableDatabase();
        Bundle bundle = getIntent().getExtras();
        cursor = db.rawQuery("SELECT id," +
                "title_blog," +
                "desc_blog FROM tb_blog " +
                "WHERE id = '" +
                mIntent.getStringExtra("id") + "'",null);
        cursor.moveToFirst();

        id_blog = (EditText)findViewById(R.id.id_blog);
        id_blog.setText(cursor.getString(0).toString());

        edt_title = (EditText)findViewById(R.id.edt_title);
        edt_title.setText(cursor.getString(1).toString());

        edt_desc = (EditText)findViewById(R.id.edt_desc);
        edt_desc.setText(cursor.getString(2).toString());

        //update data
        dbHelper = new DataHelper(this);
        updateData = (Button) findViewById(R.id.updateData);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update tb_blog set title_blog='"+
                            edt_title.getText().toString() +"', desc_blog='" +
                            edt_desc.getText().toString() +"' where id='" +
                            id_blog.getText().toString()+"'");
                    Toast.makeText(getApplicationContext(), "Update Data", Toast.LENGTH_LONG).show();
                    MainActivity.ma.getBlog();
                    finish();
            }
        });

        //delete data
        deleteData = (Button) findViewById(R.id.deleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM tb_blog WHERE id ='"+id_blog.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Delete Data", Toast.LENGTH_LONG).show();
                MainActivity.ma.getBlog();
                finish();
            }
        });
    }
}
