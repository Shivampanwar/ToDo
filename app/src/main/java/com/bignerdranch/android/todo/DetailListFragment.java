package com.bignerdranch.android.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.DTDHandler;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by panwa on 2/25/2017.
 */

public class DetailListFragment extends Fragment{
    private RecyclerView detailrecyclerview;
   private TaskAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        detailrecyclerview = (RecyclerView) view
                .findViewById(R.id.task_recycler_view);
        detailrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DatabaseHandler db = new DatabaseHandler(getActivity());


        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Task task=new Task();
                db.addTask(task);
                task.set_Id(db.getfuck());
                Intent intent= MainActivity.newIntent(getActivity(),task.get_Id());
                startActivity(intent);

                Log.d("abc",String.valueOf(db.getfuck()+ " "+ String.valueOf(task.get_Id())));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void updateUI() {
        DatabaseHandler db=new DatabaseHandler(getContext());
        List<Task> tasks = db.getAllTasks();
        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks);
            detailrecyclerview.setAdapter(mAdapter);
        }else {
            Log.d("bc","inside notify");
            Log.d("bc",String.valueOf(db.getfuck()));

            mAdapter.notifyDataSetChanged();
        }
        //List<Task> contacts = db.getAllTasks();

        for (Task cn : tasks) {
            String log = "Id: "+cn.get_Id()+" ,Name: " + cn.get_Heading() ;
            // Writing Contacts to log
            Log.d("bc: ", log);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
         Log.d("bc","inside onresume");
        //DatabaseHandler db=new DatabaseHandler(getContext());

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_list_fragment, menu);
    }

private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private Task mTask;
    public TextView mTitleTextView;
    public Button button;
    public TaskHolder(View itemView) {
        super(itemView);
        mTitleTextView= (TextView) itemView.findViewById(R.id.tasktextview);
        button= (Button) itemView.findViewById(R.id.button);

    }
    public void bindtask(Task task) {
        mTask = task;
        mTitleTextView.setText(mTask.get_Heading());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("abc","Inside button");
                DatabaseHandler db=new DatabaseHandler(getContext());
                db.deleteTask(mTask);
            }
        });

    }
    @Override
    public void onClick(View v) {
       // Toast.makeText(getActivity(),
              //  mTask.get_Heading() + " clicked!", Toast.LENGTH_SHORT)
                //.show();
        Intent i=MainActivity.newIntent(getActivity(),mTask.get_Id());
        startActivity(i);
    }



}
    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;
        public TaskAdapter(List<Task>tasks) {
            mTasks = tasks;
        }
        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.task_list_row, parent, false);
            return new TaskHolder(view);
        }
        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
          //  holder.mTitleTextView.setText(task.get_Heading());
          holder.bindtask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}

