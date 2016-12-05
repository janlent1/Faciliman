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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class _3_IncidentDescription extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    Button sendbutton = null;
    TextView locdescrp, damagdescrinfo = null;

    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.incidentdescription, container, false);
        sendbutton = ((Button)ll.findViewById(R.id.descr_finished_button));
        sendbutton.setOnClickListener(this);
        locdescrp = ((TextView)ll.findViewById(R.id.detailed_location_description));
        locdescrp.setOnClickListener(this);
        damagdescrinfo = ((TextView)ll.findViewById(R.id.damage_description_information));
        damagdescrinfo.setOnClickListener(this);
        Spinner spinner = ((Spinner)ll.findViewById(R.id.facilities_spinner));
        //spinner.setOnClickListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ll.getContext(), R.array.facilities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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
        Toast.makeText(this.ll.getContext(), "Das ist das 4. Fragment", Toast.LENGTH_SHORT).show();

        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/descr_finished_button")){
            Toast.makeText(this.ll.getContext(), v.getResources().getResourceName(v.getId()), Toast.LENGTH_SHORT).show();
            Toast.makeText(this.ll.getContext(), locdescrp.getText()    , Toast.LENGTH_SHORT).show();

            FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
            _4_FinalScreen newFragment = new _4_FinalScreen();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/detailed_location_description")){
            // TODO: 11/15/2016
        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/damage_description_information")){
            // TODO: 11/15/2016
        }
        else if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/facilities_spinner")){
            // TODO: 11/15/2016
        }


    }
}