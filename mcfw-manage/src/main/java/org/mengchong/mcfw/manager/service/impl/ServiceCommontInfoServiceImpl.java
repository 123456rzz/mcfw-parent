package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.ServiceCommontInfoMapper;
import org.mengchong.mcfw.manager.service.ServiceCommontInfoService;
import org.mengchong.mcfw.model.entity.comment.ServiceCommontInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCommontInfoServiceImpl implements ServiceCommontInfoService {
    @Autowired
    private ServiceCommontInfoMapper serviceCommontInfoMapper;

    @Override
    public PageInfo<ServiceCommontInfo> findByPage(Integer page, Integer limit) {
        //设置分页参数：当前页page，每页记录数limit
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行分页查询
        List<ServiceCommontInfo> list=serviceCommontInfoMapper.findByPage();
        PageInfo<ServiceCommontInfo> pageInfo =new PageInfo<>(list);
        //返回list集合查询结果
        return pageInfo;
    }

    @Override
    public void save(ServiceCommontInfo serviceCommontInfo) {
        serviceCommontInfoMapper.save(serviceCommontInfo);
    }

    @Override
    public void update(ServiceCommontInfo serviceCommontInfo) {
        //调用保存方法将修改的数据存到数据库
        serviceCommontInfoMapper.update(serviceCommontInfo);
    }

    @Override
    public void deleteById(Long commentId) {
        serviceCommontInfoMapper.delete(commentId);
    }
}
