/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.d062654.faciliman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d062654.faciliman.Connection.ApiInterface;
import com.example.d062654.faciliman.Connection.Connection;
import com.example.d062654.faciliman.Requests.LoginRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;

public class _1_IncidentLogin extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    Button matlogin = null;
    TextView matnr = null;
    TextView matpassword = null;
    ProgressBar progressbar = null;
    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.incidentlogin, container, false);
        progressbar = ((ProgressBar)ll.findViewById(R.id.incidentlogprogressBar));
        progressbar.setVisibility(GONE);
        ((Button)ll.findViewById(R.id.matloginbutton)).setOnClickListener(this);
        matnr = ((TextView)ll.findViewById(R.id.matnr));
        matpassword = (TextView) ll.findViewById(R.id.matpassword);
        // Inflate the layout for this fragment
        return ll;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
       Toast.makeText(this.ll.getContext(), "Das ist das zweite Fragment", Toast.LENGTH_SHORT).show();
        final View view = v;
        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/matloginbutton")){

            progressbar.setVisibility(View.VISIBLE);
            Call<ResponseBody> call = Connection.getApiInterface().getLogin(matnr.getText().toString(), matpassword.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Do awesome stuff
                        Toast.makeText(ll.getContext(), view.getResources().getResourceName(view.getId()), Toast.LENGTH_SHORT).show();
                        FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
                        _3_IncidentDescription newFragment = new _3_IncidentDescription();
                        newFragment.user = matnr.getText().toString();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack so the user can navigate back
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);
                        progressbar.setVisibility(GONE);

                        // Commit the transaction
                        transaction.commit();
                    } else if (response.code() == 401) {
                        // Handle unauthorized
                        progressbar.setVisibility(GONE);

                    } else {
                        // Handle other responses
                        progressbar.setVisibility(GONE);

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody>call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                    progressbar.setVisibility(GONE);

                }
            });
            //this.checkLoginData((String)matnr.getText(), (String)matpassword.getText());

        }


    }
    /*
    private boolean checkLoginData(String matnr, String matpassword){
        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<LoginRequest> call = apiService.);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                List<Incident> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }*/
}