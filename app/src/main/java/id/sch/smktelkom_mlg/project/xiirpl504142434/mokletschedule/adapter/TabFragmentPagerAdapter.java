package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.JumatFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.KamisFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.RabuFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.SelasaFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.SeninFragment;

/**
 * Created by Alie on 09/11/2016.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    //nama tab nya
    String[] title = new String[]{
            "Senin", "Selasa", "Rabu","Kamis", "Jumat"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new SeninFragment();
                break;
            case 1:
                fragment = new SelasaFragment();
                break;
            case 2:
                fragment = new RabuFragment();
                break;
            case 3:
                fragment = new KamisFragment();
                break;
            case 4:
                fragment = new JumatFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
