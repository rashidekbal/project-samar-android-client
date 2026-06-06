package com.rtechnologies.samar.networking.chatProvider;

import com.rtechnologies.samar.constant.ApiEndpoints;
import com.rtechnologies.samar.constant.NetworkOperationTags;
import com.rtechnologies.samar.interfaces.ApiResponseInterface;
import com.rtechnologies.samar.networking.NetworkingModule;
import com.rtechnologies.samar.utils.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatApiProvider {
    NetworkingModule nm;
    public ChatApiProvider(NetworkingModule nm){
        this.nm=nm;

    }
    public void newConversation(String message_id, String message, ApiResponseInterface callback) throws JSONException {

        String url= ApiEndpoints.NEW_CONVERSATION;
        NewMessageRequestModel newMessageRequestModel=new NewMessageRequestModel(message_id,message);
        JSONObject body=new JSONObject(new JsonUtil<NewMessageRequestModel>().getJsonString(newMessageRequestModel));
        nm.post(NetworkOperationTags.CHAT_SEND.toString(),url,body,null,callback);

    }
    public void newMessage(String conversation_id,String message_id,String message,ApiResponseInterface callback) throws JSONException {
        String url= ApiEndpoints.NEW_MESSAGE;
        MessageRequestModel messageRequestModel=new MessageRequestModel(conversation_id,message_id,message);
        JSONObject body=new JSONObject(new JsonUtil<MessageRequestModel>().getJsonString(messageRequestModel));
        nm.post(NetworkOperationTags.CHAT_SEND.toString(),url,body,null,callback);
    }

}
