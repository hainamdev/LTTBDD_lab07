package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);


//        db.addUser(new User("Nguyễn Hải Nam"));
//        db.addUser(new User("Nguyễn Quý Khả"));
//        db.addUser(new User("Trần Văn Nhân"));

        List<User> userList = db.getAllUsers();
        List<String> listName = new ArrayList<String>();
        for(User u : userList)
            listName.add(u.getName());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listName);
        ListView listView = findViewById(R.id.ListName);
        listView.setAdapter(arrayAdapter);

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtName = findViewById(R.id.txtName);
                String newName = txtName.getText().toString();
                db.addUser(new User(newName));
                userList.add(new User(newName));
                listName.add(newName);
                txtName.setText("");
                listView.setAdapter(arrayAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = listName.get(i);
                TextView txtName = findViewById(R.id.txtName);
                txtName.setText(name);
            }
        });

        Button btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtName = findViewById(R.id.txtName);
                String newName = txtName.getText().toString();
                db.deleteUser(new User(newName));
                listName.remove(newName);
                userList.remove(new User(newName));
                listView.setAdapter(arrayAdapter);
                txtName.setText("");
            }
        });

        Button btnCancel = findViewById(R.id.bntCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtName = findViewById(R.id.txtName);
                txtName.setText("");
            }
        });



    }
}