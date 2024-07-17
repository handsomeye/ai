package com.xiaqing.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yezhike
 * @Description 人工智能实体类 百川智能 请求类
 * @Date 2024/4/15 17:23
 */

@NoArgsConstructor
@Data
public class BcReq {

    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<MessagesDTO> messages;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    @JsonProperty("top_k")
    private Integer topK;
    @JsonProperty("stream")
    private Boolean stream;
    @JsonProperty("with_search_enhance")
    private Boolean withSearchEnhance;
    @JsonProperty("tools")
    private List<ToolsDTO> tools;

    @NoArgsConstructor
    @Data
    public static class ToolsDTO {
        @JsonProperty("type")
        private String type;
        @JsonProperty("retrieval")
        private RetrievalDTO retrieval;

        @NoArgsConstructor
        @Data
        public static class RetrievalDTO {
            @JsonProperty("kb_ids")
            private List<String> kbIds;
            @JsonProperty("answer_mode")
            private String answerMode;
        }
    }

    @NoArgsConstructor
    @Data
    public static class MessagesDTO {
        @JsonProperty("role")
        private String role;
        @JsonProperty("content")
        private String content;
    }
}
