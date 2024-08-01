package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.PetServeInfoService;
import org.mengchong.mcfw.model.dto.service.PetServeDto;
import org.mengchong.mcfw.model.entity.service.PetServeInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin/petserve")
public class PetServeInfoController {
    @Autowired
    private PetServeInfoService petServeInfoService;

    /**
     *  1 宠物服务列表条件分页查询接口
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param petServeDto 查询条件对象
     * @return
     */
    @Log(title = "宠物信息列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping(value ="/findByPage/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                             PetServeDto petServeDto) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<PetServeInfo> pageInfo = petServeInfoService.findByPage(page, limit, petServeDto);
        //
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * // 2 宠物服务信息添加
     * @param petServeInfo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody PetServeInfo petServeInfo) {
        petServeInfoService.save(petServeInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 3 宠物服务信息修改
     * @param petServeInfo 对象
     * @return
     */
    @PutMapping("/update")
    public Result updateBrand(@RequestBody PetServeInfo petServeInfo){
        petServeInfoService.update(petServeInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 宠物服务信息删除
     * @param serveId 宠物服务id
     * @return
     */
    @DeleteMapping("/deleteById/{serveId}")
    public Result deleteById(@PathVariable("serveId") Long serveId){
        petServeInfoService.deleteById(serveId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
