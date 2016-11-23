package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

public class GuruActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

        Toast.makeText(this, "Selamat Datang Bapak/Ibu "+ SharedPrefered.readString(this, SharedPrefered.guru, ""), Toast.LENGTH_LONG).show();
    }
}
