package com.coolcats.sqlitedatabaseprj.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.coolcats.sqlitedatabaseprj.R;
import com.coolcats.sqlitedatabaseprj.databinding.ActivityMainBinding;
import com.coolcats.sqlitedatabaseprj.model.User;
import com.coolcats.sqlitedatabaseprj.model.db.UserDatabaseHelper;
import com.coolcats.sqlitedatabaseprj.util.MyHelper;
import com.coolcats.sqlitedatabaseprj.util.Position;
import com.coolcats.sqlitedatabaseprj.view.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.coolcats.sqlitedatabaseprj.util.Logger.logMessage;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SharedPreferences sharedPreferences;
    private UserDatabaseHelper dbHelper;
    private ActivityMainBinding binding;
    private List<User> userList;
    private List<String> options = new ArrayList<String>(Arrays.asList("ANDROID_DEVELOPER", "IOS_DEVELOPER", "MANAGER"));
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<String> adapter=  new ArrayAdapter<String>(this, R.layout.spinner_item_layout,R.id.item_text, options);

        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(this);
        dbHelper = new UserDatabaseHelper(this);
        userList = new ArrayList<>();
        UserAdapter userAdapter = new UserAdapter(userList);
        binding.userListview.setAdapter(userAdapter);

        MyHelper.readDB(dbHelper, userList);
        displayUsers(userList);

        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("FIRST_TIME", true))
            showWelcomeDialog();

        binding.addUserButton.setOnClickListener(view -> {

            if(MyHelper.checkInput(this, binding.usernameEdittext.getText().toString().trim(), 4)) {
                User newUser = new User(binding.usernameEdittext.getText().toString().trim(), Position.valueOf(options.get(id)));
                dbHelper.insertUser(newUser);
                MyHelper.readDB(dbHelper, userList);
                binding.usernameEdittext.setText("");
                displayUsers(userList);
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displayUsers(List<User> users) {
        logMessage("Current users");
        for(int i = 0; i < users.size(); i++)
            logMessage(users.get(i).toString());


    }

    private void showWelcomeDialog() {

        sharedPreferences.edit().putBoolean("FIRST_TIME", false).apply();
        new AlertDialog
                .Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                .setMessage("Welcome to the application")
                /*.setPositiveButton("+ve", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(this, "Positive ")
                    }
                })*/.setNeutralButton("Thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })/*.setNegativeButton("-ve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }
        )*/.create().show();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        toastMessage(options.get(i) + ", "+l);
        id = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        toastMessage("Please make a selection!");
    }
}








