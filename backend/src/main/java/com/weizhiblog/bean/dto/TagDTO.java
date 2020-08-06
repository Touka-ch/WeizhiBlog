package com.weizhiblog.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 *
 * @createTime 08-05 13:23:6
 * @author Touka_
 * @classname com.weizhiblog.bean.dto.TagDTO
 * @lastModifiedTime 8月5日   13:23:6
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TagDTO {
    private String tag;
    private List<String> tags;
}
