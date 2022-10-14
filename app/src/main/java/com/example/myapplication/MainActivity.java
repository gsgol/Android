package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity
{
    public static final int show_second_activity = 1;

    private static final String string_for_second_activity = "Hello from Main Activity!";

    public static final String string_from_second
            = MainActivity.class.getCanonicalName() + ".string_from_second";
    public static final String second_name = MainActivity.class.getCanonicalName() + ".sndname";
    public static final String first_name = MainActivity.class.getCanonicalName() + ".fstname";
    public static final String third_name= MainActivity.class.getCanonicalName() + ".trdname";
    public static final String address_ = MainActivity.class.getCanonicalName() + ".address";

    private String sndname;
    private String fstname;
    private String trdname;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        Button btnStartSecond = findViewById(R.id.btnStartSecond);
        Button btnShowToast = findViewById(R.id.btnShowToast);
        Button btnStartBrowser = findViewById(R.id.btnStartBrowser);

        btnStartSecond.setOnClickListener(view -> {
            Intent dataForSecond = new Intent(this, SecondActivity.class);
            dataForSecond.putExtra(SecondActivity.string_from_main, string_for_second_activity);
            startActivityForResult(dataForSecond, show_second_activity);
        });

        btnShowToast.setOnClickListener(view -> {
            boolean newLine = false;
            StringBuilder sb = new StringBuilder();
            if(MainActivity.this.sndname != null && MainActivity.this.sndname.length() != 0) {
                newLine = true;
                sb.append(MainActivity.this.sndname);
            }
            if(MainActivity.this.fstname != null && MainActivity.this.fstname.length() != 0) {
                if(newLine) {
                    sb.append('\n');
                }
                newLine = true;
                sb.append(MainActivity.this.fstname);
            }
            if(MainActivity.this.trdname != null && MainActivity.this.trdname.length() != 0) {
                if(newLine) {
                    sb.append('\n');
                }
                sb.append(MainActivity.this.trdname);
            }

            String str;
            if(sb.length() > 0) {
                str = sb.toString();
            } else {
                str = getString(R.string.MsgNotSpecifiedName);
            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        });

        btnStartBrowser.setOnClickListener(view ->
        {
            if(MainActivity.this.address == null || MainActivity.this.address.length() == 0)
            {
                Toast.makeText(this, R.string.MsgNotSpecifiedAddress, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(MainActivity.this.address));
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            if(intent.resolveActivity(getPackageManager()) == null)
            {
                Toast.makeText(this,
                        R.string.MsgIncorrectAddress,
                        Toast.LENGTH_LONG).show();
                return;
            }
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent dataFromSecond) {
        super.onActivityResult(requestCode, resultCode, dataFromSecond);
        if (requestCode == show_second_activity && resultCode == Activity.RESULT_OK) {
            ((TextView) findViewById(R.id.activityMainTextViewStringFromSecond))
                    .setText(dataFromSecond.getStringExtra(string_from_second));
            sndname = dataFromSecond.getStringExtra(second_name);
            fstname = dataFromSecond.getStringExtra(first_name);
            trdname = dataFromSecond.getStringExtra(third_name);
            address = dataFromSecond.getStringExtra(address_);
        }
    }
}


