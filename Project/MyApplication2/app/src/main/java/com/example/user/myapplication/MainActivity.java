package com.example.user.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


        public class MainActivity extends AppCompatActivity{


        FirebaseDatabase database = FirebaseDatabase.getInstance();//получает ссылку на бд
        DatabaseReference myRef = database.getReference("message");
            Button button;

             @Override
                 protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);
                 button=findViewById(R.id.button);
                 button.setOnClickListener(new View.OnClickListener(){
                    @Override
                     public void onClick(View view){
                        myRef.setValue("Hello world");
                    }
                 });

    }
}
