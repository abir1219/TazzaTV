package com.textifly.tazzatv.ui.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.textifly.tazzatv.MainActivity;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.FragmentHomeBinding;
import com.textifly.tazzatv.ui.Home.Adapter.ViewPagerAdapter;
import com.textifly.tazzatv.ui.Home.AllNews.AllNewsFragment;
import com.textifly.tazzatv.ui.Home.IPL.IplFragment;
import com.textifly.tazzatv.ui.Home.NationalNews.NationalNewsFragment;


public class HomeFragment extends Fragment implements View.OnClickListener{
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        btnClick();
        setViewPager();

        return binding.getRoot();
    }

    private void btnClick() {
        binding.llMenu.setOnClickListener(this);
    }

    private void setViewPager() {
        binding.tabLayout.setupWithViewPager(binding.viewPager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // add your fragments
        adapter.addFrag(new AllNewsFragment(), "Home");
        adapter.addFrag(new IplFragment(), "IPL 2022");
        adapter.addFrag(new NationalNewsFragment(), "India");
        adapter.addFrag(new NationalNewsFragment(), "Photo");
        adapter.addFrag(new NationalNewsFragment(), "Video");
        adapter.addFrag(new NationalNewsFragment(), "Entertainment");

        binding.viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llMenu:
                ((MainActivity)getActivity()).openDrawer();
                break;
        }
    }
}