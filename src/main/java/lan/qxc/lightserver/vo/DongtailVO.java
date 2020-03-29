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
public class DongtailVO implements Serializable {


    private Long userid;
    private String username;
    private String icon;

    private Long dtid;
    private String dtcontent;
    private String dtpic;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dtcreatetime;

    private String deviceinfo;

    //1代表我关注了他    2代表他关注了我   0代表好友   4代表都不是
    private Integer guanzhu_type;

}
