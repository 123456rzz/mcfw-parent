package org.mengchong.mcfw.manager.service;

import jakarta.servlet.http.HttpServletResponse;
import org.mengchong.mcfw.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    //列表查询
    List<Category> findCategoryList(Long id);

    //导出
    void exportData(HttpServletResponse response);
}
