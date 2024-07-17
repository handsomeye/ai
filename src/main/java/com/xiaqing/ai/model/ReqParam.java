package com.xiaqing.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yezhike
 * @Description TODO
 * @Date 2024/4/23 11:00
 */
@NoArgsConstructor
@Data
public class ReqParam {

    @JsonProperty("dialogueId")
    private String dialogueId;

    @JsonProperty("messages")
    private List<Intelligence> messages;
    @JsonProperty("model")
    private String model;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    @JsonProperty("top_k")
    private Integer topK;
    @JsonProperty("stream")
    private Boolean stream;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
}
