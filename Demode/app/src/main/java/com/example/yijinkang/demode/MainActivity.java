package com.example.yijinkang.demode;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    public final static String PROJ_NAME_EXTRA = "com.example.yijinkang.demode.PROJ_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FloatingActionButton imageButton = (FloatingActionButton)findViewById(R.id.imageButton);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ScrollView scrollView = new
//            }
//        });
//        FloatingActionButton imageButton4 = (FloatingActionButton)findViewById(R.id.imageButton4);
//        imageButton4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        FloatingActionButton imageButton3 = (FloatingActionButton)findViewById(R.id.imageButton3);
//        imageButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        FloatingActionButton imageButton2 = (FloatingActionButton)findViewById(R.id.imageButton2);
//        imageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startNewProject(View view) {
        Intent intent = new Intent(this, Design.class);
        EditText projName = (EditText) findViewById(R.id.projName);
        String newProjName = projName.getText().toString();
        intent.putExtra(PROJ_NAME_EXTRA, newProjName);
        startActivity(intent);
    }

    public void openSavedProject(View view) {
        // TODO
    }
}
