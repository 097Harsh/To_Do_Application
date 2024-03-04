package com.example.to_do_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_task);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,add_task.class);
                startActivity(intent); // This line starts the add_task activity
            }
        });

        tableLayout = findViewById(R.id.data_task);
        // Reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //data node in database
        database.getReference("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Assuming your data structure has "name" and "value" fields
                    String id = Objects.requireNonNull(snapshot.child("id").getValue()).toString();
                    String name = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                    String details = Objects.requireNonNull(snapshot.child("details").getValue()).toString();
                    String status = Objects.requireNonNull(snapshot.child("status").getValue()).toString();
                    // Create a new row and add it to the table
                    addRowToTable(id,name, details,status);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void addRowToTable(String id,String name, String details,String status) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(layoutParams);

        TextView textView1 = new TextView(this);
        textView1.setText(id);
        textView1.setGravity(Gravity.CENTER);
        row.addView(textView1);

        TextView textView2 = new TextView(this);
        textView2.setText(name);
        textView2.setGravity(Gravity.CENTER);
        row.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText(details);
        textView3.setGravity(Gravity.CENTER);
        row.addView(textView3);


        tableLayout.addView(row);
    }
}