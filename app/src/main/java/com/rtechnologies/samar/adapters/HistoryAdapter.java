package com.rtechnologies.samar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.rtechnologies.samar.R;
import com.rtechnologies.samar.interfaces.ConversationHistoryCardInterface;
import com.rtechnologies.samar.roomdb.schema.ChatGroupSchema;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ChatGroupSchema> list;
    ConversationHistoryCardInterface callbackInterface;
    public int selectedId;
    public HistoryAdapter(Context context,List<ChatGroupSchema> list,ConversationHistoryCardInterface callbackInterface){
        this.context=context;
        this.list=list;
        this.callbackInterface=callbackInterface;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.conversation_history_card,parent,false);
        return new HistoryCard(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderView, int rawPosition) {
         int position=holderView.getAbsoluteAdapterPosition();
         if(holderView instanceof HistoryCard){
             HistoryCard holder=(HistoryCard) holderView;
             if (list.get(position).getId()==selectedId)holder.mainContainer.setCardBackgroundColor(context.getResources().getColor(R.color.surface3,null));
             else holder.mainContainer.setCardBackgroundColor(context.getResources().getColor(R.color.surface2,null));
             holder.title.setText(list.get(position).getTitle());
             holder.mainContainer.setOnClickListener(v-> {
                 callbackInterface.onSelectItem(position);
                 selectedId=list.get(position).getId();

             });
         }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class HistoryCard extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;
        MaterialCardView mainContainer;

        public HistoryCard(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            time=itemView.findViewById(R.id.time_holder);
            mainContainer=itemView.findViewById(R.id.mainContainer);
        }
    }



}
