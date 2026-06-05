package com.rtechnologies.samar.networking;

public class ApiProvider {
  private static final NetworkingModule networkingModule=new NetworkingModule();

  public static final ChatApiProvider chatApiProvider=new ChatApiProvider(networkingModule);
}
