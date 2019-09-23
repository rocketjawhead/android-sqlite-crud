package com.crud.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crud.sqlite.database.DataHelper;

public class AddBlog extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSave;
    EditText edt_title,edt_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);

        dbHelper = new DataHelper(this);
        edt_title = (EditText) findViewById (R.id.edt_title);
        edt_desc = (EditText) findViewById(R.id.edt_desc);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into tb_blog(title_blog,desc_blog) values('" + edt_title.getText().toString() + "','" + edt_desc.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Insert Data", Toast.LENGTH_LONG).show();
                    MainActivity.ma.getBlog();
                    finish();

            }
        });
    }
}
