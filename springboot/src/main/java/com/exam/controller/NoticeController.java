package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Notice;
import com.exam.serviceimpl.NoticeServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    // 获取所有公告（不分页，后续可以用于简单展示）
    @GetMapping("/notices")
    public ApiResult findAll() {
        return ApiResultHandler.buildApiResult(200, "查询所有公告", noticeService.findAll());
    }

    // 分页获取公告（每页显示几条）
    @GetMapping("/notices/{page}/{size}")
    public ApiResult findAllPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Notice> noticePage = new Page<>(page, size);
        IPage<Notice> res = noticeService.findAllPage(noticePage);
        return ApiResultHandler.buildApiResult(200, "分页查询公告", res);
    }

    // 发布新公告
    @PostMapping("/notice")
    public ApiResult add(@RequestBody Notice notice) {
        int res = noticeService.add(notice);
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200, "公告发布成功", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "发布失败", res);
        }
    }

    // 修改公告
    @PutMapping("/notice")
    public ApiResult update(@RequestBody Notice notice) {
        int res = noticeService.update(notice);
        return ApiResultHandler.buildApiResult(200, "更新成功", res);
    }

    // 删除公告
    @DeleteMapping("/notice/{noticeId}")
    public ApiResult delete(@PathVariable("noticeId") Integer noticeId) {
        int res = noticeService.delete(noticeId);
        return ApiResultHandler.buildApiResult(200, "删除成功", res);
    }
}
