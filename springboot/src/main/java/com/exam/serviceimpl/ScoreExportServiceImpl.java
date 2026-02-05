package com.exam.serviceimpl;

import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreExportService;
import com.exam.vo.ScoreExcelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service层：业务逻辑层
 * 这里加 @Service 注解，是为了告诉 SpringBoot：“我是个干活的，把我管理起来（IOC容器）”
 * 当别的地方需要用我的时候，你直接通过 @Autowired 帮我自动注入进去。
 */
@Service
public class ScoreExportServiceImpl implements ScoreExportService {

    // 注入 Mapper（数据层），因为数据都在数据库里，得找 Mapper 要
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<ScoreExcelVO> getExportData(Integer examCode) {
        // 调用 Mapper 里刚才写好的那个多表查询 SQL
        return scoreMapper.findByExamCodeForExport(examCode);
    }
}
