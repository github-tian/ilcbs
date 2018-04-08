package cn.tzs.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


//region Description
public interface BaseService<T> {
    //endregion
    //获取一条记录
    public T findOne(String id);

    //查询所有，带条件查询
    public List<T> find(Specification<T> spec);

    //分页查询，将数据封装到一个page分页工具类对象
    public Page<T> findPage(Specification<T> spec, Pageable pageable);

    //新增和修改保存
    public void saveOrUpdate(T entity);

    //批量新增和修改保存
    public void saveOrUpdateAll(Collection<T> entitys);

    //单条删除，按id
    public void deleteById(String id);

    //批量删除
    public void delete(String[] ids);

}
