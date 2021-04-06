package com.thic.qrreadercreator.UI.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class bottomSheet extends BottomSheetDialogFragment {

    private RoundedImageView roundedImageView;
    private QRGEncoder encoder;
    private TextView qrValue;
    private AppCompatButton save,copy,share;
    private QrViewmodel viewmodel;

    public bottomSheet() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(QrViewmodel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        roundedImageView = root.findViewById(R.id.roundedQRBottomSheet);
        qrValue = root.findViewById(R.id.QrValueBottomsheet);
        save = root.findViewById(R.id.bottomSheetSave);
        copy = root.findViewById(R.id.bottomSheetCopy);
        share = root.findViewById(R.id.bottomSheetShare);

        model incomingData = viewmodel.getPushDataModel();
        if (incomingData.isScan() ==false){
            copy.setVisibility(View.GONE);
        }
        encoder = new QRGEncoder(incomingData.getValue(),null, QRGContents.Type.TEXT,500);
        qrValue.setText(incomingData.getValue());
        roundedImageView.setImageBitmap(encoder.getBitmap());

        return root;
    }
}