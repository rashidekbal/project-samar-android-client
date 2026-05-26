package com.rtechnologies.samar.adapters.Chat.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtechnologies.samar.R;
import com.rtechnologies.samar.models.MessageModel;

public class TextReceived extends RecyclerView.ViewHolder {
    TextView message,time;
    public TextReceived(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.text_view);
        time = itemView.findViewById(R.id.time);

    }

    public static void bind(TextReceived holder, MessageModel data){
        holder.message.setText(data.getMessage());

//        TODO:handle time with actual time
        holder.time.setText("12:55");

    }
}
