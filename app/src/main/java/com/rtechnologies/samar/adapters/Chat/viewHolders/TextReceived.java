package com.rtechnologies.samar.adapters.Chat.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtechnologies.samar.R;
import com.rtechnologies.samar.Samar;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;

import io.noties.markwon.Markwon;

public class TextReceived extends RecyclerView.ViewHolder {
    TextView message,time;
    public TextReceived(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.text_view);
        time = itemView.findViewById(R.id.time);

    }

    public static void bind(TextReceived holder, ChatSchema data){
        Markwon markwon=Markwon.create(Samar.getGlobalContext());
        markwon.setMarkdown(holder.message,data.getMessage());

//        TODO:handle time with actual time
        holder.time.setText("12:55");

    }
}
