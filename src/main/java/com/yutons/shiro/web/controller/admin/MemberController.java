package com.yutons.shiro.web.controller.admin;

import com.yutons.shiro.bean.admin.User;
import com.yutons.shiro.service.admin.RoleService;
import com.yutons.shiro.service.admin.UserService;
import com.yutons.shiro.util.ShiroKit;
import com.yutons.shiro.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yutons
 * @desc
 * @date 2017/10/31 12:52
 */
@Controller
@RequestMapping("/admin/member")
public class MemberController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /**
     * 跳转到修改密码页面
     * @param msg
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatepwd", method = RequestMethod.GET)
    public String updatepwd(String msg, Model model) {
        User user= TokenUtil.getUser();
        model.addAttribute("user",user);
        return "admin/member/updatepwd";
    }

    /**
     * 修改密码
     * @param user
     * @param pwd
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatepwd", method = RequestMethod.POST)
    public Integer update(User user, String pwd, Model model) {
        String pwdKit = ShiroKit.md5(pwd, user.getUsername());
        String password = userService.loadByUsername(user.getUsername()).getPassword();
        if (pwdKit.equals(password)) {
            //密码校验成功，执行修改密码操作
            Integer i = userService.updatePwd(user);
            if (i == 1) {
                return 1;
            } else {
                return 2;
            }

        } else {
            return 0;
        }
    }

    /**
     * 跳转到我的权限页面
     * @param msg
     * @param model
     * @return
     */
    /*@RequestMapping(value = "/mypermission", method = RequestMethod.GET)
    public String mypermission(String msg, Model model) {
        User user= TokenUtil.getUser();
        List<Integer> roleIds=userService.selectRoleIdByUserId(user.getId());
        String[] permissions ={};
        if (roleIds!=null&&roleIds.size()==1){
            Integer roleId=roleIds.get(0);
            Role role = roleService.selectRoleById(roleId);
            permissions = role.getPermission().split(" ");
        }
        model.addAttribute("permissions",permissions);
        model.addAttribute("user",user);
        return "admin/member/mypermission";
    }*/
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        User user= TokenUtil.getUser();
        model.addAttribute("user",user);
        return "admin/member/detail";
    }
}
