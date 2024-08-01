package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.ServiceCommontInfoService;
import org.mengchong.mcfw.model.entity.comment.ServiceCommontInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/admin/comment/user")
public class ServiceCommontInfoController {
    @Autowired
    private ServiceCommontInfoService serviceCommontInfoService;

    /**
     *  // 1 用户服务评论列表分页查询接口
     * @param page 当前页
     * @param limit 每页显示记录数
     * @return
     */
    //列表分页
    @Log(title = "用户服务评论列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit){
        PageInfo<ServiceCommontInfo> pageInfo= serviceCommontInfoService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * // 2 用户服务评论添加
     * @param serviceCommontInfo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody ServiceCommontInfo serviceCommontInfo) {
        serviceCommontInfoService.save(serviceCommontInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 3 用户服务评论修改
     * @param serviceCommontInfo 对象
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody ServiceCommontInfo serviceCommontInfo){
        serviceCommontInfoService.update(serviceCommontInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 用户服务评论删除
     * @param commentId 宠物id
     * @return
     */
    @DeleteMapping("/deleteById/{commentId}")
    public Result deleteById(@PathVariable("commentId") Long commentId){
        serviceCommontInfoService.deleteById(commentId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}


