package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.Role;
import com.yutons.shiro.bean.admin.RolePage;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 13:17
 */
public interface RoleService {
    /**
     * 获取角色列表
     * @return
     */
    List<Role> selectRoles();

    /**
     * 获取角色列表(分页)
     * @param role
     * @return
     */
    RolePage selectRolePage(Role role);

    /**
     * 添加角色信息,并进行授权
     * @param role
     * @return
     */
    Role add(Role role);

    /**
     * 根据ID修改角色信息
     * @param roleId
     * @return
     */
    Role selectRoleById(Integer roleId);

    /**
     * 修改角色,删除授权并重新授权
     * @param role
     * @return
     */
    Role update(Role role);
    /**
     * 删除角色,并删除授权
     * @param role
     * @return
     */
    Integer deleteRoleById(Role role);
}
