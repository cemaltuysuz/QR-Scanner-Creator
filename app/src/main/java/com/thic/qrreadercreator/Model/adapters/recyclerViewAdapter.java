package com.thic.qrreadercreator.Model.adapters;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.thic.qrreadercreator.Model.model;
import com.thic.qrreadercreator.R;
import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    private QRGEncoder encoder;
    private List<model>modelList = new ArrayList<>();
    private boolean isScan;

    public recyclerViewAdapter(boolean isScan) {
        this.isScan = isScan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.priv_list_count,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        model currentmodel = modelList.get(position);
        encoder = new QRGEncoder(modelList.get(position).getValue(),null, QRGContents.Type.TEXT,500);

        if (currentmodel.isScan()==isScan) {
            holder.imageView.setImageBitmap(encoder.getBitmap());
            holder.description.setText("deneme : " + position);
            holder.value.setText(currentmodel.getValue());
            holder.dateTime.setText(" ");
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setQR(List<model>modelList){
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
        private TextView description,value,dateTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.priv_count_image);
            description = itemView.findViewById(R.id.priv_count_description);
            value = itemView.findViewById(R.id.priv_count_value);
            dateTime = itemView.findViewById(R.id.priv_count_date);
        }
    }
}
