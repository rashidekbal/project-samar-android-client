package com.rtechnologies.samar.networking;

import com.rtechnologies.samar.networking.chatProvider.ChatApiProvider;

public class ApiProvider {
  private static final NetworkingModule networkingModule=new NetworkingModule();

  public static final ChatApiProvider chatApiProvider=new ChatApiProvider(networkingModule);
}
