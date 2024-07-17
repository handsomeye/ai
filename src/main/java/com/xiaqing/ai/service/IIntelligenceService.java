package com.xiaqing.ai.service;



import com.xiaqing.ai.model.Intelligence;
import com.xiaqing.ai.model.ReqParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description 人工智能服务接口
 * @author YeZhiKe
 * @date 2023/6/16 16:33
 */
public interface IIntelligenceService {

    /**
     * @description 流式接口
     * @param req
     * @author YeZhiKe
     * @date 2024/4/18 11:21
     */
    void doLsQuestion(ReqParam req, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
