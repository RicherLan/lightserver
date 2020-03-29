package lan.qxc.lightserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendVO {

    private Long userid;
    private String phone;
    private String username;
    private Byte sex;
    private String icon;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    private String introduce;
    private String location;
    private String hometown;
    private String job;


    private String remark;
    private Byte is_blacked;

    //1代表我关注了他    2代表他关注了我   0代表好友
    private Integer guanzhu_type;

}
