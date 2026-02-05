package com.exam.vo;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 学生导入Excel模板
 * 这个类定义了Excel文件的结构：每一列对应一个字段
 * @ExcelProperty 的 index 参数表示Excel列的顺序（从0开始）
 */
public class StudentImportVO {

    @ExcelProperty(value = "学号", index = 0)
    private Integer studentId;

    @ExcelProperty(value = "姓名", index = 1)
    private String studentName;

    @ExcelProperty(value = "年级", index = 2)
    private String grade;

    @ExcelProperty(value = "专业", index = 3)
    private String major;

    @ExcelProperty(value = "班级", index = 4)
    private String clazz;

    @ExcelProperty(value = "学院", index = 5)
    private String institute;

    @ExcelProperty(value = "电话", index = 6)
    private String tel;

    @ExcelProperty(value = "邮箱", index = 7)
    private String email;

    @ExcelProperty(value = "性别", index = 8)
    private String sex;

    // Getter 和 Setter（必须要有，EasyExcel通过它们来填充数据）
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
