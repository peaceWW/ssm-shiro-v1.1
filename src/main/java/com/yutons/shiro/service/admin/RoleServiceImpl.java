package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.*;
import com.yutons.shiro.bean.admin.RoleAndPermission;
import com.yutons.shiro.bean.admin.RolePage;
import com.yutons.shiro.dao.admin.RoleDao;
import com.yutons.shiro.util.TokenUtil;
import com.yutons.shiro.bean.admin.Role;
import com.yutons.shiro.bean.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 13:17
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 获取角色列表
     *
     * @return
     */
    @Override
    public List<Role> selectRoles() {
        User user = TokenUtil.getUser();
        Role role=roleDao.selectRoleByUserId(user.getUserId());
        List<Role> roles = roleDao.selectRoles();
        if (role.getId()>=2){
            for (int i = 0; i < roles.size(); i++) {
                if (roles.get(i).getId()==1||roles.get(i).getId()==2){
                    roles.remove(i);
                    i--;
                }
            }
        }
        return roles;
    }

    /**
     * 获取角色列表(分页)
     *
     * @param role
     * @return
     */
    @Override
    public RolePage selectRolePage(Role role) {
        List<Role> data = roleDao.selectRoleByCondition(role);
        for (Role role1 : data) {
            List<Role> roles = roleDao.selectPermissionByRoleId(role1.getRoleId());
            String permission = "";
            for (Role role2 : roles) {
                if (role2 != null) {
                    permission += role2.getPermission() + " ";
                }
            }
            role1.setPermission(permission);
            //System.out.println(permission);
        }
        Integer count = roleDao.selectRoleCountByCondition(role);
        RolePage rolePage = new RolePage();
        rolePage.setCount(count);
        rolePage.setData(data);
        return rolePage;
    }

    /**
     * 添加角色信息,并进行授权
     *
     * @param role
     * @return
     */
    @Override
    public Role add(Role role) {
        //添加角色,并返回主键
        int i = roleDao.add(role);
        //进行授权
        if (role.getPermission() != null) {
            String[] permissions = role.getPermission().split(",");
            List<RoleAndPermission> roleAndPermissions = new ArrayList<>();
            for (int j = 0; j < permissions.length; j++) {
                RoleAndPermission roleAndPermission = new RoleAndPermission();
                Integer permission = Integer.parseInt(permissions[j]);
                roleAndPermission.setRoleId(role.getRoleId());
                roleAndPermission.setPermissionId(permission);
                roleAndPermissions.add(roleAndPermission);
            }
            int j = roleDao.batchAddRoleAndPermission(roleAndPermissions);
        }
        return role;
    }

    /**
     * 根据ID修改角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    public Role selectRoleById(Integer roleId) {
        Role role = roleDao.selectRoleById(roleId);
        List<Role> roles = roleDao.selectPermissionByRoleId(roleId);
        String permission = "";
        for (Role role2 : roles) {
            if (role2 != null) {
                permission += role2.getPermission() + " ";
            }
        }
        role.setPermission(permission);
        return role;
    }

    /**
     * 修改角色,删除授权并重新授权
     *
     * @param role
     * @return
     */
    @Override
    public Role update(Role role) {
        //修改角色
        int a=roleDao.update(role);
        //删除角色授权
        int b=roleDao.delectPermissionByRoleId(role.getRoleId());
        //进行授权
        if (role.getPermission() != null) {
            String[] permissions = role.getPermission().split(",");
            List<RoleAndPermission> roleAndPermissions = new ArrayList<>();
            for (int j = 0; j < permissions.length; j++) {
                RoleAndPermission roleAndPermission = new RoleAndPermission();
                Integer permission = Integer.parseInt(permissions[j]);
                roleAndPermission.setRoleId(role.getRoleId());
                roleAndPermission.setPermissionId(permission);
                roleAndPermissions.add(roleAndPermission);
            }
            int c= roleDao.batchAddRoleAndPermission(roleAndPermissions);
        }
        return role;
    }
    /**
     * 删除角色,并删除授权
     * @param role
     * @return
     */
    @Override
    public Integer deleteRoleById(Role role) {
        int i=roleDao.deleteRoleById(role.getRoleId());
        int j= roleDao.delectPermissionByRoleId(role.getRoleId());
        if (i + j >= 2) {
            return 1;
        }else{
            return 0;
        }
    }
}
