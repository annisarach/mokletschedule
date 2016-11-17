package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal.PilihanFragment;

public class AwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container,
                            new PilihanFragment(),
                            PilihanFragment.class.getSimpleName()).commit();
        }
    }
}
