package com.coolcats.sqlitedatabaseprj.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.coolcats.sqlitedatabaseprj.model.User;
import com.coolcats.sqlitedatabaseprj.model.db.UserDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.coolcats.sqlitedatabaseprj.model.db.UserDatabaseHelper.ID_COLUMN;
import static com.coolcats.sqlitedatabaseprj.model.db.UserDatabaseHelper.NAME_COLUMN;
import static com.coolcats.sqlitedatabaseprj.model.db.UserDatabaseHelper.POSITION_COLUMN;

public class MyHelper {

    public static void readDB(UserDatabaseHelper dbHelper, List<User> list) {

        Cursor dbC = dbHelper.getAllUsers();
        dbC.moveToPosition(-1);
        while(dbC.moveToNext()){
            String name = dbC.getString(dbC.getColumnIndex(NAME_COLUMN));
            int id = dbC.getInt(dbC.getColumnIndex(ID_COLUMN));
            String posName = dbC.getString(dbC.getColumnIndex(POSITION_COLUMN));
            User user = new User(name, id, Position.valueOf(posName));
            list.add(user);
        }

        dbC.close();

    }

    public static boolean checkInput(Context context, String string, int minLength){
        if(string.isEmpty()) {
            makeToast(context, "Input must not be empty!");
            return false;
        }
        else if(string.length() < minLength){
            makeToast(context, "Input must be at least " + minLength + " characters!");
            return false;
        }
        return true;
    }

    public static Position getValue(String string){

        Position position;
        if(string.equals("Android Developer"))
            return Position.ANDROID_DEVELOPER;
        else if(string.equals("IOS Developer"))
            return Position.IOS_DEVELOPER;
        else if(string.equals("Manager"))
            return Position.MANAGER;
        else if(string.equals("Instructor"))
            return Position.INSTRUCTOR;
        else return Position.TRAINEE;

    }

    public static String fromPosition(Position position){

        if(position.equals(Position.ANDROID_DEVELOPER))
            return "Android Developer";
        else if(position.equals(Position.IOS_DEVELOPER))
            return "IOS Developer";
        else if(position.equals(Position.MANAGER))
            return "Manager";
        else if(position.equals(Position.INSTRUCTOR))
            return "Instructor";
        else
            return "Trainee";
    }

    private static void makeToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
