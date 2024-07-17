package com.xiaqing.ai.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaqing.ai.constant.IntelligenceConstant;
//import com.xiaqing.ai.mapper.AiDialogueMapper;
import com.xiaqing.ai.model.*;
import com.xiaqing.ai.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * @description 人工智能实现类
 * @author YeZhiKe
 * @date 2023/6/16 16:33
 */
@Service
public class IntelligenceServiceImpl implements IIntelligenceService{

    private static final Logger logger = LoggerFactory.getLogger(IntelligenceServiceImpl.class);
//    @Autowired
//    private AiDialogueMapper aiDialogueMapper;
    /**
     * @param req
     * @return {@link null}
     * @throws
     * @description 调用百川的流式接口
     * @author YeZhiKe
     * @date 2024/4/18 11:21
     */
    @Override
    public void doLsQuestion(ReqParam req, HttpServletRequest request,HttpServletResponse response) throws IOException {

        BcReq bcReq = new BcReq();
        bcReq.setModel(req.getModel());
        //设置messages
        List<BcReq.MessagesDTO> list = new ArrayList<>();

        List<Intelligence> messages = req.getMessages();
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }
        Intelligence intelligence1 = messages.get(messages.size() - 1);
        String content = intelligence1.getContent();
        //伪装套壳
        List<String> keyArr = new ArrayList<>();
        keyArr.add("你是\n");
        keyArr.add("你是?\n");
        keyArr.add("你是谁\n");
        keyArr.add("你是谁?\n");
        if(content.contains("模型")||keyArr.contains(content)){
            String responseMsg = "我是叶志科瞎捣鼓的XiaoQing-2B大模型。";
            response.getOutputStream().write(responseMsg.getBytes(StandardCharsets.UTF_8));
            return;
        }
        if(content.contains("#草泥马")){
            //取值范围: [.0f, 1.0f]。 多样性，越高，多样性越好, 缺省 0.3
            bcReq.setTemperature(1.0);

            //取值范围: [.0f, 1.0f)。值越小，越容易出头部, 缺省 0.85
            bcReq.setTopP(1.0);
            content = content.replaceAll("#草泥马", "");
            intelligence1.setContent(content);

        }else if(content.contains("#呵呵")){
            //取值范围: [.0f, 1.0f]。 多样性，越高，多样性越好, 缺省 0.3
            bcReq.setTemperature(0.0);

            //取值范围: [.0f, 1.0f)。值越小，越容易出头部, 缺省 0.85
            bcReq.setTopP(0.0);
            content = content.replaceAll("#呵呵", "");
            intelligence1.setContent(content);
        } else{
            //取值范围: [.0f, 1.0f]。 多样性，越高，多样性越好, 缺省 0.3
            bcReq.setTemperature(req.getTemperature());

            //取值范围: [.0f, 1.0f)。值越小，越容易出头部, 缺省 0.85
            bcReq.setTopP(req.getTopP());
        }
        for (Intelligence intelligence : messages) {
            BcReq.MessagesDTO message = new BcReq.MessagesDTO();
            //消息作者的角色，为以下其中之一
            //1. user
            //2. assistant
            message.setRole(intelligence.getRole());
            //消息内容
            message.setContent(intelligence.getContent());
            list.add(message);
        }
        //对话消息列表 (历史对话按从老到新顺序填入)
        bcReq.setMessages(list);

        //取值范围: [0, 20]。搜索采样控制参数，越大，采样集大, 0 则不走 top_k 采样筛选策略，最大 20(超过 20 会被修正成 20)，缺省 5
        bcReq.setTopK(req.getTopK());

        //设置为流式接口
        bcReq.setStream(true);

        //True: 开启web搜索增强，搜索增强会产生额外的费用, 缺省 False
        bcReq.setWithSearchEnhance(true);


        BcReq.ToolsDTO tools = new BcReq.ToolsDTO();
        //工具类型，目前只支持 retrieval，retrieval工具最多只能有一个
        tools.setType("retrieval");
        List<BcReq.ToolsDTO> toolsList = new ArrayList<>();
        toolsList.add(tools);
        bcReq.setTools(toolsList);

        ObjectMapper mapper = new ObjectMapper();
        String bcReqStr = null;
        bcReqStr = mapper.writeValueAsString(bcReq);
        logger.info("jackson请求报文：" + bcReqStr);

        InputStream inputStream = HttpRequest.post(IntelligenceConstant.BAICHUAN2_URL)
                .header("Content-Type", "application/json")
                .auth(IntelligenceConstant.BAICHUAN2_KEY)
                .body(bcReqStr)
                .execute(true).bodyStream();
        //设置异步访问

        //输出inputStream输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        ServletOutputStream outputStream = null;
        response.setContentType("application/json; charset=UTF-8");
        outputStream = response.getOutputStream();
        StringBuilder responseText = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.equals("data: [DONE]")) {
                //数据库记录下对话信息
                AiDialogue aiDialogue = new AiDialogue();
                aiDialogue.setDialogueId(req.getDialogueId());
                //拿最后一个的内容，是最新的
                BcReq.MessagesDTO messagesDTO = list.get(list.size() - 1);
                aiDialogue.setReqContent(messagesDTO.getContent());
                aiDialogue.setRespContent(responseText.toString());
                String ip = IpUtils.getRemoteHost(request);
                aiDialogue.setIp(ip);
                aiDialogue.setCreateTime(DateUtil.now());
//                aiDialogueMapper.insert(aiDialogue);
                break;
            }
            if (line.startsWith("data: ")) {
                line = line.replace("data: ", "");
                BcLsResp bcLsResp = mapper.readValue(line, BcLsResp.class);
                List<BcLsResp.ChoicesDTO> choices = bcLsResp.getChoices();
                if (!CollectionUtils.isEmpty(choices)) {
                    BcLsResp.ChoicesDTO choicesDTO = choices.get(0);
                    BcLsResp.ChoicesDTO.DeltaDTO delta = choicesDTO.getDelta();
                    if (delta != null) {
                        String content1 = delta.getContent();
                        responseText.append(content1);
                        if (!org.springframework.util.StringUtils.isEmpty(content1)) {
//                            content1 = content1.replaceAll("\n","<br/>");
                            outputStream.write(content1.getBytes(StandardCharsets.UTF_8));
                            // 确保数据发送并刷新输出流
                            outputStream.flush();
                        }
                    }
                }
            }
        }
        outputStream.close();
        reader.close();
    }


}
