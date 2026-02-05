package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 查询所有公告
    @Select("select * from notice")
    List<Notice> findAll();

    // 分页查询公告（如果公告很多，不能一次全查出来）
    @Select("select * from notice order by noticeId desc")
    IPage<Notice> findAllPage(Page<Notice> page);

    // 添加一条公告
    // useGeneratedKeys=true 表示让数据库自动生成ID，并回填到对象里
    @Options(useGeneratedKeys = true, keyProperty = "noticeId")
    @Insert("insert into notice(content, createTime) values(#{content}, #{createTime})")
    int add(Notice notice);

    // 删除一条公告
    @Delete("delete from notice where noticeId = #{noticeId}")
    int delete(Integer noticeId);

    // 修改公告
    @Update("update notice set content = #{content} where noticeId = #{noticeId}")
    int update(Notice notice);
}