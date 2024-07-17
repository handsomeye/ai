package com.xiaqing.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yezhike
 * @Description 百川流式接口的响应类
 * @Date 2024/4/18 15:36
 */
@NoArgsConstructor
@Data
public class BcLsResp {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("created")
    private Integer created;
    @JsonProperty("model")
    private String model;
    @JsonProperty("choices")
    private List<ChoicesDTO> choices;
    @JsonProperty("usage")
    private UsageDTO usage;
    @JsonProperty("knowledge_base")
    private KnowledgeBaseDTO knowledgeBase;

    @NoArgsConstructor
    @Data
    public static class UsageDTO {
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("completion_tokens")
        private Integer completionTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class KnowledgeBaseDTO {
        @JsonProperty("cites")
        private List<CitesDTO> cites;

        @NoArgsConstructor
        @Data
        public static class CitesDTO {
            @JsonProperty("title")
            private String title;
            @JsonProperty("content")
            private String content;
            @JsonProperty("file_id")
            private String fileId;
            @JsonProperty("chunk_id")
            private String chunkId;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ChoicesDTO {
        @JsonProperty("index")
        private Integer index;
        @JsonProperty("delta")
        private DeltaDTO delta;
        @JsonProperty("finish_reason")
        private String finishReason;

        @NoArgsConstructor
        @Data
        public static class DeltaDTO {
            @JsonProperty("role")
            private String role;
            @JsonProperty("content")
            private String content;
        }
    }
}
