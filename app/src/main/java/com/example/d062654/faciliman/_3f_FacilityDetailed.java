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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;

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

        factitle   =(TextView)ll.findViewById(R.id.factitle);
        factitle.setText(incident.getTitle());
        facplace   =(TextView)ll.findViewById(R.id.facplace);
        facplace.setText(incident.getLocation());
        facdetailed_location = (TextView)ll.findViewById(R.id.facdetailed_location);
        facdetailed_location.setText(incident.getExactLocation());
        facdetailed_description = (TextView)ll.findViewById(R.id.facdetailed_description);
        facdetailed_description.setText(incident.getDescription());
        facimage   =(ImageView)ll.findViewById(R.id.facimage);
        facarchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = Connection.getApiInterface().archieveIncident(user, incident.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(fragact.getApplicationContext(),
                                    "Archivierungsvorgang abgeschlossen", Toast.LENGTH_SHORT)
                                    .show();
                            facarchive.setEnabled(false);
                        }
                        else if (response.code() == 401) {
                        // Handle unauthorized
                        Toast.makeText(fragact.getApplicationContext(),
                                "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                                .show();
                        }
                        else {
                        // Handle other responses
                        Toast.makeText(fragact.getApplicationContext(),
                                "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                                .show();
                    }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }});

        Call<ResponseBody> call = Connection.getApiInterface().getFile(user, incident.getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Do awesome stuff
                    Bitmap bmp = null;

                    try {
                        byte[] imgbytes = response.body().bytes(); // = Base64.decode(response.body().bytes(), Base64.DEFAULT);
                        int targetW = facimage.getWidth();
                        int targetH = facimage.getHeight();
                        // bimatp factory
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;

                        bmp = BitmapFactory.decodeByteArray(imgbytes, 0, imgbytes.length, options);

                        int photoW = options.outWidth;
                        int photoH = options.outHeight;
                        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = scaleFactor;
                        options.inPurgeable = true;

                        bmp = BitmapFactory.decodeByteArray(imgbytes, 0, imgbytes.length, options);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ImageView image = (ImageView) ll.findViewById(R.id.facimage);


                    //Bitmap bitmap = BitmapFactory.decodeFile(photoURI.toString(), options);
                    Matrix matrix = new Matrix();

                    matrix.postRotate(90);

                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp,bmp.getWidth(),bmp.getHeight(),true);

                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
                    facimage.setImageBitmap(rotatedBitmap);
                    //image.setImageBitmap(bmp);
                    /*try {
                        InputStream is;
                        is = response.body()..byteStream();
                        FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "test.jpg"));
                        int read = 0;
                        byte[] buffer = new byte[32768];
                        while( (read = is.read(buffer)) > 0) {
                            fos.write(buffer, 0, read);
                        }

                        fos.close();
                        is.close();

                    }
                    catch(Exception e){}
                    /*Uri photoURI = FileProvider.getUriForFile(ll.getContext(),
                            "com.d062654.fileprovider",
                            response.body().);
                    facimage.setVisibility(View.VISIBLE);

*/

                } else if (response.code() == 401) {
                    // Handle unauthorized
                    Toast.makeText(fragact.getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Handle other responses
                    Toast.makeText(fragact.getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });
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