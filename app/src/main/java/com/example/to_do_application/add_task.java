package com.example.to_do_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_task extends AppCompatActivity {

    private EditText TaskName,taskDetails,taskid;
    private Button addButton;
    private TextView taskDisplayTextView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TaskName = findViewById(R.id.taskName);
        taskid = findViewById(R.id.taskId);
        taskDetails = findViewById(R.id.taskDetails);
        addButton = findViewById(R.id.addTask);
        taskDisplayTextView = findViewById(R.id.tasks);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("tasks");

        //to show the user inputed Data
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String taskId = taskid.getText().toString().trim();
                String taskName = TaskName.getText().toString().trim();
                String taskdetails = taskDetails.getText().toString().trim();
                String status = "Pending";
                if(!taskName.isEmpty() && !taskdetails.isEmpty()){
                    displayTask(taskName,taskdetails,taskId,status);
                    taskDisplayTextView.setText("Task: " + taskName + "\nTask Details: " + taskdetails+"\ntask ID:"+taskId+"\nTask Status:"+status);
                    taskDisplayTextView.setVisibility(View.VISIBLE);


                }
            }
        });
    }
    //i will pass the value to displayTask functino to store the value on firebase
    private void displayTask(String taskName, String taskdetails,String taskId,String status) {
        Task task = new Task(taskId,taskName, taskdetails,status);
        // Store the task in Firebase under the generated key
        DatabaseReference taskref = FirebaseDatabase.getInstance().getReference().child("tasks");
        //using push method we can store data into firebase easily
        taskref.push().setValue(task);
        Toast.makeText(add_task.this,"Data Inserted!!!",Toast.LENGTH_SHORT).show();
        // Create an intent to start the main activity
        Intent intent = new Intent(add_task.this, MainActivity.class);
        startActivity(intent);
        // Finish this activity so that the user cannot navigate back to it
        finish();
    }
}