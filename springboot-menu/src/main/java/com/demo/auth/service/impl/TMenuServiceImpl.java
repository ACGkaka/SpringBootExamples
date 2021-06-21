package com.demo.auth.service.impl;

import com.demo.auth.entity.TMenu;
import com.demo.auth.mapper.TMenuMapper;
import com.demo.auth.service.TMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(TMenu)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service("tMenuService")
public class TMenuServiceImpl implements TMenuService {
    @Resource
    private TMenuMapper tMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TMenu queryById(Long id) {
        return this.tMenuMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TMenu> queryAllByLimit(int offset, int limit) {
        return this.tMenuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu insert(TMenu tMenu) {
        this.tMenuMapper.insert(tMenu);
        return tMenu;
    }

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu update(TMenu tMenu) {
        this.tMenuMapper.update(tMenu);
        return this.queryById(tMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tMenuMapper.deleteById(id) > 0;
    }
}