package com.example.gamehub.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gamehub.view.AjustesFragment;
import com.example.gamehub.view.MisLikesFragment;
import com.example.gamehub.view.MisPublicacionesFragment;

public class PerfilPagerAdapter extends FragmentStateAdapter {
    public PerfilPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new MisPublicacionesFragment();
            case 1: return  new MisLikesFragment();
            case 2:  return  new AjustesFragment();
            default: return  new MisPublicacionesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
