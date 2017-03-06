package com.bignerdranch.android.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by panwa on 2/25/2017.
 */

public class DetailFragment extends Fragment {

    private static final String Detail_id = "crime_id";
    public static  Task task;
    String str1,str2;
    private EditText mHeading,mDetail;
    private Button b;
    public static DetailFragment newInstance(int Id) {
        Bundle args = new Bundle();
        args.putInt(Detail_id, Id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //int id=getActivity().getIntent().getIntExtra(MainActivity.EXTRA_TASK_ID,0);
       //task=db.getTask(id);
        DatabaseHandler db=new DatabaseHandler(getActivity());
         task =db.getTask(getArguments().getInt(Detail_id));
      //  int i=db.getfuck();
        //Log.d("bcd",String.valueOf(i));
    }
    @Override
    public void onPause() {
        super.onPause();

        task.set_Heading(mHeading.getText().toString());
        task.set_Detail(mDetail.getText().toString());
        task.set_Id(getArguments().getInt(Detail_id));
        DatabaseHandler db=new DatabaseHandler(getActivity());
        Log.d("Stop",task.get_Heading()+"omn");
        int k=db.updateTask(task);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
Log.d("stop","Inside on create view");

        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        mHeading= (EditText) v.findViewById(R.id.heading);
        mDetail= (EditText) v.findViewById(R.id.details);

        mHeading.setText(task.get_Heading());
        mDetail.setText(task.get_Detail());


       //final DatabaseHandler db=new DatabaseHandler(getActivity());

        mHeading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.set_Heading(s.toString());

            }
        });
        mDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                task.set_Detail(s.toString());
            }
        });




        //int j=db.updateTask(task);
        return v;
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("Stop","Inside on stop");
    }
}
