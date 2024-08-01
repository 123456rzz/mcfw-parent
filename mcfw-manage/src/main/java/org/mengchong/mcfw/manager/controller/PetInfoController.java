package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.PetInfoService;
import org.mengchong.mcfw.model.dto.pet.PetInfoDto;
import org.mengchong.mcfw.model.entity.pet.PetInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/pet/pet")
public class PetInfoController {
    @Autowired
    private PetInfoService petInfoService;

    /**
     *  1 宠物列表条件分页查询接口
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param petInfoDto 查询条件对象
     * @return
     */
    @Log(title = "宠物信息列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping(value ="/findByPage/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                             PetInfoDto petInfoDto) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo< PetInfo> pageInfo = petInfoService.findByPage(page, limit, petInfoDto);
        //
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * // 2 宠物信息添加
     * @param petInfo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody PetInfo petInfo) {
        petInfoService.save(petInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 3 宠物信息修改
     * @param petInfo 对象
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody PetInfo petInfo){
        petInfoService.update(petInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 宠物信息删除
     * @param petId 宠物id
     * @return
     */
    @DeleteMapping("/deleteById/{petId}")
    public Result deleteById(@PathVariable("petId") Long petId){
        petInfoService.deleteById(petId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}
