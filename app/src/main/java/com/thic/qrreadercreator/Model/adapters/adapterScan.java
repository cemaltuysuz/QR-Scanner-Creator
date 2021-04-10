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

public class adapterScan extends RecyclerView.Adapter<adapterScan.MyViewHolder> {

    private QRGEncoder encoder;
    private List<model>modelList = new ArrayList<>();
    public static List<model> selectedListAdapter = new ArrayList<>();
    //ActionMode Check
    public static boolean actionActive;

    public adapterScan() {
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

        encoder = new QRGEncoder(modelList.get(position).getValue(),null, QRGContents.Type.TEXT,500);

            holder.imageView.setImageBitmap(encoder.getBitmap());
            holder.description.setText(modelList.get(position).getDescription());
            holder.value.setText(modelList.get(position).getValue());
            holder.dateTime.setText(modelList.get(position).getDateTime());

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
                    SelectedListCheck(modelList.get(position));
                    QrViewmodel.setScanSelectedList(selectedListAdapter);
                }
                //If actionMode is not active , displays data
                else {
                    QrViewmodel.setPushDataModel(modelList.get(position));
                    QrViewmodel.setSingleSelect(true);
                }
            }
        });
        // adds to the list (Selected List)
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedListCheck(modelList.get(position));
                QrViewmodel.setScanSelectedList(selectedListAdapter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void dataChanged(){
        notifyDataSetChanged();
    }

    public void setQR(List<model>modelList){
        this.modelList = modelList;
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
        for (int i = 0; i< selectedListAdapter.size(); i++){
            if (selectedListAdapter.get(i).getQrID()==model.getQrID()){
               selectedListAdapter.remove(i);
                check =true;
                break;
            }
        }
        if (check==false){
            selectedListAdapter.add(model);
        }
    }
    private void onClickSettings(){

    }


}
