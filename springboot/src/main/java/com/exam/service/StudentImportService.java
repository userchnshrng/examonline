package com.exam.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 学生导入服务接口
 * MultipartFile 是SpringBoot提供的文件上传类型
 */
public interface StudentImportService {
    /**
     * 批量导入学生
     * @param file Excel文件
     * @return 成功导入的数量
     */
    int importStudents(MultipartFile file) throws Exception;
}
