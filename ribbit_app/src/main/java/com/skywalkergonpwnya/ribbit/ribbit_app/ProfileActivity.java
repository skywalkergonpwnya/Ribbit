package com.skywalkergonpwnya.ribbit.ribbit_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

/**
 * Created by gonpwnya on 2014-07-06.
 */
public class ProfileActivity extends Activity {

    protected EditText mTestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mTestText = (EditText) findViewById(R.id.profileTestLabel);

        mTestText.setText("AAAAAAAA.");
    }
}
