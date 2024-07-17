package com.xiaqing.ai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xiaqing.ai.constant.IntelligenceConstant;
import com.xiaqing.ai.model.Intelligence;
import com.xiaqing.ai.model.Json;
import com.xiaqing.ai.model.ReqParam;
import com.xiaqing.ai.service.IIntelligenceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("intelligence")
public class IntelligenceController {

    private Logger logger = Logger.getLogger(IntelligenceController.class);

    @Autowired
    private IIntelligenceService intelligenceService;

    /**
     * @param req
     * @return {@link Json}
     * @throws
     * @description 流式接口访问百川Api，提问
     * @author YeZhiKe
     * @date 2024/4/18 11:16
     */
    @RequestMapping("doLsQuestion_mobile")
    public void doLsQuestion(@RequestBody ReqParam req, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(!verifyParame(req)){
            return;
        }

        // 获取请求是从哪里来的
        String referer = request.getHeader("referer");
        //todo 生产上打开
        // 如果是直接输入的地址，或者不是从本网站访问的重定向到本网站的首页
        if (referer == null || !referer.startsWith("https://www.yezhike.cn")) {
            String responseMsg = "请勿盗刷接口，你访问的是盗版网站，请访问https://www.yezhike.cn";
            response.getOutputStream().write(responseMsg.getBytes(StandardCharsets.UTF_8));
            return;
        }

        try {
            //处理问题
            intelligenceService.doLsQuestion(req,request, response);
        }catch (JsonProcessingException e) {
            logger.error("解析请求报文错误:", e);
        }catch (IOException e){
            logger.error("IOException:", e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * @description 校验用户提交参数
     * @param req
     * @return
     * @throws
     * @author YeZhiKe
     * @date 2024/4/23 11:28
     */
    private boolean verifyParame(ReqParam req) {
        List<Intelligence> messages = req.getMessages();
        for(Intelligence intelligence:messages){
            String role = intelligence.getRole();
            //如果是用户提问，要校验用户的提交的内容
            if("user".equals(role)){
                String content = intelligence.getContent();
                if(StringUtils.isEmpty(content)){
                    logger.error("用户提交的内容为空:");
                    return false;
                }
            }
        }
        String model = req.getModel();
        if(StringUtils.isEmpty(model)){
            logger.error("用户提交的model为空:");
            //设置一个默认的模型
            req.setModel(IntelligenceConstant.MODEL_BAICHUAN2_TURBO);
        }
        return true;
    }
}
