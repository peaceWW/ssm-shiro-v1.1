package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.Role;
import com.yutons.shiro.bean.admin.UserPage;
import com.yutons.shiro.dao.admin.RoleDao;
import com.yutons.shiro.util.MenuUtil;
import com.yutons.shiro.util.ShiroKit;
import com.yutons.shiro.util.TokenUtil;
import com.yutons.shiro.bean.admin.User;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.yutons.shiro.dao.admin.UserDao;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    /**
     * 登录逻辑
     * 1、先根据用户名查询用户对象
     * 2、如果有用户对象，则继续匹配密码
     * 如果没有用户对象，则抛出异常
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User user = userDao.findByUserName(username);
        // 密码匹配的工作交给 Shiro 去完成
        if (user == null) {
            // 因为缓存切面的原因,在这里就抛出用户名不存在的异常
            throw new UnknownAccountException("用户名不存在(生产环境中应该写:用户名和密码的组合不正确)");
        } else if (user.getStatus() == 0) {
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
        return user;
    }

    /**
     * 根据用户id查询权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Permission> selectPermissionsByUserId(Integer userId) {
        List<Permission> permissions = userDao.selectPermissionsByUserId(userId);
        /*if (roleIds.contains(1)){
            //超级管理员
        }*/
        return permissions;
    }

    /**
     * 根据用户id查询角色昵称
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> selectRoleSnsByUserId(Integer userId) {
        return userDao.selectRoleSnsByUserId(userId);
    }

    /**
     * 根据用户ID获取菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<List<Permission>> selectMenusByUserId(Integer userId) {
        List<Integer> roleIds = userDao.selectRoleIdByUserId(userId);
        List<Permission> permissions = null;
        //如果角色id包含1,说明当前登录用户拥有超级管理员身份
        if (roleIds.contains(1)) {
            permissions = userDao.selectAllMenus();
        } else {
            permissions = userDao.selectMenusByUserId(userId);
        }
        List<List<Permission>> list = null;
        if (permissions.size() > 0) {
            list = MenuUtil.getMenus(permissions);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == 0) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public UserPage selectUserPage(User user) {
        List<User> data = userDao.selectUserByCondition(user);
        User token = TokenUtil.getUser();
        Role role = roleDao.selectRoleByUserId(token.getUserId());
        if (role.getId() >= 2) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getRoleId() == 1 || data.get(i).getRoleId() == 2) {
                    data.remove(i);
                    i--;
                }
            }
        }
        Integer count = userDao.selectUserCountByCondition();
        UserPage userPage = new UserPage();
        userPage.setCount(count);
        userPage.setData(data);
        return userPage;
    }

    /**
     * 添加用户信息,并关联角色表
     *
     * @param user
     * @return
     */
    @Override
    public User add(User user) {
        if (user.getPassword() == null||user.getPassword()=="") {
            user.setPassword("000000");
        }
        // 使用用户名作为盐值，MD5 算法加密
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
        User u = userDao.findByUserName(user.getUsername());
        //判断要插入的目标对象是否存在
        if (u == null) {
            Date now=new Date();
            int i = userDao.insert(user);
            int j = userDao.insertUserRole(user);
        } else {
            user = new User();
        }
        return user;
    }

    /**
     * 根据id获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);
    }

    /**
     * 提交用户修改数据
     *
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        User u = userDao.findByUserId(user.getUserId());
        user.setId(u.getId());
        user.setDeptname(u.getDeptname());
        user.setRolename(u.getRolename());
        if (!u.equals(user)) {
            if (!(u.getPassword()).equals(user.getPassword())) {
                // 使用用户名作为盐值，MD5 算法加密
                user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
            }
            /*//修改用户
            int update = userDao.update(user);
            //删除用户角色关联
            int deleteUserRole = userDao.delectUserRole(user);
            //重新添加用户角色关联
            int addUserRole = userDao.addUserRole(user);*/
        }
        //修改用户
        int update = userDao.update(user);
        //删除用户角色关联
        int deleteUserRole = userDao.delectUserRole(user);
        //重新添加用户角色关联
        int addUserRole = userDao.addUserRole(user);
        return user;
    }

    /**
     * 修改用户当前状态
     *
     * @param user
     * @return
     */
    @Override
    public Integer updateStatusById(User user) {
        int i = userDao.updateStatusById(user);
        return i;
    }

    /**
     * 删除选择用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer deleteUserById(User user) {
        int i = userDao.deleteUserById(user);
        int i1 = userDao.delectUserRole(user);
        if (i + i1 >= 2) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * 根据用户名加载用户对象（用于登录使用）
     * @param username
     * @return
     */
    @Override
    public User loadByUsername(String username) {
        return userDao.loadByUserName(username);
    }
    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    public Integer updatePwd(User user) {
        user.setPassword(ShiroKit.md5(user.getPassword(),user.getUsername()));
        return userDao.updatePwd(user);
    }
    /**
     * 根据用户id获取角色id列表
     * @param id
     * @return
     */
    @Override
    public List<Integer> selectRoleIdByUserId(Integer id) {
        return userDao.selectRoleIdByUserId(id);
    }
}
