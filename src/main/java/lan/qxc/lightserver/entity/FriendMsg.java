package lan.qxc.lightserver.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendMsg {

    private Long id;
    private Long userid;
    private Long touserid;

    private Byte msgtype;
    private String info;
    private Byte readstate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createtime;


}




