package com.exam.service;

import com.exam.vo.ScoreExcelVO;

import java.util.List;

public interface ScoreExportService {
    // 定义导出接口：给我一个考试ID，我把这门考试所有人的信息查出来给你
    List<ScoreExcelVO> getExportData(Integer examCode);
}
