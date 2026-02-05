package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Notice;
import com.exam.mapper.NoticeMapper;
import com.exam.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }

    @Override
    public IPage<Notice> findAllPage(Page<Notice> page) {
        return noticeMapper.findAllPage(page);
    }

    @Override
    public int add(Notice notice) {
        return noticeMapper.add(notice);
    }

    @Override
    public int delete(Integer noticeId) {
        return noticeMapper.delete(noticeId);
    }

    @Override
    public int update(Notice notice) {
        return noticeMapper.update(notice);
    }
}
