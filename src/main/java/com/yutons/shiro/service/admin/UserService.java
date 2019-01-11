package com.yutons.shiro.service.admin;

import java.util.List;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.User;
import com.yutons.shiro.bean.admin.UserPage;

/**
 * @author yutons
 */
public interface UserService {
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
    User login(String username, String password);

    /**
     * 根据用户id查询权限列表
     *
     * @param userId
     * @return
     */
    List<Permission> selectPermissionsByUserId(Integer userId);

    /**
     * 根据用户id查询角色昵称
     *
     * @param userId
     * @return
     */
    List<String> selectRoleSnsByUserId(Integer userId);

    /**
     * 根据用户ID获取菜单列表
     *
     * @param userId
     * @return
     */
    List<List<Permission>> selectMenusByUserId(Integer userId);

    /**
     * 获取用户列表
     *
     * @return
     */
    UserPage selectUserPage(User user);

    /**
     * 添加用户信息,并关联角色表
     *
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 根据id获取用户信息
     *
     * @param userId
     * @return
     */
    User selectUserById(Integer userId);

    /**
     * 提交用户修改数据
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 修改用户当前状态
     *
     * @param user
     * @return
     */
    Integer updateStatusById(User user);
    /**
     * 删除选择用户
     * @param user
     * @return
     */
    Integer deleteUserById(User user);
    /**
     * 根据用户名加载用户对象（用于登录使用）
     * @param username
     * @return
     */
    User loadByUsername(String username);

    /**
     * 修改密码
     * @param user
     * @return
     */
    Integer updatePwd(User user);

    /**
     * 根据用户id获取角色id列表
     * @param id
     * @return
     */
    List<Integer> selectRoleIdByUserId(Integer id);
}
