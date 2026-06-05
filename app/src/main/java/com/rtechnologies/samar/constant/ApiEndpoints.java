package com.rtechnologies.samar.constant;


import com.rtechnologies.samar.BuildConfig;

public class ApiEndpoints {
    private static final String BASE_URL= BuildConfig.baseUrl+"/api/v1";
//    chat group
    private static final String CHAT=BASE_URL+"/chat";
    public static final String NEW_CONVERSATION=CHAT+"/newConversation";
    public static final String NEW_MESSAGE=CHAT+"/newMessage";

}
