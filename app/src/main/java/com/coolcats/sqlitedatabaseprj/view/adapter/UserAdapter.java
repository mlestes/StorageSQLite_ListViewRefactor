package com.coolcats.sqlitedatabaseprj.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coolcats.sqlitedatabaseprj.R;
import com.coolcats.sqlitedatabaseprj.databinding.UserItemLayoutBinding;
import com.coolcats.sqlitedatabaseprj.model.User;
import com.coolcats.sqlitedatabaseprj.util.MyHelper;

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
    public User getItem(int position) {
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

        Glide.with(parent)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(MyHelper.getImage(userList.get(position).getPosition()))
                .placeholder(R.drawable.ic_work)
                .into(binding.layoutImage);

        binding.nameText.setText(userList.get(position).getName());
        binding.titleText.setText(MyHelper.fromPosition(userList.get(position).getPosition()));

        binding.getRoot().setOnClickListener(v -> {
            userDelegate.selectUser(userList.get(position));
        });

        return binding.getRoot();
    }

    public void updateList(List<User> userList){
        notifyDataSetChanged();
        this.userList = userList;
    }

}
