package lan.qxc.lightserver.netty.sender.message;

public interface MessageType {

    //好友消息  关注、取关
    int FRIEND_MSG = 1;

    //单人聊天文字消息
    int SINGLE_CHAT_TEXT_MSG = 2;
    int SINGLE_CHAT_PIC_MSG = 3;
    int SINGLE_CHAT_VOICE_MSG = 4;


}
