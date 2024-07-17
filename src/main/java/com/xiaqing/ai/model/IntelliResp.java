package com.xiaqing.ai.model;

/**
 * @author Yezhike
 * @Description 返回报文实体类
 * @Date 2023/7/19 17:22
 */

public class IntelliResp {


    private DataDTO data;
    private Integer code;
    private String message;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
