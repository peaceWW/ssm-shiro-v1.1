package com.yutons.shiro.web.controller.admin;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.Role;
import com.yutons.shiro.bean.admin.RolePage;
import com.yutons.shiro.service.admin.PermissionService;
import com.yutons.shiro.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/27 12:54
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转到角色列表
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String msg, Model model) {
        /*if (msg != null && msg != "") {
            model.addAttribute("msg", "角色添加成功!");
        }*/
        return "admin/role/list";
    }

    /**
     * 根据条件获取角色列表
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RolePage list(Role role) {
        return roleService.selectRolePage(role);
    }

    /**
     * 跳转到角色添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addIndex(Model model) {
        Permission permission = permissionService.selectPermission();
        model.addAttribute("permission",permission);
        return "admin/role/add";
    }

    /**
     * 添加角色---提交
     *
     * @param user
     * @param model
     * @return
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Role role, Model model) {
        Role add = roleService.add(role);
        return "redirect:/admin/role/index?msg=success";
    }

    /**
     * 跳转到角色修改页面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{roleId}", method = RequestMethod.GET)
    public String updateIndex(@PathVariable("roleId") Integer roleId, Model model) {
        Role role = roleService.selectRoleById(roleId);
        String[] permissions = role.getPermission().split(" ");
        List list=new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            list.add(permission);
        }
        Permission permission = permissionService.selectPermission();
        model.addAttribute("permission",permission);
        model.addAttribute("permissions",list);
        model.addAttribute("role", role);
        return "admin/role/update";
    }

    /**
     * 更新角色,删除授权并重新进行授权
     * @param role
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, Model model) {
        Role update = roleService.update(role);
        return "redirect:/admin/role/index?msg=success";
    }

    /**
     * 删除角色,并删除授权
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteRoleById",method = RequestMethod.POST)
    public String deleteRoleById(Role role){
        Integer i=roleService.deleteRoleById(role);
        if (i==1) {
            return "success";
        }else {
            return "error";
        }
    }
}
