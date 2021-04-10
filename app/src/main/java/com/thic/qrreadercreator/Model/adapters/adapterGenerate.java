package com.thic.qrreadercreator.Model.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import com.thic.qrreadercreator.Viewmodel.QrViewmodel;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class adapterGenerate extends RecyclerView.Adapter<adapterGenerate.MyViewHolder> {


    private QRGEncoder encoder;
    private List<model> generateData = new ArrayList<>();
    public static List<model> selectedItemList = new ArrayList<>();
    //ActionMode Check
    public static boolean actionActive;

    public adapterGenerate() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.priv_list_count,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Initialize

        encoder = new QRGEncoder(generateData.get(position).getValue(),null, QRGContents.Type.TEXT,500);

            holder.imageView.setImageBitmap(encoder.getBitmap());
            holder.description.setText(generateData.get(position).getDescription());
            holder.value.setText(generateData.get(position).getValue());
            holder.dateTime.setText(generateData.get(position).getDateTime());

            // CheckBox visibility
            if (actionActive==true){
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.checkBox.setChecked(false);
            }
            else {
                holder.checkBox.setVisibility(View.GONE);
            }

            //Action mode On
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                actionActive =true;
                QrViewmodel.actionModeİsActive.setValue(true);
                notifyDataSetChanged();
                return true;
            }
        });
            //Single select or dataView
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If actionmode is active, adds to the list (Selected list)
                if (actionActive == true){
                    holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    SelectedListCheck(generateData.get(position));
                    QrViewmodel.setGenerateSelectedList(selectedItemList);
                }
                //If actionMode is not active , displays data
                else {
                    QrViewmodel.setPushDataModel(generateData.get(position));
                    QrViewmodel.setSingleSelect(true);
                }
            }
        });
        // adds to the list (Selected List)
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedListCheck(generateData.get(position));
                QrViewmodel.setGenerateSelectedList(selectedItemList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return generateData.size();
    }

    public void dataChanged(){
        notifyDataSetChanged();
    }

    public void setQR(List<model>modelList){
        this.generateData = modelList;
        dataChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
        private TextView description,value,dateTime;
        private RelativeLayout relativeLayout;
        private CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.priv_count_image);
            description = itemView.findViewById(R.id.priv_count_description);
            value = itemView.findViewById(R.id.priv_count_value);
            dateTime = itemView.findViewById(R.id.priv_count_date);
            checkBox = itemView.findViewById(R.id.priv_count_checkBox);
            relativeLayout = itemView.findViewById(R.id.priv_count_layout);
        }
    }
    //This method, checks if an item is inside.
    public void SelectedListCheck(model model){
        Boolean check =false;
        for (int i = 0; i< selectedItemList.size(); i++){
            if (selectedItemList.get(i).getQrID()==model.getQrID()){
               selectedItemList.remove(i);
                check =true;
                break;
            }
        }
        if (check==false){
            selectedItemList.add(model);
        }
    }
}
