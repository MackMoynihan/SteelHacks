package com.example.yijinkang.demode;


import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Design extends AppCompatActivity {
    RelativeLayout layout;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        filter = new IntentFilter();

        layout = (RelativeLayout) findViewById(R.id.design);
        Intent intent = getIntent();
        String projName = intent.getStringExtra("Project Name");

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Design.this , Pop.class) );
            }
        });
//        TextView projNameView = new TextView(this);
//        projNameView.setTextSize(40); // TODO look up TextView documentation to make it look better
//        projNameView.setText(projName);
//        layout.addView(projNameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        filter.addAction("com.example.yijinkang.demode.addWidget");
        registerReceiver(rec, filter);
        //the first parameter is the name of the inner class we created.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design, menu);
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

    BroadcastReceiver rec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("broadcast", "received");
            int newWidget = intent.getIntExtra("newWidget", -1);
            switch(newWidget) {
                case -1:
                    break;
                case 0: {
                    Log.d("position", "0 received");
                    TextView text = new TextView(Design.this);
                    text.setText("TextView");
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 400);
                    params.leftMargin = 500;
                    params.topMargin = 600;
                    layout.addView(text, params);
                    break;
                }
                case 1: {
                    Log.d("position", "1 received");
                    ImageView img = new ImageView(Design.this);
                    img.setImageResource(R.drawable.sample_0);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 400);
                    params.leftMargin = 500;
                    params.topMargin = 600;
                    layout.addView(img, params);
                    break;
                }
                case 2: {
                    Log.d("position", "2 received");
                    Button btn = new Button(Design.this);
                    btn.setText("test");
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 400);
                    params.leftMargin = 500;
                    params.topMargin = 600;
                    layout.addView(btn, params);
                    break;
                }
                default:
                    break;
            }
        }
    };

}
