package lan.qxc.lightserver.netty.protocol.response.user_response;


import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.netty.protocol.Packet;
import lan.qxc.lightserver.vo.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lan.qxc.lightserver.netty.protocol.command.Command.LOGIN_RESPONSE;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet {

    private String rs;
    private String type;
    private PersonalInfo personalInfo;

    @Override
    public int getCommand() {
        return LOGIN_RESPONSE;
    }

}
