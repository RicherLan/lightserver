package lan.qxc.lightserver.netty.sender.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lan.qxc.lightserver.netty.sender.message.MessageType.SINGLE_CHAT_TEXT_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextSingleChatMessage extends Message {


    private Long sendUid;
    private String sendUername;
    private String sendUicon;
    private Byte sex;

    private Long receiveUid;

    private String textbody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private String createtime;

    @Override
    public int getType() {
        return SINGLE_CHAT_TEXT_MSG;
    }
}
