package com.example.flashcardandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add subjects to the list or use a RecyclerView for a dynamic list
        List<String> subjects = Arrays.asList("Biology", "Physics",
                                                "Chemistry", "Computer","History",
                                                "Political Science","Mathematics",
                                                "Engineering","Sociology");

        ListView listView = findViewById(R.id.subjectListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedSubject = subjects.get(position);
            Intent intent = new Intent(MainActivity.this, SubjectActivity.class);
            intent.putExtra("subject", selectedSubject);
            startActivity(intent);
        });
    }
}
