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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class _0_startScreen extends Fragment implements View.OnClickListener {
    RelativeLayout ll = null;
    FragmentActivity fragact = null;

    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.startscreen, container, false);

        ((Button)ll.findViewById(R.id.button)).setOnClickListener(this);
        ((Button)ll.findViewById(R.id.facmanbutton)).setOnClickListener(this);

        // Inflate the layout for this fragment
        return (View)ll;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ll.getContext(), "Das ist das erste Fragment", Toast.LENGTH_SHORT).show();
        Toast.makeText(this.ll.getContext(), v.getResources().getResourceName(v.getId()).substring(30), Toast.LENGTH_SHORT).show();
        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/button") ) {
             FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
            _1_IncidentLogin newFragment = new _1_IncidentLogin();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/facmanbutton")){
            // TODO: 11/15/2016 implement buttonclickhandler
            FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
            _1b_FacilityLogin newFragment = new _1b_FacilityLogin();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}