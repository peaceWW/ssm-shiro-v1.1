package com.yutons.shiro.web.controller.admin;

import com.yutons.shiro.bean.admin.Permission;
import com.yutons.shiro.bean.admin.PermissionPage;
import com.yutons.shiro.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 11:54
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转到权限列表
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String msg, Model model) {
        /*if (msg != null && msg != "") {
            model.addAttribute("msg", "权限添加成功!");
        }*/
        return "admin/permission/list";
    }

    /**
     * 根据条件获取权限列表
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PermissionPage list(Permission permission) {
        return permissionService.selectPermissionPage(permission);
    }

    /**
     * 跳转到权限添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addIndex(Model model) {
        List<Permission> parantMenus=permissionService.selectAllPermissions();
        model.addAttribute("parantMenus",parantMenus);
        return "admin/permission/add";
    }

    /**
     * 添加权限---提交
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Permission permission, Model model) {
        //如果父级菜单为null,说明是顶级菜单,设置为0
        if (permission.getParantid()==null){
            permission.setParantid(0);
        }
        Integer add = permissionService.add(permission);
        if (add == 0) {
            //添加失败,重新添加
            model.addAttribute("permission", permission);
            model.addAttribute("msg", "权限ID已存在,请重新输入!");
            return "/admin/permission/add";
        } else {
            return "redirect:/admin/permission/index?msg=success";
        }
    }
    /**
     * 跳转到角色修改页面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{permissionId}", method = RequestMethod.GET)
    public String updateIndex(@PathVariable("permissionId") Integer permissionId, Model model) {
        Permission permission = permissionService.selectPermissionById(permissionId);
        List<Permission> parantMenus=permissionService.selectAllPermissions();
        model.addAttribute("permission",permission);
        model.addAttribute("parantMenus",parantMenus);
        return "admin/permission/update";
    }

    /**
     * 更新角色,删除授权并重新进行授权
     * @param role
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Permission permission, Model model) {
        Integer update = permissionService.update(permission);
        return "redirect:/admin/permission/index?msg=success";
    }
    /**
     * 根据id删除权限
     * @param permission
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletePermissionById",method = RequestMethod.POST)
    public String deletePermissionById(Permission permission){
        Integer i=permissionService.deletePermissionById(permission);
        if (i==1) {
            return "success";
        }else {
            return "error";
        }
    }
}
