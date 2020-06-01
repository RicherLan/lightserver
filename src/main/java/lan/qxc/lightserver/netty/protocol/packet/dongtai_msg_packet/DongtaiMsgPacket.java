package lan.qxc.lightserver.netty.protocol.packet.dongtai_msg_packet;


import lan.qxc.lightserver.entity.Dongtai;
import lan.qxc.lightserver.entity.DongtaiMsg;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.vo.DongtaiMsgVO;
import lan.qxc.lightserver.vo.DongtailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lan.qxc.lightserver.netty.protocol.command.Command.DONGTAI_MSG_PACKET;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DongtaiMsgPacket extends Packet {

    DongtailVO dongtailVO;
    DongtaiMsgVO dongtaiMsgVO;

    @Override
    public int getCommand() {
        return DONGTAI_MSG_PACKET;
    }

}
