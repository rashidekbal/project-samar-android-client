package com.rtechnologies.samar.adapters.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtechnologies.samar.R;
import com.rtechnologies.samar.adapters.Chat.viewHolders.TextReceived;
import com.rtechnologies.samar.adapters.Chat.viewHolders.TextSent;
import com.rtechnologies.samar.constant.MessageType;
import com.rtechnologies.samar.models.MessageModel;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<MessageModel> list;
    private final int TEXT_RECEIVED=1;
    private final int TEXT_SENT=2;


    public MessageAdapter(Context context, ArrayList<MessageModel> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }
    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals(MessageType.TEXT.toString())){
            if (list.get(position).isSent())return TEXT_SENT;
            else return TEXT_RECEIVED;
        }
        else{
            return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case TEXT_RECEIVED:
                v= LayoutInflater.from(context).inflate(R.layout.received_text_card,parent,false);
                return new TextReceived(v);

            case TEXT_SENT:
                v= LayoutInflater.from(context).inflate(R.layout.sent_text_card,parent,false);
                return new TextSent(v);

                default:
                    v= LayoutInflater.from(context).inflate(R.layout.sent_text_card,parent,false);
                    return new TextSent(v);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TextReceived){
            TextReceived textReceived=(TextReceived) holder;
            TextReceived.bind(textReceived,list.get(position));

        }
        else {
            TextSent textSent=(TextSent) holder;
            TextSent.bind(textSent,list.get(position));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
