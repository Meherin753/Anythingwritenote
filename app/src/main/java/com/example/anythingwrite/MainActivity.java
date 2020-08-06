package com.example.anythingwrite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView ttitle, tdes;
    EditText etitle, edes;
    Button bt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ttitle = findViewById(R.id.title1);
        tdes = findViewById(R.id.text);
        etitle = findViewById(R.id.title2);
        edes = findViewById(R.id.entertext);

        bt = findViewById(R.id.add);

        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("Title");
        DatabaseReference d2 = FirebaseDatabase.getInstance().getReference("Description");
        d1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ttitle.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        d2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tdes.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etitle.getText().toString();
                String description = edes.getText().toString();
                databaseReference.child("Title").setValue(title);
                databaseReference.child("Description").setValue(description);


            }
        });

    }
}
