package com.thic.qrreadercreator.UI.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateFragment extends Fragment {

    //UI Components
    AppCompatImageView okeyButton,QrView,backButton;
    private EditText inputValue;
    //Other Components
    private QRGEncoder encoder;
    private String qrinput;
    private Bitmap qrCode;
    private QrViewmodel viewmodel;


    public GenerateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(requireActivity()).get(QrViewmodel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_generate, container, false);

        inputValue = root.findViewById(R.id.generateInput);
        backButton = root.findViewById(R.id.generateBackButton);
        okeyButton = root.findViewById(R.id.okeyButtonGenerate);
        QrView = root.findViewById(R.id.QRCodeView);

        //RealTime Encode
        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                qrinput = s.toString();
                encoder = new QRGEncoder(qrinput,null, QRGContents.Type.TEXT,500);
                qrCode = encoder.getBitmap();
                QrView.setImageBitmap(qrCode);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Backbutton Action
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.generate_to_home);
            }
        });
        //Okey button Action
        okeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputValue.getText().toString().trim().isEmpty()){
                    viewmodel.setPushDataModel(new model(qrinput," "," ",0));
                    Navigation.findNavController(root).navigate(R.id.action_generateFragment_to_bottomSheet);
                }else Toast.makeText(getActivity(),"Is Empty !",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}