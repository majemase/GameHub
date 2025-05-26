package com.example.gamehub.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamehub.R;
import com.example.gamehub.Utils.PerfilPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TextView nickname_tv;
    private ImageView avatar_iv;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        nickname_tv = view.findViewById(R.id.nickname_tv);
        avatar_iv = view.findViewById(R.id.avatar_iv);

        SharedPreferences preferences = requireActivity().getSharedPreferences("usuario", getContext().MODE_PRIVATE);
        nickname_tv.setText(preferences.getString("nickname", "nombre de usuario"));
        // avatar_iv.setImageResource(preferences.getString("avatar", "avatar"));

        PerfilPagerAdapter adapter = new PerfilPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Mis publicaciones");
                    break;
                case 1:
                    tab.setText("Mis likes");
                    break;
                case 2:
                    tab.setText("Ajustes");
                    break;
                default:
                    tab.setText("Mis publicaciones");
                    break;
            }
            ;
        }
        ).attach();
    }
}