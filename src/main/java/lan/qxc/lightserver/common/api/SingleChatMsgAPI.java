package lan.qxc.lightserver.common.api;

public interface SingleChatMsgAPI {

    String SET_ALL_MSG_HAD_READ_BY_UID = "/chat/single/read_msg_u_od_u";

    String DELETE_MSG_BY_MSGID = "/chat/single/delmsgbyid";

    String GET_MSG_BY_MSGID = "/chat/single/getbymsgid";

    String GET_ALL_MSG_NOT_READ_BY_UID = "/chat/single/get_msg_not_read_byuid";


}
