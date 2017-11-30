package utils;

import com.google.gson.Gson;
import messages.Message;

public class MessageConverter {

    public String toGson(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    public Message toObj(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, Message.class);
    }
}
