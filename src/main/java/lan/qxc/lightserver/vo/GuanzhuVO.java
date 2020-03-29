package lan.qxc.lightserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuanzhuVO implements Serializable {

    private Long gzid;

    private Long userid;
    private String username;
    private String icon;
    private String introduce;

    private Long gzuid;
    private String gzusername;
    private String gzicon;
    private String gzintroduce;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    private String remarkname;
    private Byte is_deleted;
    private Byte is_blacked;

}
