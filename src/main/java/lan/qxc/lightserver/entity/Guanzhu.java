package lan.qxc.lightserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guanzhu implements Serializable {

    private Long gzid;
    private Long userid;
    private Long gzuid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createtime;

    private String remarkname;
    private Byte is_deleted;
    private Byte is_blacked;



}
