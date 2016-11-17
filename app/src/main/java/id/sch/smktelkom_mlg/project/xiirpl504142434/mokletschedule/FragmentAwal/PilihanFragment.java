package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.AwalActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PilihanFragment extends Fragment {
    private Button btnLoginSiswa;
    private Button btnLoginGuru;

    public PilihanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_pilihan, container, false);
        ((AwalActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        btnLoginGuru = (Button) view.findViewById(R.id.buttonGuru);
        btnLoginSiswa = (Button) view.findViewById(R.id.buttonSiswa);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnLoginSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                        replace(R.id.frame_container,
                                new SiswaFragment(),
                                SiswaFragment.class.getSimpleName())
                        .addToBackStack(null).commit();
            }
        });

        btnLoginGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).
                        replace(R.id.frame_container,
                                new GuruFragment(),
                                GuruFragment.class.getSimpleName())
                        .addToBackStack(null).commit();
            }
        });
    }
}
