package com.xiaqing.ai.model;

/**
 * @author Yezhike
 * @Description 调用人工智能的请求空对象
 * @Date 2023/6/2 16:51
 */

public class IntelligenceReqJson {
    private String prompt;
    private OptionsDTO options;
    private String userId;
    private Boolean usingContext;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public OptionsDTO getOptions() {
        return options;
    }

    public void setOptions(OptionsDTO options) {
        this.options = options;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getUsingContext() {
        return usingContext;
    }

    public void setUsingContext(Boolean usingContext) {
        this.usingContext = usingContext;
    }
}
