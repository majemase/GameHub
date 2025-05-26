package com.example.gamehub.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gamehub.view.LoginFragment;
import com.example.gamehub.view.RegistroFragment;

public class LoginPagerAdapter extends FragmentStateAdapter {

    public LoginPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new LoginFragment() : new RegistroFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
