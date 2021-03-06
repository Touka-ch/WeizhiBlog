package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @NotNull
    private Integer id;


    private String title;

    /**
     * md文件源码
     */
    private String mdContent;

    /**
     * html源码
     */
    private String htmlContent;

    private String summary;

    private Integer cid;

    private Integer uid;

    private Timestamp publishDate;

    private Timestamp editTime;

    private Integer commentNum;

    private Integer likeNum;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    private Integer pageView;

    private Boolean publicToOthers;
    
    List<Tag> tags;
    List<Map<String,Object>> comments;
}