package org.mengchong.mcfw.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> data=new ArrayList<>();


    /**
     * 读取excel内容，从第二行开始读取，把每行读取内容封装到T对象里面
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        data.add(t);
    }

    public List<T> getData(){
        return data;
    }


    /**
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
