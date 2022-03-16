package com.zy.wx.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: ZYi
 * @Date: 2021/3/22 9:18
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class InfoDTO {
    // 受理日期
    private String acceptDate;
    // 公证员姓名
    private String name;
    // 卷宗号
    private String dossier;
    // 公证书编号
    private String number;
    // 申请人
    private String applicant;
}
