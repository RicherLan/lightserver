package lan.qxc.lightserver.netty.protocol.command;

public interface Command {
    //心跳
    int HEARTBEAT_REQUEST = 1;
    int HEARTBEAT_RESPONSE = 2;

    //登陆
    int LOGIN_REQUEST = 3;
    int LOGIN_RESPONSE = 4;

    //用户关注、取关
    int Friend_GUANZHU_MSG = 5;

    int SINGLE_CHAT_MSG_REQUEST = 6;
    int SINGLE_CHAT_MSG_RESPONSE = 7;

    int SINGLE_CHAT_MSG_PACKET = 8;

}
