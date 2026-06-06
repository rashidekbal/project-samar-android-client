package com.rtechnologies.samar.adapters.Chat.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtechnologies.samar.R;
import com.rtechnologies.samar.roomdb.schema.ChatSchema;
import com.rtechnologies.samar.utils.DateUtil;

public class TextSent extends RecyclerView.ViewHolder {
    TextView message,state_time;
    public TextSent(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.text_view);
        state_time = itemView.findViewById(R.id.state_time);

    }



    public static void bind(TextSent holder, ChatSchema data){
        holder.message.setText(data.getMessage());
        holder.state_time.setText(DateUtil.getMessageTime(data.getTimeStamp()));
    }
}
