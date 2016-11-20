package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.FragmentAwal;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.AppVar;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuruFragment extends Fragment {

    EditText edguru;
    Button bGuru;
    ProgressDialog pDialog;
    public GuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pDialog = new ProgressDialog(getActivity());
        View view = inflater.inflate(R.layout.fragment_guru, container, false);
        edguru = (EditText) view.findViewById(R.id.editTextGuru);
        bGuru = (Button) view.findViewById(R.id.buttonGG);

        ((AwalActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lgngr();
            }
        });
    }

    private void lgngr() {
        final String edGr = edguru.getText().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_LIK, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                //If we are getting success from server
                if (response.contains(AppVar.LOGIN)) {
                    hideDialog();
                    //adminKR01
                    String [] x = response.split("#");
                    String KoG = x[1];
                    String Guru = x[2];
                    gotoCourseActivity(KoG, Guru);

                }

                else {
                    hideDialog();
                    //Displaying an error message on toast
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_LONG).show();
                    edguru.setText("");
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(getActivity(), "The server unreachable", Toast.LENGTH_LONG).show();
                        edguru.setText("");

                    }
                }){ @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            //Adding parameters to request
            params.put(AppVar.KEY_kog, edGr);
            //params.put(AppVar.KEY_kelas, kelas);

            //returning parameter
            return params;
        }};

        //Adding the string request to the queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void gotoCourseActivity(String koG, String guru) {
        /*Bundle b=new Bundle();
        b.putString("nik",nik);
        b.putString("nama",nama);
        Intent in=new Intent(getApplicationContext(),user.class);*/
        SharedPrefered.writeString(getActivity(), SharedPrefered.kog,koG);
        SharedPrefered.writeString(getActivity(), SharedPrefered.guru,guru);
        SharedPrefered.writeBoolean(getActivity(), SharedPrefered.bool,true);

        /*tv1.setText(SharedPrefered.readString(getActivity(), SharedPrefered.nama, ""));
        tv2.setText(SharedPrefered.readString(getActivity(), SharedPrefered.nis, ""));*/
        //Toast.makeText(this, "Selamat Datang Bapak/Ibu "+(SharedPrefered.readString(getActivity(), SharedPrefered.guru, "")), Toast.LENGTH_LONG).show();
        Intent in=new Intent(getActivity(),MainActivity.class);

        //in.putExtras(b);
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
