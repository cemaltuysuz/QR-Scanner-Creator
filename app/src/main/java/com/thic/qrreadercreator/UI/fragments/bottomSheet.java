package com.thic.qrreadercreator.UI.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.thic.qrreadercreator.R;

public class bottomSheet extends BottomSheetDialogFragment {

    private RoundedImageView roundedImageView;
    private TextView qrValue;


    public bottomSheet() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        roundedImageView = root.findViewById(R.id.roundedQRBottomSheet);
        qrValue = root.findViewById(R.id.QrValueBottomsheet);

        return root;
    }


}