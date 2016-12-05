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

public class _2_IncidentSelection extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.incidentselection, container, false);
        ((Button)ll.findViewById(R.id.damagebutton)).setOnClickListener(this);
        ((Button)ll.findViewById(R.id.othersbutton)).setOnClickListener(this);
        ((Button)ll.findViewById(R.id.pollutionbutton)).setOnClickListener(this);
        ((Button)ll.findViewById(R.id.missingequipmentbutton)).setOnClickListener(this);
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
        Toast.makeText(this.ll.getContext(), "Das ist das dritte Fragment", Toast.LENGTH_SHORT).show();
        Toast.makeText(this.ll.getContext(), v.getResources().getResourceName(v.getId()), Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
        _3_IncidentDescription newFragment = new _3_IncidentDescription();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        /*if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/damagebutton")){
            Toast.makeText(this.ll.getContext(), v.getResources().getResourceName(v.getId()), Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
            _3_IncidentDescription newFragment = new _3_IncidentDescription();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/othersbutton")){
            // TODO: 11/15/2016

        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/pollutionbutton")){
            // TODO: 11/15/2016  
        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/missingequipmentbutton")){
            // TODO: 11/15/2016
        }*/
    }
}