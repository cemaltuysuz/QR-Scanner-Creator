package com.thic.qrreadercreator.UI.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thic.qrreadercreator.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class homeFragment extends Fragment {

    private static boolean mPermissionGranted;
    private AppCompatImageView openListButton;
    private AppCompatButton scanButton;
    public homeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        openListButton = root.findViewById(R.id.homeOpenList);
        scanButton = root.findViewById(R.id.QrScanButton);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        mPermissionGranted = false;
                        requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
                    } else {
                        mPermissionGranted = true;
                    }
                } else {
                    mPermissionGranted = true;
                }

                if (mPermissionGranted ==true){
                    Navigation.findNavController(root).navigate(R.id.home_to_scan);
                }

            }
        });
        openListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.home_to_listhome);
            }
        });

        return root;
    }
}