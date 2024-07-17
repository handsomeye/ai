package com.xiaqing.ai.model;

import lombok.Data;

/**
 *@Author: YeZhiKe
 *@CreateTime: 2024-07-12
 *@Description:
 */
@Data
public class AiDialogue {
    private Integer id;
    private String dialogueId;
    private String reqContent;
    private String respContent;
    private String ip;
    private String operNm;
    private String createTime;
    private String jsonReq;
    private String jsonResp;
}
