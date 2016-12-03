package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.R;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.HttpHandler;
import id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model.SharedPrefered;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeninFragment extends Fragment {

    private String TAG = SeninFragment.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private String url = "http://tsschedule.16mb.com/jadwal.php?hari=senin&&kelas=";
    private String url1 = "http://tsschedule.16mb.com/jdlguru.php?hari=senin&&kode_guru=";

    ArrayList<HashMap<String, String>> contactList;


    public SeninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_senin, container, false);
        contactList = new ArrayList<>();

        lv = (ListView) view.findViewById(R.id.list);
        if (SharedPrefered.readString(getActivity(), SharedPrefered.nama, "") != "")
        {
            new GetContacts().execute();
        }
        else if (SharedPrefered.readString(getActivity(), SharedPrefered.guru, "") != "")
        {
            new GetContacts1().execute();
        }


        return view;
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            Log.e(TAG, "Response from url: " + url+SharedPrefered.readString(getActivity(), SharedPrefered.kelas, ""));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url+SharedPrefered.readString(getActivity(), SharedPrefered.kelas, ""));

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String mapel = c.getString("Mapel");
                        String guru = c.getString("Guru");
                        String jam = c.getString("Jam");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("Mapel", mapel);
                        contact.put("Guru", guru);
                        contact.put("Jam", jam);


                        // adding contact to contact list
                        contactList.add(contact);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(
                getActivity(), contactList,
                R.layout.list_item, new String[]{"Mapel", "Guru",
                "Jam"}, new int[]{R.id.textViewPelajaran,
                R.id.textViewGuru, R.id.textViewJam});

        lv.setAdapter(adapter);
    }

}

    private class GetContacts1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            Log.e(TAG, "isi from url: " + url1+SharedPrefered.readString(getActivity(), SharedPrefered.kog, ""));
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url1+SharedPrefered.readString(getActivity(), SharedPrefered.kog, ""));

            Log.e(TAG, "isi from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String kelas = c.getString("Kelas");
                        String mapel = c.getString("Mapel");
                        //String guru = c.getString("Guru");
                        String jam = c.getString("Jam");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("Kelas", kelas);
                        contact.put("Mapel", mapel);
                        //contact.put("", guru);
                        contact.put("Jam", jam);

                        // adding contact to contact list
                        contactList.add(contact);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), contactList,
                    R.layout.list_item, new String[]{"Kelas","Mapel",
                    "Jam"}, new int[]{R.id.textViewPelajaran,
                    R.id.textViewGuru, R.id.textViewJam});

            lv.setAdapter(adapter);
        }

    }
}
