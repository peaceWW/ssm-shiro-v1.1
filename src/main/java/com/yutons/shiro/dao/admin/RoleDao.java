package com.yutons.shiro.dao.admin;

import com.yutons.shiro.bean.admin.Role;
import com.yutons.shiro.bean.admin.RoleAndPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 13:23
 */
public interface RoleDao {
    /**
     * 获取角色列表
     *
     * @return
     */
    List<Role> selectRoles();

    /**
     * 根据条件获取角色列表
     *
     * @param role
     * @return
     */
    List<Role> selectRoleByCondition(@Param("role") Role role);

    /**
     * 根据条件获取角色总数
     *
     * @param role
     * @return
     */
    Integer selectRoleCountByCondition(@Param("role") Role role);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    int add(@Param("role") Role role);

    /**
     * 批量添加角色权限关联
     *
     * @param roleAndPermissions
     * @return
     */
    int batchAddRoleAndPermission(@Param("roleAndPermissions") List<RoleAndPermission> roleAndPermissions);

    /**
     * 根据角色id获取权限字符串
     *
     * @param roleId
     * @return
     */
    List<Role> selectPermissionByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据ID修改角色信息
     *
     * @param roleId
     * @return
     */
    Role selectRoleById(@Param("roleId") Integer roleId);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    int update(@Param("role") Role role);

    /**
     * 删除授权
     *
     * @param roleId
     * @return
     */
    int delectPermissionByRoleId(@Param("roleId") Integer roleId);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    int deleteRoleById(Integer roleId);

    /**
     * 根据用户id获取角色id
     * @param userId
     * @return
     */
    Role selectRoleByUserId(Integer userId);
}
