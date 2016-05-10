package com.pentair.showcase.data;

import com.pentair.showcase.common.dao.UIDGenerator;
import com.pentair.showcase.common.entity.Role;
import com.pentair.showcase.common.entity.User;
import org.springside.modules.test.utils.DataUtils;

/**
 * 用户测试数据生成.
 *
 * @author calvin
 */
public class UserData {

    public static String getUserId() {
        return (String) new UIDGenerator().generate(null, null);
    }

    public static User getRandomUser() {
        String userName = DataUtils.randomName("User");

        User user = new User();
        user.setLoginName(userName);
        user.setName(userName);
        user.setPlainPassword("123456");
        user.setEmail(userName + "@springside.org.cn");

        return user;
    }

    public static User getRandomUserWithAdminRole() {
        User user = UserData.getRandomUser();
        Role adminRole = UserData.getAdminRole();
        user.getRoles().add(adminRole);
        return user;
    }

    public static Role getAdminRole() {
        Role role = new Role();
        role.setId("1");
        role.setName("Admin");

        return role;
    }
}
