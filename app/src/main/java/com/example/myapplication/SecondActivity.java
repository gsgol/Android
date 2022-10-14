package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity
{
    private static final String string_for_main = "Hello from Second!";

    public static final String string_from_main
            = SecondActivity.class.getCanonicalName() + ".stringFromMain";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.second_activity);
        Button btnOk = findViewById(R.id.activitySlaveBtnOk);
        btnOk.setOnClickListener(view -> {
            Intent dataForMain = new Intent();
            dataForMain.putExtra(MainActivity.string_from_second, string_for_main);
            dataForMain.putExtra(
                    MainActivity.second_name,
                    ((EditText) findViewById(R.id.activitySecondEditTextScndname)).getText().toString()
                            .trim());
            dataForMain.putExtra(
                    MainActivity.first_name,
                    ((EditText) findViewById(R.id.activitySecondEditTextFstname)).getText().toString()
                            .trim());
            dataForMain.putExtra(
                    MainActivity.third_name,
                    ((EditText) findViewById(R.id.activitySecondEditTextTrdname)).getText().toString()
                            .trim());
            dataForMain.putExtra(
                    MainActivity.address_,
                    ((EditText) findViewById(R.id.activitySlaveEditTextAddress)).getText().toString()
                            .trim());
            setResult(Activity.RESULT_OK, dataForMain);
            finish();
        });

        Intent dataFromMain = getIntent();
        ((TextView) findViewById(R.id.activitySecondTextViewStringFromMain))
                .setText(dataFromMain.getStringExtra(string_from_main));
    }
}
