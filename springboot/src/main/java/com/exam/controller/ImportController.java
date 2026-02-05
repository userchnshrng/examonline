package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.service.StudentImportService;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件导入Controller
 * 
 * 核心知识点：
 * 1. @RestController = @Controller + @ResponseBody
 *    表示这个类的所有方法返回值都会转成JSON
 * 
 * 2. @PostMapping：处理POST请求（上传文件必须用POST）
 * 
 * 3. @RequestParam：从请求参数中获取值
 *    - "file" 是前端上传时的字段名（必须匹配）
 *    - required = true 表示这个参数必须传
 * 
 * 4. MultipartFile：SpringMVC提供的文件类型
 *    - getOriginalFilename()：获取原始文件名
 *    - getSize()：获取文件大小
 *    - getInputStream()：获取文件流
 */
@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private StudentImportService studentImportService;

    /**
     * 批量导入学生
     * 前端访问：POST http://localhost:8080/import/students
     * 
     * @param file Excel文件（前端上传时字段名必须是"file"）
     * @return API响应结果
     */
    @PostMapping("/students")
    public ApiResult importStudents(@RequestParam("file") MultipartFile file) {
        try {
            // 1. 校验文件是否为空
            if (file == null || file.isEmpty()) {
                return ApiResultHandler.buildApiResult(400, "文件不能为空", null);
            }
            
            // 2. 校验文件格式（只允许Excel）
            String filename = file.getOriginalFilename();
            if (filename == null || (!filename.endsWith(".xlsx") && !filename.endsWith(".xls"))) {
                return ApiResultHandler.buildApiResult(400, "文件格式错误，请上传Excel文件", null);
            }
            
            // 3. 调用Service处理业务逻辑
            int count = studentImportService.importStudents(file);
            
            // 4. 返回成功结果
            return ApiResultHandler.buildApiResult(200, "成功导入" + count + "名学生", count);
            
        } catch (Exception e) {
            // 异常处理：打印日志并返回错误信息
            e.printStackTrace();
            return ApiResultHandler.buildApiResult(500, "导入失败：" + e.getMessage(), null);
        }
    }
    
    /**
     * 下载学生导入模板（可选功能）
     * 让用户先下载一个标准格式的Excel，按照格式填写后再上传
     */
    @GetMapping("/students/template")
    public ApiResult downloadTemplate() {
        // 这里可以生成一个空的Excel模板供下载
        // 暂时返回提示信息
        return ApiResultHandler.buildApiResult(200, 
            "模板格式：学号|姓名|年级|专业|班级|学院|电话|邮箱|性别", null);
    }
}
