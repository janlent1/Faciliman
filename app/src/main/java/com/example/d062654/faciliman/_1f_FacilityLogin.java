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

import com.example.d062654.faciliman.Connection.Connection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;

public class _1f_FacilityLogin extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    TextView facpassword = null, facusername = null;
    ProgressBar progressbar = null;

    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.facilitylogin, container, false);
        ((Button)ll.findViewById(R.id.fac_login)).setOnClickListener(this);
        facpassword = ((TextView)ll.findViewById(R.id.fac_password));
        facusername = ((TextView)ll.findViewById(R.id.fac_username));
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
        final View view = v;
        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/fac_login")){
            Call<ResponseBody> call = Connection.getApiInterface().getLogin(facusername.getText().toString(), facpassword.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Do awesome stuff
                        Toast.makeText(ll.getContext(), view.getResources().getResourceName(view.getId()), Toast.LENGTH_SHORT).show();
                        FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
                        _2f_FacilityView newFragment = new _2f_FacilityView();
                        newFragment.username = facusername.getText().toString();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack so the user can navigate back
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();
                    } else if (response.code() == 401) {
                        // Handle unauthorized

                    } else {
                        // Handle other responses

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody>call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());

                }
            });


            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            //transaction.replace(R.id.fragment_container, newFragment);
            //transaction.addToBackStack(null);

            // Commit the transaction
            //transaction.commit();
        }

    }
}