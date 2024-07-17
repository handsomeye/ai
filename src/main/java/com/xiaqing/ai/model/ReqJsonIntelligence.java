package com.xiaqing.ai.model;

/**
 * @author Yezhike
 * @Description TODO
 * @Date 2023/7/18 14:31
 */
public class ReqJsonIntelligence {


    private String prompt;
    private String userId;
    private Boolean network;
    private String system;
    private Boolean withoutContext;
    private Boolean stream;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getNetwork() {
        return network;
    }

    public void setNetwork(Boolean network) {
        this.network = network;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Boolean getWithoutContext() {
        return withoutContext;
    }

    public void setWithoutContext(Boolean withoutContext) {
        this.withoutContext = withoutContext;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }
}
