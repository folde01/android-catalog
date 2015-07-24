package com.example.android.rouxacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String COURSE_TITLE = "courseTitle";
    public static final String COURSE_DESC = "courseDesc";
    public static final String COURSE_IMG_ID = "courseImg";

    public static final int DETAIL_REQUEST_CODE = 1001;

    protected List<Course> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = DataProvider.getData();

//        ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, data);
        ArrayAdapter<Course> courseArrayAdapter = new CourseArrayAdapter(this, 0, data); // finally, use the subclass from below
                                                                                            // 0 is because that val won't be used
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(courseArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = data.get(position);
                displayDetail(course);
            }
        });

    }

    private void displayDetail(Course course) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(COURSE_TITLE, course.getTitle());
        intent.putExtra(COURSE_DESC, course.getDescription());

        int res = getResources().getIdentifier(
                "image_" + course.getCourseNumber(), "drawable", getPackageName()
        );

        intent.putExtra(COURSE_IMG_ID, res);
        startActivityForResult(intent, DETAIL_REQUEST_CODE);

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

    class CourseArrayAdapter extends ArrayAdapter<Course> {

        Context context;
        List<Course> objects;

        public CourseArrayAdapter(Context context, int resource, List<Course> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Course course = objects.get(position); // get Course object from position in list of courses

//            Log.d("MainActivity", "course null: " + (course == null));
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE); // inflate layout file
            View view = inflater.inflate(R.layout.course_item, null);

            TextView tv = (TextView) view.findViewById(R.id.tvTitle);  // pass data (String) to View (TextView)
            Log.d("MainActivity", "tv: " + tv);
            tv.setText(course.getTitle());

            ImageView iv = (ImageView) view.findViewById(R.id.imageCourse);  // pass data (image file) to View (ImageView)
            int res = context.getResources().getIdentifier(
                    "image_" + course.getCourseNumber(), "drawable", context.getPackageName()
            ); // resource ID of image to display
            iv.setImageResource(res);

            return view;
        }
    }
}
