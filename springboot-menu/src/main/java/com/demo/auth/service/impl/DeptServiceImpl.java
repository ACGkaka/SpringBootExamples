package com.demo.auth.service.impl;

import com.demo.auth.entity.Dept;
import com.demo.auth.mapper.DeptMapper;
import com.demo.auth.service.DeptService;
import com.demo.common.exception.BaseException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept queryById(Long id) {
        // 根据ID查找
        return deptMapper.queryById(id);
    }

    @Override
    public PageInfo<Dept> queryByPage(int pageNum, int pageSize) {
        // 分页查找
        PageHelper.startPage(pageNum, pageSize);
        List<Dept> list = deptMapper.queryAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<Dept> queryAll() {
        // 查询全部
        return deptMapper.queryAll();
    }

    @Override
    public Dept insert(Dept dept) {
        // 新增部门
        Dept query = new Dept();
        query.setDeptName(dept.getDeptName());
        List<Dept> list = deptMapper.query(query);
        if (list != null && list.size() > 0) {
            throw new BaseException("部门名称已存在");
        }
        if (dept.getParentId() == null) {
            dept.setParentId(-1L);
        }
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Dept update(Dept dept) {
        // 编辑部门
        Dept result = deptMapper.queryById(dept.getId());
        if (result == null) {
            throw new BaseException("部门不存在");
        }
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
        return  queryById(dept.getId());
    }

    @Override
    public void deleteById(Long id) {
        // 删除部门
        Dept dept = deptMapper.queryById(id);
        if (dept == null) {
            throw new BaseException("部门不存在");
        }
        deptMapper.deleteById(id);
    }
}