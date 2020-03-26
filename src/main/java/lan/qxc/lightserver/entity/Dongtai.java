package lan.qxc.lightserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dongtai implements Serializable {

    private Long dtid;
    private Long userid;

    private String dtcontent;
    private String dtpic;


    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date dtcreatetime;

    private String deviceinfo;

    private Byte is_deleted;
    private Byte is_locked;


}
