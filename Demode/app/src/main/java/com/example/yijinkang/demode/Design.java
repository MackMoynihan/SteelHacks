package com.example.yijinkang.demode;


import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Design extends AppCompatActivity implements EditTextDialog.EditTextDialogListener {
    RelativeLayout layout;
    int windowWidth;
    int windowHeight;
    int leftM;
    int topM;
    IntentFilter filter;
    TextView textToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        filter = new IntentFilter();

        layout = (RelativeLayout) findViewById(R.id.design);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        windowWidth = metrics.widthPixels;
        windowHeight = metrics.heightPixels;
        Log.d("size", windowWidth + " x " + windowHeight);

        // for adding more Views
        leftM = windowWidth/2 - 150;
        topM = windowHeight/2 - 100;
        Log.d("coords", leftM + ", " + topM);

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

        layout.setOnDragListener(new View.OnDragListener() {
            float xCoord = 0;
            float yCoord = 0;
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        xCoord = event.getX();
                        yCoord = event.getY();
                        Log.d("coord","x " + xCoord);
                        Log.d("coord","y " + yCoord);
                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    case DragEvent.ACTION_DROP:
                        View view = (View) event.getLocalState();
                        int width = view.getWidth();
                        int height = view.getHeight();
                        int leftMargin = xCoord + width/2 < windowWidth ? (int) xCoord-width/2 : windowWidth - width;
                        int topMargin = yCoord + height/2 < windowHeight ? (int) yCoord-height/2 : windowHeight - height;
                        leftMargin = leftMargin < 0 ? 0 : leftMargin;
                        topMargin = topMargin < 0 ? 0 : topMargin;

                        ((ViewGroup) view.getParent()).removeView(view);
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                        params.leftMargin = leftMargin;
                        params.topMargin = topMargin;
                        layout.addView(view, params);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
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
            Log.d("coords", leftM + ", " + topM);

            switch(newWidget) {
                case -1:
                    break;
                case 0: {
                    Log.d("position", "0 received");
                    TextView text = new TextView(Design.this);
                    text.setText("TextView");
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textToEdit = (TextView) v;
                            DialogFragment newFragment = new EditTextDialog();
                            newFragment.show(getSupportFragmentManager(), "edit_text");
                        }
                    });
                    text.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    Log.d("coords", leftM + ", " + topM);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(text, params);
                    break;
                }
                case 1: {
                    Log.d("position", "1 received");
                    ImageView img = new ImageView(Design.this);
                    img.setImageResource(R.drawable.image);
                    img.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img, params);
                    break;
                }
                case 2: {
                    Log.d("position", "2 received");
                    Button btn = new Button(Design.this);
                    btn.setText("test");
                    btn.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(btn, params);
                    break;
                }
                case 3: {
                    Log.d("position", "3 received");
                    ImageView img3 = new ImageView(Design.this);
                    img3.setImageResource(R.drawable.textfield);
                    img3.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img3, params);
                    break;
                }
                case 4: {
                    Log.d("position", "4 received");
                    ImageView img4 = new ImageView(Design.this);
                    img4.setImageResource(R.drawable.list);
                    img4.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img4, params);
                    break;
                }
                case 5: {
                    Log.d("position", "5 received");
                    ImageView img5 = new ImageView(Design.this);
                    img5.setImageResource(R.drawable.menu);
                    img5.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img5, params);
                    break;
                }
                case 6: {
                    Log.d("position", "6 received");
                    ImageView img6 = new ImageView(Design.this);
                    img6.setImageResource(R.drawable.checkbox);
                    img6.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img6, params);
                    break;
                }
                case 7: {
                    Log.d("position", "7 received");
                    ImageView img7 = new ImageView(Design.this);
                    img7.setImageResource(R.drawable.radiobutton);
                    img7.setOnLongClickListener(listener);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                    params.leftMargin = leftM;
                    params.topMargin = topM;
                    layout.addView(img7, params);
                    break;
                }
                default:
                    break;
                }
        }
    };

    View.OnLongClickListener listener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }
    };

    public void showEditTextDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new EditTextDialog();
        dialog.show(getSupportFragmentManager(), "EditTextDialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, CharSequence text) {
        textToEdit.setText(text);
    }
}
