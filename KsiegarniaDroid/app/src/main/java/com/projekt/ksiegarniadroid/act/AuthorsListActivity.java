package com.projekt.ksiegarniadroid.act;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.projekt.ksiegarniadroid.R;
import com.projekt.ksiegarniadroid.adapters.AuthorsAdapterView;
import com.projekt.ksiegarniadroid.connectivity.RESTClient;
import com.projekt.ksiegarniadroid.connectivity.RESTClientAdapter;
import com.projekt.ksiegarniadroid.exceptions.RESTClientException;
import com.projekt.ksiegarniadroid.objects.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorsListActivity extends Activity implements Runnable {

    private ArrayList<Author> authors = new ArrayList<>();
    private AuthorsAdapterView adapter;
    private ListView lvAuthors;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_list);
        setControls();
        getAuthors();
        setListViewAdapter();
        setEvents();
    }

    private void setControls() {
        lvAuthors = (ListView) findViewById(R.id.lvAuthors);
    }

    private void setEvents() {

    }

    private void setListViewAdapter() {
        adapter = new AuthorsAdapterView(authors, this);
        lvAuthors.setAdapter(adapter);
    }

    private void getAuthors() {
        Thread _thread = new Thread(this);
        _thread.start();
    }

    @Override
    public void run() {
        try {
            authors = RESTClientAdapter.getAllAuthors();
        } catch (RESTClientException e) {
            e.printStackTrace();
        } finally {
            adapter.notifyDataSetChanged();
            setListViewAdapter();
        }
    }
}