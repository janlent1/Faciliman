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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.width;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.os.Environment.*;
import static android.provider.MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
import static com.example.d062654.faciliman.R.attr.height;


public class _2_IncidentPicture extends Fragment implements View.OnClickListener{
    RelativeLayout ll = null;
    FragmentActivity fragact = null;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    public static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 3;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    Button sendbutton = null;
    TextView locdescrp, damagdescrinfo = null;
    private Uri fileUri; // file url to store image/video
    private Uri mCameraTempUri;
    private ImageView imgPreview;
    private Button btnCapturePicture;
    private int CameraPermission=0;
    private int ExStoPermission=0;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    File photoFile = null;
    public String user = null;
    String imageFileName = null;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = ll.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }
    @Override
    public void onAttach(Activity activity) {
        fragact = (FragmentActivity)activity;
        super.onAttach(activity);
    }
    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }
    /*
     * Here we restore the fileUri again
     */
    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }*/
    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage(Intent data) {
        try {

            //Bundle extras = data.getExtras();
            imgPreview.setVisibility(View.VISIBLE);
            int targetW = imgPreview.getWidth();
            int targetH = imgPreview.getHeight();
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mCurrentPhotoPath, options);
            int photoW = options.outWidth;
            int photoH = options.outHeight;
            int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;
            options.inPurgeable = true;
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
            Matrix matrix = new Matrix();

            matrix.postRotate(90);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);

            Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
            imgPreview.setImageBitmap(rotatedBitmap);
            
            //final Bitmap bitmap = (Bitmap) extras.get("data");;
            //System.out.println("***"+ mCameraTempUri);
            //imgPreview.setImageBitmap(bitmap);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage(data);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(fragact.getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(fragact.getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    /*
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(ll.getContext().getPackageManager()) != null) {
            // Create the File where the photo should go

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(ll.getContext(),
                        "com.d062654.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }
        }
        //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        /*ContentValues values = new ContentValues(1);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        mCameraTempUri = getActivity().getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        System.out.println("###"+mCameraTempUri);


        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraTempUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        */
        // start the image capture Intent
        //startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ExStoPermission = 1;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }
            case MY_PERMISSIONS_REQUEST_CAMERA:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CameraPermission =  1;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }


            }
            default:{
                if(ExStoPermission == 1 && CameraPermission == 1){
                    captureImage();
                    return;
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout) inflater.inflate(R.layout.incidentpicture, container, false);
        sendbutton = ((Button)ll.findViewById(R.id.send_button));
        sendbutton.setOnClickListener(this);
        imgPreview = (ImageView) ll.findViewById(R.id.Imageprev);
        btnCapturePicture = (Button) ll.findViewById(R.id.select_pic);
/**
 * Capture image button click event
 * */
        btnCapturePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // The request code used in ActivityCompat.requestPermissions()
                // and returned in the Activity's onRequestPermissionsResult()
                int PERMISSION_ALL = 1;
                String[] PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

                if(!hasPermissions(ll.getContext(), PERMISSIONS)){
                    ActivityCompat.requestPermissions((Activity) ll.getContext(), PERMISSIONS, PERMISSION_ALL);
                }
                    captureImage();

            }


        });
        // Inflate the layout for this fragment
        return ll;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public void uploadPhoto(){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        // MultipartBody.Part is used to send also the actual filename
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", photoFile.getName(), requestFile);
        // adds another part within the multipart request
        String descriptionString = "Sample description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

// executes the request
        Call<ResponseBody> call = Connection.getApiInterface().uploadPicture(user, body, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Do awesome stuff
                    Toast.makeText(fragact.getApplicationContext(),
                            "Gut", Toast.LENGTH_SHORT)
                            .show();
                } else if (response.code() == 401) {
                    // Handle unauthorized
                    Toast.makeText(fragact.getApplicationContext(),
                            "Schlecht?", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Handle other responses
                    Toast.makeText(fragact.getApplicationContext(),
                            "??", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(this.ll.getContext(), "Das ist das 4. Fragment", Toast.LENGTH_SHORT).show();

        if(v.getResources().getResourceName(v.getId()).substring(30).contentEquals("id/send_button")){
            // creates RequestBody instance from file
            uploadPhoto();

            FragmentTransaction transaction = fragact.getSupportFragmentManager().beginTransaction();
            _3_IncidentDescription newFragment = new _3_IncidentDescription();
            newFragment.user = user;
            newFragment.imageFileName= imageFileName;

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();



    }

}
}