package com.xiaqing.ai.model;

/**
 * @author Yezhike
 * @Description 请求报文实体类
 * @Date 2023/7/19 17:42
 */
public class IntelliReq {


    private String prompt;
    private String conversationId;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
