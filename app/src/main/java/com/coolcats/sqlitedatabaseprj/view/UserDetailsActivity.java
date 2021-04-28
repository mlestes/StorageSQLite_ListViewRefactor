package com.coolcats.sqlitedatabaseprj.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.coolcats.sqlitedatabaseprj.R;
import com.coolcats.sqlitedatabaseprj.databinding.ActivityUserDetailsBinding;
import com.coolcats.sqlitedatabaseprj.model.User;

public class UserDetailsActivity extends AppCompatActivity {

    private ActivityUserDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User user = (User) getIntent().getSerializableExtra("user");
        if(user != null){
            Glide.with(binding.getRoot())
                    .load(user.getImage())
                    .placeholder(R.drawable.person_work)
                    .into(binding.detailImage);

            binding.detailsIdText.setText(user.getId()+"");
            binding.detailsNameText.setText(user.getName());
            binding.detailsTitleText.setText(user.getPosition().toString());
        }

        binding.backButton.setOnClickListener(view -> {
            finish();
        });
    }
}