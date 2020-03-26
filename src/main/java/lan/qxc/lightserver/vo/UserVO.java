package lan.qxc.lightserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private Long userid;
    private String username;
    private Byte sex;
    private String icon;

    private String introduce;

    private String remark;
    private Byte is_blacked;

}
