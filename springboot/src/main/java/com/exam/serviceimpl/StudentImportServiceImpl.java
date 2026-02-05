package com.exam.serviceimpl;

import com.alibaba.excel.EasyExcel;
import com.exam.entity.Student;
import com.exam.mapper.StudentMapper;
import com.exam.service.StudentImportService;
import com.exam.vo.StudentImportVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生导入服务实现类
 * 
 * 核心知识点：
 * 1. @Service：告诉SpringBoot这是业务逻辑层，会被IOC容器管理
 * 2. @Autowired：依赖注入，自动装配Mapper
 * 3. MultipartFile：SpringMVC提供的文件上传类型
 * 4. InputStream：Java IO流，用于读取文件内容
 */
@Service
public class StudentImportServiceImpl implements StudentImportService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int importStudents(MultipartFile file) throws Exception {
        // 1. 获取文件输入流（IO流知识点）
        InputStream inputStream = file.getInputStream();
        
        // 2. 使用EasyExcel读取Excel，并转换为List<StudentImportVO>
        // read()：读取文件
        // head()：指定Excel对应的Java类
        // sheet()：读取第一个工作表
        // doReadSync()：同步读取，返回List
        List<StudentImportVO> dataList = EasyExcel.read(inputStream)
                .head(StudentImportVO.class)
                .sheet()
                .doReadSync();
        
        // 3. 转换VO为Entity，并设置默认值
        List<Student> studentList = new ArrayList<>();
        for (StudentImportVO vo : dataList) {
            Student student = new Student();
            
            // BeanUtils.copyProperties：Spring提供的属性拷贝工具
            // 自动把VO的属性值复制到Entity（字段名相同的会自动匹配）
            BeanUtils.copyProperties(vo, student);
            
            // 设置默认密码（业务逻辑）
            student.setPwd("123456");
            // 设置角色为学生
            student.setRole("2");
            
            studentList.add(student);
        }
        
        // 4. 批量插入数据库（需要Mapper支持批量插入）
        int count = 0;
        for (Student student : studentList) {
            // 这里为了简化，使用循环插入
            // 实际生产环境可以用MyBatis的批量插入提升性能
            try {
                studentMapper.add(student);
                count++;
            } catch (Exception e) {
                // 如果学号重复或其他错误，跳过这条记录
                System.out.println("导入失败：" + student.getStudentName() + "，原因：" + e.getMessage());
            }
        }
        
        return count;
    }
}
