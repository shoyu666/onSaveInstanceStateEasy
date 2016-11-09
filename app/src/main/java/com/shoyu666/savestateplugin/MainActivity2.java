package com.shoyu666.savestateplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by shoyu666 on 2016/11/9.
 */
import com.shoyu666.savestate.SaveStatesAnno;

/**
 * Created by shoyu666 on 2016/11/9.
 */
public class MainActivity2 extends AppCompatActivity {
    @SaveStatesAnno("name")
    String ss;
    @SaveStatesAnno("name2")
    String ss2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
