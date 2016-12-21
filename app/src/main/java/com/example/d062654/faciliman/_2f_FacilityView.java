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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.d062654.faciliman.Adapter.IncidentsAdapter;
import com.example.d062654.faciliman.Connection.Connection;
import com.example.d062654.faciliman.Requests.IncidentRequest;
import com.example.d062654.faciliman.Util.RecyclerItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class _2f_FacilityView extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    String username = null;
    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.facilityview, container, false);


        final RecyclerView recyclerView = (RecyclerView) new RecyclerView(ll.getContext()); //ll.findViewById(R.id.incidents_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(ll.getContext()));

        Call<List<IncidentRequest>> call = Connection.getApiInterface().getIncidents(username);
        call.enqueue(new Callback<List<IncidentRequest>>() {
            @Override
            public void onResponse(Call<List<IncidentRequest>> call, Response<List<IncidentRequest>> response) {
                int statusCode = response.code();
                final List<IncidentRequest> incidents = (List<IncidentRequest>) response.body();
                System.out.println(incidents.get(0));
                IncidentsAdapter adapter = new IncidentsAdapter(incidents,R.layout.list_item_incident, ll.getContext());
                recyclerView.setAdapter(adapter);
                adapter.onDataChanged(incidents);
                ll.addView(recyclerView);
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(ll.getContext(), recyclerView , new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                // do whatever
                                FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
                                _3f_FacilityDetailed newFragment = new _3f_FacilityDetailed();
                                newFragment.incident = incidents.get(position);
                                newFragment.user = username;
                                // Replace whatever is in the fragment_container view with this fragment,
                                // and add the transaction to the back stack so the user can navigate back
                                transaction.replace(R.id.fragment_container, newFragment);
                                transaction.addToBackStack(null);
                                // Commit the transaction
                                transaction.commit();

                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
            }


            @Override
            public void onFailure(Call<List<IncidentRequest>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



        return ll;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



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
        Toast.makeText(this.ll.getContext(), "Das ist das zweite Fragment b", Toast.LENGTH_SHORT).show();

        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/fac_login")){

        }

    }
}