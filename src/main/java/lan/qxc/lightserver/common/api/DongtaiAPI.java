package lan.qxc.lightserver.common.api;

public interface DongtaiAPI {

    String ADD_DONGTAI = "/dongtai/add";
    String ADD_DONGTAI_NOT_PIC = "/dongtai/addnpic";
    String DELETE_DONGTAI = "/dongtai/delete";
    String UPDATE_DONGTAI = "/dongtai/update";

    String GET_DONGTAI_BACK_LIST = "/dongtai/backlist";
    String GET_DONGTAI_NEW_LIST = "/dongtai/newlist";


    String GET_USER_DONGTAI_BACK_LIST = "/dongtai/user_backlist";
    String GET_USER_DONGTAI_NEW_LIST = "/dongtai/user_newlist";


    //动态消息
    String LIKE_DONGTAI = "/dongtai/like";
    String COMMON_DONGTAI = "/dongtai/common";
    String TRANSMIT_DONGTAI = "/dongtai/transmit";


}
