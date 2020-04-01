package lan.qxc.lightserver.netty.protocol.packet.chat_msg;

import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.netty.sender.message.SingleChatMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lan.qxc.lightserver.netty.protocol.command.Command.SINGLE_CHAT_MSG_PACKET;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChatMsgPacket extends Packet {

    SingleChatMsg singleChatMsg;

    @Override
    public int getCommand() {
        return SINGLE_CHAT_MSG_PACKET;
    }
}
