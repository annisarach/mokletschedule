package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.adapter.TabFragmentPagerAdapter;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

public class GuruActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

        //set up toolbar
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Guru Schedule");

        Toast.makeText(this, "Selamat Datang Bapak/Ibu "+ SharedPrefered.readString(this, SharedPrefered.guru, ""), Toast.LENGTH_LONG).show();

        //inisialisasi tab dan pager
        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        //set object adapter kedalam ViewPager
        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));

        //Manimpilasi sedikit untuk set TextColor pada Tab
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));


        //set tab ke ViewPager
        tabs.setupWithViewPager(pager);

        //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchview.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return true;
                    }
                }
        );*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(GuruActivity.this, AboutActivity.class));
                break;

            case R.id.action_setting:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Apakah Anda Ingin Keluar?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*SharedPrefered.writeString(GuruActivity.this, SharedPrefered.nama, "");
                                SharedPrefered.writeString(GuruActivity.this, SharedPrefered.kelas, "");
                                SharedPrefered.writeString(GuruActivity.this, SharedPrefered.nis, "");*/
                                SharedPrefered.writeString(GuruActivity.this, SharedPrefered.guru, "");
                                SharedPrefered.writeString(GuruActivity.this, SharedPrefered.kog, "");
                                SharedPrefered.writeBoolean(GuruActivity.this, SharedPrefered.bool,false);
                                startActivity(new Intent(GuruActivity.this, AwalActivity.class));
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                break;
        }
        return true;
    }
}
