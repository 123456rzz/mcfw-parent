package org.mengchong.mcfw.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.manager.mapper.PetCommentInfoMapper;
import org.mengchong.mcfw.manager.mapper.ServiceCommontInfoMapper;
import org.mengchong.mcfw.manager.service.PetCommentInfoService;
import org.mengchong.mcfw.manager.service.ServiceCommontInfoService;
import org.mengchong.mcfw.model.entity.comment.PetCommentInfo;
import org.mengchong.mcfw.model.entity.comment.ServiceCommontInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCommentInfoServiceImpl implements PetCommentInfoService {
    @Autowired
    private PetCommentInfoMapper petCommentInfoMapper;

    @Override
    public PageInfo<PetCommentInfo> findByPage(Integer page, Integer limit) {
        //设置分页参数：当前页page，每页记录数limit
        PageHelper.startPage(page,limit);
        //调用mapper中的方法进行分页查询
        List<PetCommentInfo> list=petCommentInfoMapper.findByPage();
        PageInfo<PetCommentInfo> pageInfo =new PageInfo<>(list);
        //返回list集合查询结果
        return pageInfo;
    }

    @Override
    public void save(PetCommentInfo petCommentInfo) {
        petCommentInfoMapper.save(petCommentInfo);
    }

    @Override
    public void update(PetCommentInfo petCommentInfo) {
        //调用保存方法将修改的数据存到数据库
        petCommentInfoMapper.update(petCommentInfo);
    }

    @Override
    public void deleteById(Long commentId) {
        petCommentInfoMapper.delete(commentId);
    }
}
