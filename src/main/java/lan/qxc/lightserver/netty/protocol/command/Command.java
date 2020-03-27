package lan.qxc.lightserver.netty.protocol.command;

public interface Command {
    //心跳
    int HEARTBEAT_REQUEST = 1;
    int HEARTBEAT_RESPONSE = 2;

    //登陆
    int LOGIN_REQUEST = 3;
    int LOGIN_RESPONSE = 4;

}
