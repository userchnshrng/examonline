package com.exam.controller;

import com.alibaba.excel.EasyExcel;
import com.exam.service.ScoreExportService;
import com.exam.vo.ScoreExcelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Controller层：控制层
 * 作用：像餐厅的服务员，专门接待前端（浏览器）发来的请求。
 * @RestController = @Controller + @ResponseBody，意思是这个类里所有方法返回的数据都会直接写给浏览器（通常是JSON），
 * 但我们这里要下载文件，所以会用到 void 返回类型，直接操作输出流。
 */
@RestController
public class ExportController {

    @Autowired
    private ScoreExportService scoreExportService;

    // 前端访问地址： http://localhost:8080/export/score/20190001
    // @GetMapping: 处理 HTTP GET 请求
    // @PathVariable: 把 URL 里的 {examCode} 拿出来赋值给参数里的 examCode
    @GetMapping("/export/score/{examCode}")
    public void export(@PathVariable Integer examCode, HttpServletResponse response) throws IOException {
        
        // 1. 设置响应头 (告诉浏览器：我是个文件，不是网页，请下载我！)
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里 URLEncoder.encode 是为了防止文件名里的中文乱码
        String fileName = URLEncoder.encode("StudentScore-" + examCode, "UTF-8");
        // Content-disposition 是 HTTP 协议里专门用来控制文件下载的头
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 2. 查数据 (找数据员 Service 要数据)
        List<ScoreExcelVO> list = scoreExportService.getExportData(examCode);

        // 3. 写出 Excel (使用 EasyExcel 工具的“一把梭”方法)
        // write(输出流, 写出数据的格式类).sheet("工作表名").doWrite(数据集合)
        EasyExcel.write(response.getOutputStream(), ScoreExcelVO.class)
                .sheet("成绩表")
                .doWrite(list);
    }
}
