package com.example.piechartfinal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piechartfinal.Database.DbManager;
import com.example.piechartfinal.Database.SpinnerDao;
import com.example.piechartfinal.MainActivity;
import com.example.piechartfinal.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class DialogMood extends DialogFragment {

    //widgets
    private Spinner spinner;
    private List<com.example.piechartfinal.Database.Spinner> spinnerList;

    private String showMe;
    private int myId;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_mood, null);
        spinner = view.findViewById(R.id.spinner);
        DbManager.setConfig(view.getContext());



        populateSampleData();

        populateSpinList();
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showMe =  spinnerList.get(position).getMOOD();
                myId = spinnerList.get(position).getID();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getDialog().dismiss();
            }
        });

        builder.setView(view)
                .setTitle("Choose mood")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                })

                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), showMe, Toast.LENGTH_SHORT).show();
                        SpinnerDao.updateCount(myId);
                        MainActivity activity = (MainActivity) getActivity();
                        activity.rainfall = activity.GetIntArray(SpinnerDao.loadMoodCounter());
                        activity.monthname = activity.GetStringArray(SpinnerDao.loadMoodName());
                        activity.chart.animateXY(100,100);
                        activity.setupPieChart();
                        activity.doInvalidation();


                    }

                });

        return builder.create();
    }




    private void populateSpinList() {
        spinnerList = new ArrayList<>();
        spinnerList = SpinnerDao.loadAllRecords();

    }

    private BaseAdapter spinnerAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return spinnerList.size();
        }

        @Override
        public Object getItem(int position) {
            return spinnerList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SpinHolder holder;
            View spinView = convertView;

            if (spinView == null) {
                spinView = getLayoutInflater().inflate(R.layout.row_spinner, parent, false);

                holder = new SpinHolder();
                holder.tvSpinMood = spinView.findViewById(R.id.tv_spin_mood);
                spinView.setTag(holder);
            } else {
                holder = (SpinHolder) spinView.getTag();
            }

            com.example.piechartfinal.Database.Spinner spinner = spinnerList.get(position);
            holder.tvSpinMood.setText(String.valueOf(spinner.getMOOD()));

            return spinView;
        }


        class SpinHolder {
            private TextView tvSpinMood;
        }

    };

   public static void getCurrentDate(){
       Calendar calendar = Calendar.getInstance();
       String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
    }

    public static void populateSampleData() {
        if (SpinnerDao.loadAllRecords().size() == 0) {

            com.example.piechartfinal.Database.Spinner spin = new com.example.piechartfinal.Database.Spinner();
            spin.setID(SpinnerDao.maxId());
            spin.setMOOD("Happy");
            spin.setUSED(0);
            spin.setCOUNTER(6);
            spin.setRATING(3);
            SpinnerDao.insertRecord(spin);

            spin = new com.example.piechartfinal.Database.Spinner();
            spin.setID(SpinnerDao.maxId());
            spin.setMOOD("Calm");
            spin.setUSED(0);
            spin.setCOUNTER(2);
            spin.setRATING(1);
            SpinnerDao.insertRecord(spin);

            spin = new com.example.piechartfinal.Database.Spinner();
            spin.setID(SpinnerDao.maxId());
            spin.setMOOD("Stressed");
            spin.setUSED(0);
            spin.setCOUNTER(1);
            spin.setRATING(5);
            SpinnerDao.insertRecord(spin);

            spin = new com.example.piechartfinal.Database.Spinner();
            spin.setID(SpinnerDao.maxId());
            spin.setMOOD("Sad");
            spin.setUSED(0);
            spin.setCOUNTER(0);
            spin.setRATING(2);
            SpinnerDao.insertRecord(spin);


        }
    }
}