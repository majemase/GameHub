package com.example.gamehub.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PerfilPagerAdapter extends FragmentStateAdapter {
    public PerfilPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 1 -> fragment = new MisPublicacionesFragment();
            case 2 -> fragment = new MisLikesFragment();
            case 3 ->  fragment = new AjustesFragment();
            default -> fragment = null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
