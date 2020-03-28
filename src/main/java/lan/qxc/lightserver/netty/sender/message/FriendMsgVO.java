package lan.qxc.lightserver.netty.sender.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendMsgVO extends Message implements Serializable {

    private Long id;
    private Long userid;

    private String username;
    private String sex;
    private String icon;
    private String introduce;

    private Long touserid;

    private Byte msgtype;
    private String info;
    private Byte readstate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createtime;

}
