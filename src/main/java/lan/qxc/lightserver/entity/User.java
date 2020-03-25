package lan.qxc.lightserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

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



}
