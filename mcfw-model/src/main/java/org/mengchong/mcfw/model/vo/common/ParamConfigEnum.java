package org.mengchong.mcfw.model.vo.common;

import lombok.Getter;

@Getter
public enum ParamConfigEnum {

    // region 角色
    NGDW_ROLE("拟稿"),
    NGDW_BM_ROLE("科室审核"),
    NGDW_ZHK_ROLE("综合科审核"),
    NGDW_ZHK_FZR_ROLE("综合科负责人审核"),
    NGDW_CLD_ROLE("部门负责人审核"),
    ZG_GLD_ROLE("总关关领导审核"),
    LSG_GLD_ROLE("隶属海关关领导"),
    WSB_ROLE("总关办公室外事办审核"),
    WSB_FH_ROLE("总关办公室外事办科室审核"),
    CT_ZHK_ROLE("参团单位综合部门审核"),
    CT_ZHK_FZR_ROLE("参团单位综合部门负责人审核"),
    CT_CLD_ROLE("参团单位部门负责人审核或者参团单位关领导审核"),
    CT_GLD_ROLE("总关关领导审核"),
    WSB_GLD_ROLE("总关关领导审核"),
    ZNBM_ZHK_ROLE("会签单位综合部门审核"),
    ZNBM_ZHK_FZR_ROLE("会签单位综合部门负责人审核"),
    ZNBM_CLD_ROLE("会签单位部门负责人审核"),
    ZNBM_GLD_ROLE("总关关领导审核"),
    RS_KS_ROLE("职能部门科室审核"),
    RS_KS_FZR_ROLE("职能部门科室负责人审核"),
    RS_CLD_ROLE("职能部门负责人审核"),
    RS_GLD_ROLE("总关关领导审核"),
    WSB_ZR_APPR_ROLE("总关办公室负责人审核"),
    WSB_ZR_GLD_ROLE("总关关领导审核"),
    // endregion
    // region 流程节点
    /**
     * ======================================拟稿单位======================================
     */
    BACK_NGDW_PROCESS("返回拟稿"),
    BACK_NGDW_ZHK_PROCESS("返回综合科"),
    NGDW_LSG_ZHK_PROCESS("隶属海关办公室审核"),
    NGDW_LSG_ZHK_FZR_PROCESS("隶属海关办公室负责人审核"),
    BACK_NGDW_LSG_ZHK_PROCESS("返回隶属海关办公室"),
    /**
     * ======================================总关办公室外事办审核======================================
     */
    BACK_WSB_CS_PROCESS("返回办公室外事办送审人"),
    /**
     * ======================================参团单位审核======================================
     */
    CT_HQ_PROCESS("参团单位审核"),
    BACK_CT_ZHK_PROCESS("返回综合部门"),
    BACK_CT_HQ_PROCESS("参团单位审核反馈"),
    /**
     * ======================================内部会签======================================
     */
    ZNBM_HQ_PROCESS("内部会签"),
    BACK_ZNBM_ZHK_PROCESS("返回综合部门"),
    BACK_ZNBM_HQ_PROCESS("会签反馈"),
    /**
     * ======================================总关关领导审核（总关办公室外事办）======================================
     */
    BACK_WSB_PROCESS("返回总关办公室"),
    /***
     * ======================================职能部门审核======================================
     */
    RS_HQ_PROCESS("职能部门审核"),
    BACK_RS_KS_PROCESS("返回职能部门"),
    BACK_RS_HQ_PROCESS("职能部门审核反馈"),
    /**
     * ======================================总关办公室负责人审核======================================
     */
    BACK_WSB_ZR_PROCESS("返回总关办公室"),
    BACK_WSB_KS_PROCESS("返回总关办公室外事办"),
    END_PROCESS("办结");
    // endregion

    private final String desc;

    private ParamConfigEnum(String desc) {
        this.desc = desc;
    }

}
