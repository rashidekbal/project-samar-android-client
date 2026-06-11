package com.rtechnologies.samar.service;

import com.androidnetworking.error.ANError;
import com.rtechnologies.samar.constant.MessageType;
import com.rtechnologies.samar.constant.NetworkOperationTags;
import com.rtechnologies.samar.interfaces.ApiResponseInterface;
import com.rtechnologies.samar.interfaces.ChatServiceCallback;
import com.rtechnologies.samar.networking.ApiProvider;
import com.rtechnologies.samar.networking.chatProvider.ChatApiProvider;
import com.rtechnologies.samar.roomdb.DbHelper;
import com.rtechnologies.samar.service.abstractClasses.ChatBluePrint;
import com.rtechnologies.samar.utils.DateUtil;
import com.rtechnologies.samar.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatService extends ChatBluePrint {
    ChatApiProvider chatApiProvider;
    DbHelper dbHelper;

    public ChatService(ChatApiProvider apiProvider,DbHelper dbHelper){
        this.chatApiProvider=apiProvider;
        this.dbHelper=dbHelper;

    }

    @Override
    public void newConversation(String type,String message, ChatServiceCallback callback) {
        dbHelper.insertChat(true,type,message, DateUtil.getTimeStamp(), id -> {
//                message inserted to db
            try {
                chatApiProvider.newConversation(String.valueOf(id), message, new ApiResponseInterface() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            JSONObject data=response.getJSONObject("data");
                            JSONObject received=data.getJSONObject("received");
                            JSONObject generated=data.getJSONObject("generated");
                            //sent messageId or int id
                            long mid=Long.parseLong(received.getString("message_id"));
                            String conversation_id=generated.getString("conversation_id");
                            String title=generated.getString("generated_title");
                            String message_id=generated.getString("generated_message_id");
                            String response_id=generated.getString("response_id");
                            String response_message=generated.getString("response_message");
                            String time_stamp=generated.getString("time_stamp");
                            //insert new chat group history
                            dbHelper.insertNewGroup(conversation_id,title,time_stamp);
                            //update the send chat metadata
                            dbHelper.updateMessageId(mid,message_id,conversation_id);
                            //insert received response in db
                            dbHelper.insertChat(false, MessageType.TEXT.toString(),conversation_id,response_id,response_message,time_stamp);
                            callback.onNewChatSuccess(conversation_id);
                        } catch (JSONException e) {
                            Logger.log("from chatService, Json exception"+ e);
                            callback.onFailure(e.toString());
                            dbHelper.remove_chatsWithoutParent();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        Logger.log("from chatService, apiError"+error.toString());
                        callback.onFailure(error.toString());
                        dbHelper.remove_chatsWithoutParent();



                    }
                });
            } catch (JSONException e) {
                dbHelper.remove_chatsWithoutParent();
                Logger.log("from chatService, Json exception"+ e);
                callback.onFailure(e.toString());
            }

        });
    }

    @Override
    public void newMessage(String type,String conversation_id,String message,ChatServiceCallback callback) {
        dbHelper.insertChat(true,type, conversation_id, message, DateUtil.getTimeStamp(), id -> {
            try {
                chatApiProvider.newMessage(conversation_id, String.valueOf(id), message, new ApiResponseInterface() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            JSONObject data=response.getJSONObject("data");
                            JSONObject received=data.getJSONObject("received");
                            JSONObject generated=data.getJSONObject("generated");
                            //sent messageId or int id
                            long mid=Long.parseLong(received.getString("message_id"));
                            String conversation_id=generated.getString("conversation_id");
                            String message_id=generated.getString("generated_message_id");
                            String response_id=generated.getString("response_id");
                            String response_message=generated.getString("response_message");
                            String time_stamp=generated.getString("time_stamp");
                            //update the send chat metadata
                            dbHelper.updateMessageId(mid,message_id,conversation_id);
                            //insert received response in db
                            dbHelper.insertChat(false,MessageType.TEXT.toString(),conversation_id,response_id,response_message,time_stamp);
                            callback.onNewChatSuccess(conversation_id);
                        } catch (JSONException e) {
                            Logger.log("from chatService, Json exception"+ e);
                            callback.onFailure(e.toString());
                            dbHelper.remove_chatsWithoutParent();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        dbHelper.remove_chatsWithoutParent();
                        Logger.log("from chatService, apiError"+error.toString());
                        callback.onFailure(error.toString());

                    }
                });
            } catch (JSONException e) {
                Logger.log("from chatService, Json exception"+ e);
                dbHelper.remove_chatsWithoutParent();
                callback.onFailure(e.toString());
            }
        });


    }
    @Override
    public void cancelMessageSending(){
        ApiProvider.helper.cancelRequest(NetworkOperationTags.CHAT_SEND.toString());
        dbHelper.remove_chatsWithoutParent();
    }
}
