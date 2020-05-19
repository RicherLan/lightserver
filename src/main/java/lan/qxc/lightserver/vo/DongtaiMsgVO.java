package lan.qxc.lightserver.vo;

import lan.qxc.lightserver.entity.DongtaiMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DongtaiMsgVO {

    private Long userid;
    private String username;
    private String icon;

    private DongtaiMsg dongtaiMsg;

}
