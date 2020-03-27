package lan.qxc.lightserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private Long userid;
    private String phone;
    private String username;
    private String password;
    private Byte sex;
    private String icon;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    private String introduce;
    private String location;
    private String hometown;
    private String job;

    private Byte is_deleted;
    private Byte is_locked;

    private Integer my_guanzhu_num;
    private Integer guanzhu_me_num;
    private Integer my_friend_num;

}
