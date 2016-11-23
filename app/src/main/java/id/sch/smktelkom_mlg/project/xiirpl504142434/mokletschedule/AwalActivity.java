package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal.GuruFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal.PilihanFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

public class AwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        check();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container,
                            new PilihanFragment(),
                            PilihanFragment.class.getSimpleName()).commit();
        }
    }
    private void check()
    {
        if (SharedPrefered.readBoolean(this, SharedPrefered.bool, false) == true)
        {
            if (SharedPrefered.readString(this, SharedPrefered.nama, "") != "")
            {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else if (SharedPrefered.readString(this, SharedPrefered.guru, "") != "")
            {
                startActivity(new Intent(this, GuruActivity.class));
                finish();
            }
        }
        else
        {
            Toast.makeText(this, "Selamat Datang, Silahkan login terlebih dahulu untuk melihat jadwal yang ada inginkan.", Toast.LENGTH_LONG).show();
        }
    }
}
