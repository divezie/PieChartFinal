package com.example.piechartfinal;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.piechartfinal.Database.DbManager;
import com.example.piechartfinal.Database.SpinnerDao;
import com.example.piechartfinal.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    int rainfall [];
    String monthname[] ;
    PieChart chart;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.setConfig(this);

        chart = findViewById(R.id.pie_chart);
        chart.setDrawHoleEnabled(true);
        chart.setDrawRoundedSlices(true);
        chart.setUsePercentValues(true);
        com.example.piechartfinal.DialogMood.populateSampleData();
        rainfall = GetIntArray(SpinnerDao.loadMoodCounter());
        monthname = GetStringArray(SpinnerDao.loadMoodName());
        setupPieChart();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });



    }

    private void openDialog() {
        DialogMood moodDialog = new DialogMood();
        moodDialog.show(getSupportFragmentManager(),"My Mood Dialog");
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



    public void doInvalidation()
    {
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    public void setupPieChart(){
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i<rainfall.length;i++)
        {
            rainfall[0]= rainfall[0];
            pieEntries.add(new PieEntry(rainfall[i],monthname[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Your mood choices:");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data = new PieData(dataSet);


        chart.animateXY(1000,1000);
        chart.setData(data);
        chart.notifyDataSetChanged();
        chart.invalidate();



    }


    // Function to convert ArrayList<String> to String[]
    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    // Function to convert ArrayList<int> to int[]
    public static int[] GetIntArray(ArrayList<Integer> arr)
    {

        // declaration and initialise String Array
        int str[] = new int[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
