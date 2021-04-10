package com.thic.qrreadercreator.UI.fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class bottomSheet extends BottomSheetDialogFragment {

    ClipboardManager clipboard ;
    private RoundedImageView roundedImageView;
    private static boolean mPermissionGranted;
    private QRGEncoder encoder;
    private TextView qrValue;
    private EditText description;
    private AppCompatButton save,copy,share;
    private QrViewmodel viewmodel;
    private static Uri uri;
    private static boolean isNotFirst = false;
    private Intent intent;
    private ProgressBar progressBar;

    public bottomSheet() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(QrViewmodel.class);
        intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        //Initialize Variable
        roundedImageView = root.findViewById(R.id.roundedQRBottomSheet);
        qrValue = root.findViewById(R.id.QrValueBottomsheet);
        save = root.findViewById(R.id.bottomSheetSave);
        copy = root.findViewById(R.id.bottomSheetCopy);
        share = root.findViewById(R.id.bottomSheetShare);
        progressBar = root.findViewById(R.id.progressBar);
        description = root.findViewById(R.id.DescriptionBottomsheet);
        progressBar.setVisibility(View.INVISIBLE);

        model incomingData = viewmodel.getPushDataModel();
        if (!incomingData.getDescription().trim().isEmpty()){
            isNotFirst =true;
        }else isNotFirst=false;

        encoder = new QRGEncoder(incomingData.getValue(),null, QRGContents.Type.TEXT,500);
        qrValue.setText(incomingData.getValue());
        roundedImageView.setImageBitmap(encoder.getBitmap());

        if (isNotFirst==true){
            description.setVisibility(View.GONE);
        }else description.setVisibility(View.VISIBLE);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(30,true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        mPermissionGranted = false;
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                    } else {
                        mPermissionGranted = true;
                    }
                } else {
                    mPermissionGranted = true;
                }
                if (mPermissionGranted ==true){
                    progressBar.setProgress(60,true);
                    uri =getImageUri(requireActivity(),encoder.getBitmap());
                    shareQR(intent,uri,incomingData);
                    progressBar.setProgress(99,true);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText("text", incomingData.getValue());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(),"Copied!",Toast.LENGTH_LONG).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (description.getText().toString().trim().isEmpty()){
                        incomingData.setDescription(currentDate());
                        incomingData.setDateTime(" ");
                        viewmodel.insert(incomingData);
                    }
                    else {
                        incomingData.setDescription(description.getText().toString());
                        incomingData.setDateTime(currentDate());
                        viewmodel.insert(incomingData);
                    }
                    Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public void shareQR (Intent shareIntent,Uri uri,model incomingData){
        shareIntent.putExtra(Intent.EXTRA_TEXT, incomingData.getValue());
        shareIntent.putExtra(Intent.EXTRA_STREAM,uri );
        shareIntent.setType("image/*");
        //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share QR Code"));
    }
    public String currentDate(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        return currentDate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isNotFirst){
            QrViewmodel.setSingleSelect(false);
        }
    }
}