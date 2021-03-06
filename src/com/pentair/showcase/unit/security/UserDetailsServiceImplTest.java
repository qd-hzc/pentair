package com.pentair.showcase.unit.security;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.pentair.showcase.common.entity.Role;
import com.pentair.showcase.common.entity.User;
import com.pentair.showcase.common.service.AccountManager;
import com.pentair.showcase.security.LoginUser;
import com.pentair.showcase.security.UserDetailsServiceImpl;

public class UserDetailsServiceImplTest {

    private IMocksControl control = EasyMock.createControl();

    private UserDetailsServiceImpl userDetailsService;
    private AccountManager mockAccountManager;

    @Before
    public void setUp() {
        userDetailsService = new UserDetailsServiceImpl();
        mockAccountManager = control.createMock(AccountManager.class);
        userDetailsService.setAccountManager(mockAccountManager);
    }

    @After
    public void tearDown() {
        control.verify();
    }

    @Test
    public void loadUserExist() {

        //准备数据
        User user = new User();
        user.setLoginName("admin");
        user.setShaPassword(new ShaPasswordEncoder().encodePassword("admin", null));
        Role role1 = new Role();
        role1.setName("admin");
        Role role2 = new Role();
        role2.setName("user");
        user.getRoles().add(role1);
        user.getRoles().add(role2);

        //录制脚本
        EasyMock.expect(mockAccountManager.findUserByLoginName("admin")).andReturn(user);
        control.replay();

        //执行测试
        LoginUser operator = (LoginUser) userDetailsService.loadUserByUsername(user.getLoginName());

        //校验结果
        assertEquals(user.getLoginName(), operator.getUsername());
        assertEquals(new ShaPasswordEncoder().encodePassword("admin", null), operator.getPassword());
        assertEquals(2, operator.getAuthorities().size());
        assertEquals(new GrantedAuthorityImpl("ROLE_admin"), operator.getAuthorities().iterator().next());
        assertNotNull(operator.getLoginTime());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByWrongUserName() {
        //录制脚本
        EasyMock.expect(mockAccountManager.findUserByLoginName("foo")).andReturn(null);
        control.replay();

        assertNull(userDetailsService.loadUserByUsername("foo"));
    }
}
