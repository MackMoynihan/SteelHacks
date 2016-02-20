package com.example.yijinkang.demode;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Design extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.design);

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
            int x = intent.getIntExtra("xCoord", -1);
            int y = intent.getIntExtra("yCoord", -1);
            // TODO how to receive the image?

            if (x != -1 && y != -1) {
                // TODO put the image down at the proper coordinates
                Log.d("coord", "received nonneg");
            } else if (x == 0 && y == 0) {
                Log.d("coord", "received zeroes");
            }
        }
    };

}
