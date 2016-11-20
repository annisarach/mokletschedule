package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.AwalActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.R;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelasaFragment extends Fragment {

    Button pindah;


    public SelasaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selasa, container, false);
        pindah = (Button)view.findViewById(R.id.buttonlink);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
