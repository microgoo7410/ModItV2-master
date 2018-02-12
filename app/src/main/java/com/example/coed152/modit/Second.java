package com.example.coed152.modit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ZoomButton;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by coed152 on 10/1/18.
 */

public class Second extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String IMAGE_DIRECTORY = "/demonuts";

    ZoomButton zoom,zoom1;
    ImageView img;
    String data;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Button btn_share=(Button)findViewById(R.id.btn_share);

        ImageView image = (ImageView) findViewById(R.id.viewImage1);
        Intent intent = getIntent();
        String imagePath = intent.getStringExtra("imagePath");
        File f = new File(imagePath);
        Bundle bundle=this.getIntent().getExtras();
        data =bundle.get("operation").toString();
        intent.putExtra("operation", data);
        intent.putExtra("imagePath", imagePath);
        TextView t3 = (TextView) findViewById(R.id.t3);
        t3.setText(imagePath);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.equals("0")) {
                    Intent intent1 = new Intent(view.getContext(), Third.class);
                    startActivity(intent1);
                    finish();
                }
                else if(data.equals("1"))
                {
                    Intent intent2 = new Intent(view.getContext(), Fourth.class);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        if(f.exists())
        {
            Drawable d = Drawable.createFromPath(imagePath);
            if (data.equals("0")) {
                image.setRotation((float) 90.0);
                image.setImageDrawable(d);

                BitmapDrawable draw = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = draw.getBitmap();
                String root = Environment.getExternalStorageDirectory().toString()+ IMAGE_DIRECTORY;
                File myDir = new File(root );
                myDir.mkdirs();
                String fname = "test1.jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    Bitmap finalBitmap = rotate(bitmap,90);
                    FileOutputStream out = new FileOutputStream(file);
                    finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(data.equals("1"))
            {
                image.setRotation((float) -90.0);
                image.setImageDrawable(d);

                BitmapDrawable draw = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = draw.getBitmap();
                String root = Environment.getExternalStorageDirectory().toString()+ IMAGE_DIRECTORY;
                File myDir = new File(root );
                myDir.mkdirs();
                String fname = "test1.jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    Bitmap finalBitmap = rotate(bitmap,-90);
                    FileOutputStream out = new FileOutputStream(file);
                    finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(data.equals("2"))
            {
                image.setImageDrawable(d);
                zoom = (ZoomButton) findViewById(R.id.zoomButton1);
                zoom1 = (ZoomButton) findViewById(R.id.zoomButton2);

                img = (ImageView) findViewById(R.id.viewImage1);

                zoom.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        float x = img.getScaleX();
                        float y = img.getScaleY();

                        img.setScaleX(x+1);
                        img.setScaleY(y+1);
                    }
                });

                zoom1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        float x = img.getScaleX();
                        float y = img.getScaleY();
                        if (img.getScaleX() > 1 && img.getScaleY() > 1) {
                            img.setScaleX(x - 1);
                            img.setScaleY(y - 1);
                        }
                    }
                });

            }

        }

        //t3.setText(data);


    }

    public static Bitmap rotate(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),source.getHeight(), matrix, false);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}