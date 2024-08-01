package org.mengchong.mcfw.manager.controller;

import com.github.pagehelper.PageInfo;
import org.mengchong.mcfw.common.log.annotation.Log;
import org.mengchong.mcfw.common.log.enums.OperatorType;
import org.mengchong.mcfw.manager.service.PetOrderService;
import org.mengchong.mcfw.model.dto.order.PetOrderDto;
import org.mengchong.mcfw.model.entity.order.McPetOrderInfo;
import org.mengchong.mcfw.model.vo.common.Result;
import org.mengchong.mcfw.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin/petorder/order")
public class PetOrderController {
    @Autowired
    private PetOrderService petOrderService;

    /**
     *  1 宠物服务订单列表条件分页查询接口
     * @param page 当前页
     * @param limit 每页显示记录数
     * @param petOrderDto 查询条件对象
     * @return
     */
    @Log(title = "宠物服务订单列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping(value ="/findByPage/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                             PetOrderDto petOrderDto) {
        //调用services中的方法进行查询,pageHelper插件实现分页,pageInfo包含了每一页的记录数据
        PageInfo<McPetOrderInfo> pageInfo = petOrderService.findByPage(page, limit, petOrderDto);
        //
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * // 2 宠物服务订单添加
     * @param mcPetOrderInfo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody McPetOrderInfo mcPetOrderInfo) {
        petOrderService.save(mcPetOrderInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     *  // 3 宠物服务订单修改
     * @param mcPetOrderInfo 对象
     * @return
     */
    @PutMapping("/update")
    public Result updateBrand(@RequestBody McPetOrderInfo mcPetOrderInfo){
        petOrderService.update(mcPetOrderInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     *  //    4 宠物服务订单删除
     * @param orderId 宠物服务id
     * @return
     */
    @DeleteMapping("/deleteById/{orderId}")
    public Result deleteById(@PathVariable("orderId") Long orderId){
        petOrderService.deleteById(orderId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
