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


    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date dtcreatetime;

    private String deviceinfo;

}
