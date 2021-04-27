package com.coolcats.sqlitedatabaseprj.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.coolcats.sqlitedatabaseprj.databinding.UserItemLayoutBinding;
import com.coolcats.sqlitedatabaseprj.model.User;
import com.coolcats.sqlitedatabaseprj.util.Position;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private List<User> userList;
    private UserDelegate userDelegate;

    public interface UserDelegate{
        void selectUser(User user);
    }

    public UserAdapter(List<User> userList, UserDelegate userDelegate){

        this.userList = userList;
        this.userDelegate = userDelegate;

    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserItemLayoutBinding binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);

        binding.nameText.setText(userList.get(position).getName());
        binding.titleText.setText(userList.get(position).getPosition().toString());

        binding.getRoot().setOnClickListener(v -> {
            userDelegate.selectUser(userList.get(position));
        });

        return binding.getRoot();
    }
}
