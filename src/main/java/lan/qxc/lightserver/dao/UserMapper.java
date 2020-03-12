package lan.qxc.lightserver.dao;

import lan.qxc.lightserver.entity.User;

public interface UserMapper {

    int deleteByUserid(Long userid);

    int insert(User user);

    int insertSelective(User user);

    User selectByUserid(Long userid);

    User selectByPhone(String phone);

    int updateByUseridSelective(User user);

    int updateByUserid(User user);


}
