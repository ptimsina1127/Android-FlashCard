package com.example.flashcardandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    private List<Flashcard> flashcards;
    private ArrayAdapter<Flashcard> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        // Get the selected subject from the intent
        String selectedSubject = getIntent().getStringExtra("subject");
        setTitle(selectedSubject);

        // Initialize flashcards and the adapter
        flashcards = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flashcards);

        // Set up the flashcardListView
        ListView flashcardListView = findViewById(R.id.flashcardListView);
        flashcardListView.setAdapter(adapter);

        // Load and display flashcards for the selected subject (We can fetch this from a database or any other source)

        // Set up the "Add Flashcard" button click
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a dialog to capture question and answer input
                showAddFlashcardDialog();
            }
        });
    }

    // Method to handle the "Add Flashcard" button click
    private void showAddFlashcardDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_flashcard, null);

        final EditText questionEditText = dialogView.findViewById(R.id.questionEditText);
        final EditText answerEditText = dialogView.findViewById(R.id.answerEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Add Flashcard")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the save button click
                        String question = questionEditText.getText().toString();
                        String answer = answerEditText.getText().toString();
                        addFlashcard(new Flashcard(question, answer));
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    // Method to add a flashcard to the list and update the adapter
    private void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
        adapter.notifyDataSetChanged();
    }
}