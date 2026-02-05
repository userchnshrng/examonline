package com.exam.vo;

import com.alibaba.excel.annotation.ExcelProperty;

public class ScoreExcelVO {

    // @ExcelProperty 用来定义 Excel 的表头名字，index 是列的顺序
    @ExcelProperty(value = "学号", index = 0)
    private Integer studentId;

    @ExcelProperty(value = "姓名", index = 1)
    private String studentName;

    @ExcelProperty(value = "学院", index = 2)
    private String institute;

    @ExcelProperty(value = "专业", index = 3)
    private String major;

    @ExcelProperty(value = "班级", index = 4)
    private String clazz;

    @ExcelProperty(value = "考试成绩", index = 5)
    private Integer etScore;

    // 记得生成 Getter 和 Setter (或者你自己手动写，如下)
    // 如果你用了 Lombok 插件，也可以只加 @Data 注解，但为了教学我们手写
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getInstitute() { return institute; }
    public void setInstitute(String institute) { this.institute = institute; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getClazz() { return clazz; }
    public void setClazz(String clazz) { this.clazz = clazz; }

    public Integer getEtScore() { return etScore; }
    public void setEtScore(Integer etScore) { this.etScore = etScore; }
}