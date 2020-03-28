package lan.qxc.lightserver.netty.protocol.packet.friend_msg_packet;

import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.sender.message.FriendMsgVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lan.qxc.lightserver.netty.protocol.command.Command.Friend_GUANZHU_MSG;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendMsgPacket extends Packet {

    FriendMsgVO friendMsgVO;

    @Override
    public int getCommand() {
        return Friend_GUANZHU_MSG;
    }

}
