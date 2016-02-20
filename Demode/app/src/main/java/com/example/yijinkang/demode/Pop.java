package com.example.yijinkang.demode;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mackmoynihan on 2/20/16.
 */
public class Pop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.6));

        // Find GridView
        GridView gridview = (GridView) findViewById(R.id.objectsGrid);

        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);



                Intent intent = exitPopup();
                startActivity(intent);


//                onStop();

                return true;
            }
        });
    }

//    OnDragListener dragListener = new OnDragListener() {
//        @Override
//        public boolean onDrag(View v, DragEvent event) {
//
//            return false;
//        }
//    };

    public Intent exitPopup() {
        return new Intent(this, Design.class);
    }

//    public boolean onDragEvent(DragEvent event) {
//
//        // TODO exit the popup and do stuff with the dragged thing
//        Intent intent = new Intent(this, Design.class);
//        intent.putExtra("Project Name", event);
//        startActivity(intent);
//        onStop();
//        return true;
//    }
}
