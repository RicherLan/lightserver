package lan.qxc.lightserver.netty.sender;


import io.netty.channel.Channel;
import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.DongtaiMsg;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.protocol.packet.dongtai_msg_packet.DongtaiMsgPacket;
import lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet.FriendMsgPacket;
import lan.qxc.lightserver.netty.sender.message_sender.MsgSender;
import lan.qxc.lightserver.netty.util.SessionUtil;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;

import java.util.HashMap;
import java.util.Map;

public class DongtaiMsgSender extends MsgSender {

    private static class DongtaiMsgSenderHolder{
        private static DongtaiMsgSender instance = new DongtaiMsgSender();
    }

    public static DongtaiMsgSender getInstance(){
        return DongtaiMsgSenderHolder.instance;
    }


    public void sendMsg(Long userid, Dongtai dongtai, DongtaiMsg dongtaiMsg){

        DongtaiMsgPacket packet = new DongtaiMsgPacket();
        packet.setVersion(new Byte("1"));
        packet.setDongtai(dongtai);
        packet.setDongtaiMsg(dongtaiMsg);
        send(userid,packet);

    }


}
