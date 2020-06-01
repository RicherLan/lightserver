package lan.qxc.lightserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lan.qxc.lightserver.entity.DongtaiMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DongtaiMsgVO {

    private Long userid;
    private String username;
    private String icon;

    private Long msgid;
    private Long dtid;

    private Byte msgtype;      //1点赞     2评论   3转发

    private String body;
    private Byte readstate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private Byte is_deleted;

}
