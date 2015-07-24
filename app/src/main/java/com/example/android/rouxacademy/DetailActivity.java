package com.example.android.rouxacademy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    protected String courseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        courseTitle = getIntent().getStringExtra(MainActivity.COURSE_TITLE);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(courseTitle);

        String courseDesc = getIntent().getStringExtra(MainActivity.COURSE_DESC);
        TextView tvDesc = (TextView) findViewById(R.id.tvDescription);
        tvDesc.setText(courseDesc);

        int courseImg = getIntent().getIntExtra(MainActivity.COURSE_IMG_ID, 0);
        ImageView iv = (ImageView) findViewById(R.id.imageCourse);
        iv.setImageResource(courseImg);

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

    public void registerClickHandler(View view) {

        getIntent().putExtra("resultMessage", "You're registered for " + courseTitle);
        setResult(RESULT_OK, getIntent());
        finish();

    }
}
