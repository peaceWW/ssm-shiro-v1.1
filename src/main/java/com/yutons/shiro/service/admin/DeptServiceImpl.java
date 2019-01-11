package com.yutons.shiro.service.admin;

import com.yutons.shiro.bean.admin.Dept;
import com.yutons.shiro.dao.admin.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yutons
 * @desc
 * @date 2017/10/26 13:14
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    /**
     * 获取部门列表
     * @return
     */
    @Override
    public List<Dept> selectDepts() {
        return deptDao.selectDepts();
    }
}
