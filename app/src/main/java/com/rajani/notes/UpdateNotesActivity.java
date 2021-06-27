package com.rajani.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNotesActivity extends AppCompatActivity {

    EditText title,description;
    Button updateNotes;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        updateNotes=findViewById(R.id.updateNote);

        Intent i =getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id=i.getStringExtra("id");

        updateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString()))
            {
                DatabaseClass sqListdatabase = new DatabaseClass(UpdateNotesActivity.this);
                sqListdatabase.updateNotes(title.getText().toString(),description.getText().toString(),id);

                Intent i=new Intent(UpdateNotesActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
                else {
                    Toast.makeText(UpdateNotesActivity.this,"Both Fields Required",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}