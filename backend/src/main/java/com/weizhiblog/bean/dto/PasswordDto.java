package com.weizhiblog.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *
 * @createTime 08-06 8:54:49
 * @author Touka_
 * @classname com.weizhiblog.bean.dto.PasswordDto
 * @lastModifiedTime 8月6日   8:54:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
    private Integer id;
    private String oldPwd;
    private String newPwd;
}
