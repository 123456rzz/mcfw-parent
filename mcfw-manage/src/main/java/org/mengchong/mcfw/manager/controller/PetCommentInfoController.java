package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.PetCommentInfoService;
import org.mengchong.mcfw.model.entity.comment.PetCommentInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/admin/comment/pet")
public class PetCommentInfoController {
    @Autowired
    private PetCommentInfoService petCommentInfoService;

    /**
     *  // 1 宠物社区评论列表分页查询接口
     * @param page 当前页
     * @param limit 每页显示记录数
     * @return
     */
    //列表分页
    @Log(title = "社区评论列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit){
        PageInfo<PetCommentInfo> pageInfo= petCommentInfoService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * // 2 社区评论添加
     * @param petCommentInfo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody PetCommentInfo petCommentInfo) {
        petCommentInfoService.save(petCommentInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 3 社区评论修改
     * @param petCommentInfo 对象
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody PetCommentInfo petCommentInfo){
        petCommentInfoService.update(petCommentInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 社区评论删除
     * @param commentId 宠物id
     * @return
     */
    @DeleteMapping("/deleteById/{commentId}")
    public Result deleteById(@PathVariable("commentId") Long commentId){
        petCommentInfoService.deleteById(commentId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}


