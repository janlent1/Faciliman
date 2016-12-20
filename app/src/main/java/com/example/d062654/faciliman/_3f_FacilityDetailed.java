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
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d062654.faciliman.Connection.Connection;
import com.example.d062654.faciliman.Requests.IncidentRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class _3f_FacilityDetailed extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    String user = null;
    IncidentRequest incident = null;
    Button    facarchive = null;
    TextView    facplace = null;
    TextView    facdetailed_location = null;
    TextView    facdetailed_description = null;
    TextView    factitle = null;
    ImageView    facimage = null;

    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.facilitydetailed, container, false);
        facarchive =(Button)ll.findViewById(R.id.facarchive);

        //factitle   =(TextView)ll.findViewById(R.id.factitle);
        //factitle.setText(incident.);
        facplace   =(TextView)ll.findViewById(R.id.facplace);
        facplace.setText(incident.getLocation());
        facdetailed_location = (TextView)ll.findViewById(R.id.facdetailed_location);
        facdetailed_location.setText(incident.getExactLocation());
        facdetailed_description = (TextView)ll.findViewById(R.id.facdetailed_description);
        facdetailed_description.setText(incident.getDescription());
        //facimage   =(ImageView)ll.findViewById(R.id.facimage);

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




    }
}