package lan.qxc.lightserver.netty.sender.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static lan.qxc.lightserver.netty.sender.message.MessageType.SINGLE_CHAT_MSG;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChatMsg extends Message{

    private Long msgid;

    private Long sendUid;
    private String sendUername;
    private String sendUicon;


    private Long receiveUid;

    private Byte msgtype;        //2:文字  3:图片 4:语音

    private String textbody;
    private String picbody;
    private String voicebody;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date updatetime;

    private Byte readstate;     //读取状态  0 未读   1已读

    private Byte is_deleted;

    @Override
    public int getType() {
        return SINGLE_CHAT_MSG;
    }
}
