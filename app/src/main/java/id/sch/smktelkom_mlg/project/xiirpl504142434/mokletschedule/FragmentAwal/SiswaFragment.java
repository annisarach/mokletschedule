package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.AwalActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.R;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment.SeninFragment;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.AppVar;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiswaFragment extends Fragment {

    private String TAG = SeninFragment.class.getSimpleName();
    EditText nis1, nis2;
    TextView nis3;
    Spinner spkls;
    String gabung = "";
    Button bsiswa;
    TextView tv1, tv2, tv3;
    ProgressDialog pDialog;
    int a, b =167;

    public SiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pDialog = new ProgressDialog(getActivity());
        View view = inflater.inflate(R.layout.fragment_siswa, container, false);
        nis1 = (EditText) view.findViewById(R.id.editTextNIS1);
        nis2 = (EditText) view.findViewById(R.id.editTextNIS2);
        nis3 = (TextView) view.findViewById(R.id.editTextNIS3);
        spkls = (Spinner) view.findViewById(R.id.spinnerKelas);
        bsiswa = (Button) view.findViewById(R.id.buttonssw);

        tv1 = (TextView) view.findViewById(R.id.textView4);
        tv2 = (TextView) view.findViewById(R.id.textViewNIS);
        tv3 = (TextView) view.findViewById(R.id.textViewKelas);


        ((AwalActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        //Getting values from edit texts
        String value= nis1.getText().toString();
        int finalValue =Integer.parseInt(value);
        a = finalValue - 167;
        String ns2 = nis2.getText().toString();
        //String ns3 = nis3.getText().toString();
        gabung = (a+ "/" + ns2 +"-"+ "070");
        Log.e(TAG, "Response from url: "+ gabung);
        //tv1.setText(gabung);
        final String nis = gabung;
        final String kelas = spkls.getSelectedItem().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                //If we are getting success from server
                if (response.contains(AppVar.LOGIN)) {
                    hideDialog();
                    //adminKR01
                    String [] x = response.split("#");
                    String NIS = x[1];
                    String Nama = x[2];
                    String Kelas = x[3];
                    gotoCourseActivity(NIS, Nama, Kelas);

                }

                else {
                    hideDialog();
                    //Displaying an error message on toast
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_LONG).show();
                    nis1.setText("");
                    nis2.setText("");
                    //nis3.setText("");
                    spkls.setSelection(0);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(getActivity(), "The server unreachable", Toast.LENGTH_LONG).show();
                        nis1.setText("");
                        nis2.setText("");
                        //nis3.setText("");
                        spkls.setSelection(0);
                    }
                }){ @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            //Adding parameters to request
            params.put(AppVar.KEY_NIS, nis);
            params.put(AppVar.KEY_kelas, kelas);

            //returning parameter
            return params;
        }};

        //Adding the string request to the queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void gotoCourseActivity(String nis, String nama, String kelas) {
        /*Bundle b=new Bundle();
        b.putString("nik",nik);
        b.putString("nama",nama);
        Intent in=new Intent(getApplicationContext(),user.class);*/
        SharedPrefered.writeString(getActivity(), SharedPrefered.nama,nama);
        SharedPrefered.writeString(getActivity(), SharedPrefered.nis,nis);
        SharedPrefered.writeString(getActivity(), SharedPrefered.kelas,kelas);
        SharedPrefered.writeBoolean(getActivity(), SharedPrefered.bool,true);

        /*tv1.setText(SharedPrefered.readString(getActivity(), SharedPrefered.nama, ""));
        tv2.setText(SharedPrefered.readString(getActivity(), SharedPrefered.nis, ""));
        tv3.setText(SharedPrefered.readString(getActivity(), SharedPrefered.kelas, ""));*/
        Intent in=new Intent(getActivity(),MainActivity.class);

        startActivity(in);
        getActivity().finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }


    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
