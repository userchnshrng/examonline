package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Notice;

import java.util.List;

public interface NoticeService {

    List<Notice> findAll();

    IPage<Notice> findAllPage(Page<Notice> page);

    int add(Notice notice);

    int delete(Integer noticeId);

    int update(Notice notice);
}
