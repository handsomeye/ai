<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection"
          content="telephone=no,email=no,date=no,address=no">
    <title>小晴ai助手</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/mobileWeb/lib/aui-20170109-v2.1/css/aui.css"/>
    <link rel="icon" href="../favicon.ico">
    <!--浏览器title的图标-->
    <%--    <link rel="stylesheet" type="text/css"--%>
    <%--          href="<%=request.getContextPath()%>/plugins/layui-v2/css/layui.css"/>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/mobileWeb/lib/aui-20170109-v2.1/script/api.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/mobileWeb/lib/aui-20170109-v2.1/script/aui-toast.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/mobileWeb/lib/aui-20170109-v2.1/script/aui-dialog.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.6.0/styles/default.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.6.0/highlight.min.js"></script>
    <%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/highlight/default.min.css">--%>
    <%--    <script src="<%=request.getContextPath()%>/js/highlight.min.js"></script>--%>
    <style type="text/css">
        ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
            padding-left: 8px;
        }

        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            padding-left: 8px;
        }

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            padding-left: 8px;
        }

        :-ms-input-placeholder { /* Internet Explorer 10-11 */
            padding-left: 8px;
        }

        .aui-chat .aui-chat-left .aui-chat-arrow {
            transform: rotate(45deg);
            left: -0.25rem;
            background-image: none !important;
        }

        .aui-chat .aui-chat-right .aui-chat-arrow {
            transform: rotate(-135deg);
            right: -0.25rem;
            background-image: none !important;
        }

        .aui-chat-content {
            max-width: 100% !important;
        }

        .aui-chat-inner {
            min-width: 20%;
        }

        body {
            font-family: Arial, Verdana, Sans-serif;
        }

        .send-button:hover {
            background-color: #23527c;
        }

        .code-header {
            position: absolute;
            top: 12px;
            right: 0;
            width: 100%;
            padding: 0 1rem;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            color: #b3b3b3;
        }

        .code-header-lang {
            margin: 0px 15px;
        }

        .code-header-copy:hover {
            color: #65a665;
        }

        .selectable {
            -webkit-touch-callout: initial;
            -webkit-user-select: text;
            -moz-user-select: text;
            -ms-user-select: text;
            user-select: text;
        }

        .aui-btn-info {
            background-color: #3D7EFF !important;
            box-shadow: 2px 3px 5px #3D7EFF;

        }

        .aui-btn-info:hover {
            box-shadow: 3px 4px 6px #3D7EFF;
        }

        .copy-style {
            font-size: 10px;
            /* width: 50px; */
            position: absolute;
            right: 5px;
            top: 9%;
            /* background-color: #FFFFFF; */
            background: rgb(246 246 247);
            border: none;
            padding-right: 0;
        }

        .img-style {
            position: absolute;
            right: 26px;
            top: 10%;
            width: 20px;
            height: 20px;
            z-index: 9;
        }

        .aui-chat .aui-chat-media img {
            width: 100%;
            border-radius: 0% !important;
        }
        .answer-aciton-divider{
            width: 200%;
            border-top: 1px solid #dadfec;
            margin: 10px 0;
            transform: scale(.5) translateX(-50%);
        }
        .icon-style{
            width: 18px;
            height: 18px;
        }
        .btn-collect{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
        .left-location{
            display: flex;
            align-items: center;
        }
        .right-location{
            display: flex;
            align-items: center;
        }
        .marginLeft5{
            margin-left: 5px;
            font-size: 16px;
        }
        .first-icon{
            margin-right: 20px;
        }
        .second-icon{
            margin-right: 20px;
        }
        pre{
            margin-top: 0px!important;

        }
        code{
            overflow-x:scroll
        }
        .footer {
            position: fixed; /* 使用固定定位 */
            bottom: 0; /* 将底部边缘与视窗底部对齐 */
            left: 0; /* （可选）若需左侧贴边，添加此行 */
            right: 0; /* （可选）若需右侧贴边，添加此行 */
            padding: 10px; /* 添加内边距以适应内容 */
            text-align: center; /* 示例文本居中对齐 */
            /*z-index: 9;*/
            height: 20%;
            min-height: 34%;
            padding: 0
        }
        .first-section{
            position: fixed;
            top: 0;
            height: 65%;
            overflow-y: scroll;
        }
        .copy-right{
            color: #808080;
            text-align: center;
            height: 26px; line-height: 12px;font-size: 12px
        }
        .body-class{
            margin: 0px;
            height: auto;
            background: rgb(246 246 247);
            overflow: scroll;
        }
        /*background: #f1eeee*/
        .textarea-class{
            padding:10px;
            font-size:14px;
            height: 120px;
            width: 90%;
            margin-left: 4%;
            /*border: 1px dashed #c2c2c2;*/
            background: #FFFFFF;
            border-radius: 8px;
        }
        .textarea-class:hover{
            box-shadow:0 4px 20px 0 rgba(23,53,80,.1)
        }
        .textarea-class:focus{
            box-shadow:0 4px 20px 0 rgba(23,53,80,.1)
        }

        .code-class{
            padding:25px 10px 0px 10px !important;
        }
        .pre-class{
            position: relative;
            /*display: table;*/
            overflow-x: scroll;
        }
    </style>

</head>
<body  class="body-class" >

<section class="aui-chat first-section" id="chatContent">
    <div class="aui-chat-item aui-chat-left" style="margin-top: 15px;">
        <div class="aui-chat-media">
            <img src="image/intelligence/intelligence2.png"/>
        </div>
        <div class="aui-chat-inner" style="max-width:75%;padding-right: 10px">
            <div class="aui-chat-content" style="background-color: #FFFFFF;">
                <div class="aui-chat-arrow" style="background-color: #FFFFFF;margin-top: 5px;"></div>
                <span class="selectable">您好，我是小晴ai助手，有什么可以帮助您的吗？</span>
            </div>
        </div>
    </div>


</section>
<section class="footer">
	<textarea placeholder="请输入您的问题"
              style=""
              class="textarea-class"
              id="questionContent"></textarea>
    <div class="aui-btn aui-btn-info aui-btn-block send-button"
         style="margin-top: 15px;width: 90%;margin-left: 4%;" onclick="btnSubmit()">发送
    </div>
    <div class="aui-slide-wrap" style="margin-top: 20px;">
        <div class="copy-right">
            Copyright © 2024 YeZhiKe. All rights reserved<br/>
        </div>
    </div>
</section>
</body>
<script type="text/javascript">
    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
</script>
<script type="text/javascript">
    apiready = function () {
        api.parseTapmode();
    }
    var toast = new auiToast();
    var dialog = new auiDialog();

    // 创建一个div设置遮罩的效果，添加aui遮罩的样式
    var tostdiv = document.createElement("div");

    let imgUrl = "image/intelligence/user.png";

    if ('${wechatAvatar}') {
        imgUrl = '${wechatAvatar}'
    }

    var userId = "";

    let map = new Map();
    //对话列表
    let arr = new Array();

    //保存numId 和 发送消息的关联
    let numMap = new Map();

    //保存numid 和 回答内容的关联
    let contentMap = new Map();

    let dom = document.getElementById('chatContent');

    //渲染页面
    function renderPage(resp) {

    }
    let numId = 0;


    function generateRecords(questionContent) {
        let html = "";
        numId++;
        html += `<div class="aui-chat-item aui-chat-right selectable">
                        <div class="aui-chat-media">
                            <img src="image/intelligence/user.png"/>
                        </div>
                        <div class="aui-chat-inner" style="max-width:75%;padding-left: 10px">

                            <div class="aui-chat-content" style="background-color: #b3e5fc;">
                                <div class="aui-chat-arrow" style="background-color: #b3e5fc;margin-top: 5px;"></div>
                                <span class="selectable">` + questionContent + `</span>
                            </div>
                        </div>

                   </div>`;

        html += `   <div class="aui-chat-item aui-chat-left selectable">
                            <div class="aui-chat-media">
                                <img src="image/intelligence/intelligence2.png"/>
                            </div>
                            <div class="aui-chat-inner" style="max-width:75%;padding-right: 10px">
                                <div class="aui-chat-content" style="background-color: #FFFFFF;">
                                    <div class="aui-chat-arrow" style="background-color: #FFFFFF;margin-top: 5px;"></div>
                                    <span style="margin-bottom: 10px" class="selectable" id="span`+numId+`"><img src="image/intelligence/loading4.gif" width="50px"></span>
                                    <div class="answer-aciton-divider"></div>
                                    <div class="btn-collect" id="collect`+numId+`">

                                    </div>
                                </div>
                            </div>
                    </div>`;


        $('#chatContent').append(html);
        numMap.set(numId, questionContent);

        $('#questionContent').val("");

        //滚动到底部
        // let dom = document.getElementById('chatContent');
        // console.log("dom.scrollHeight:",dom.scrollHeight)
        dom.scrollTo({
            top: dom.scrollHeight,
            behavior: 'smooth' // 可选，添加平滑滚动效果
        });

        setTimeout(function () {
            ajaxSubmit(questionContent);
        }, 100);


    }

    //复制代码
    function copyCode(id) {
        let num = id.split("btn")[1];
        let codeText = map.get("code" + num);

        copyFn(codeText)
    }

    //复制公有函数
    function copyFn(codeText){
        toast.success({
            title: "复制成功！",
            duration: 800
        });

        let input = document.createElement('textarea')
        input.style.position = 'fixed'
        input.style.top = '-10000px'
        input.style.zIndex = '-999'
        document.body.appendChild(input)
        input.value = codeText
        input.focus()
        input.select()
        try {
            let result = document.execCommand('copy')
            document.body.removeChild(input)
            if (!result || result === 'unsuccessful') {
                return Promise.reject('复制失败')
            } else {
                return Promise.resolve()
            }
        } catch (e) {
            document.body.removeChild(input)
            return Promise.reject(
                '当前浏览器不支持复制功能，请检查更新或更换其他浏览器操作'
            )
        }
    }

    async function ajaxSubmit(questionContent) {


        let obj = {content: questionContent, role: 'user'}
        arr.push(obj);

        let req = {
            "messages": arr,
            "model":"Baichuan2-Turbo",
            "temperature": 0.7,
            "top_p": 0.9,
            "top_k":10,
            "stream": true,
            "max_tokens": 2048
        }

        const url = '<%=request.getContextPath()%>/intelligence/doLsQuestion_mobile'
        const resp = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(req)
        })
        const reader = resp.body.getReader()
        const decoder = new TextDecoder()

        let html2 = '';
        let html = '';
        let content='';
        let number = 0;
        let codeFlag = false;
        let code = "";
        let codeHtml="";
        while (1) {
            number++;
            const {done, value} = await reader.read()
            if (done) {
                break;
            }
            let txt = decoder.decode(value)
            //txt就是一个一个的字 然后添加到页面上就可以了
            console.log("txt:", txt)
            // txt = txt.replaceAll("**","").replaceAll("<","&#60;").replaceAll("\n\n","<br/>")
            content+=txt;
            if (txt.indexOf("```") != -1) {
                codeFlag = !codeFlag;
                let split = txt.split("```");
                if(codeFlag){
                    //将原始代码存在map中，复制代码时将用到
                    code = code +split[1];
                    map.set("code"+number,code)

                    let formatStr = split[1].replaceAll("<","&#60;");
                    codeHtml = codeHtml + formatStr;

                    html2 = html+split[0];

                    let html3 =       `<pre class="pre-class">
                                            <img class="img-style" style="display: none" src="image/intelligence/copy.png" id="btn` + number + `" onclick="copyCode(this.id)"/>
                                            <button class="copy-style" style="display: none" id="btn` + number + `"  onclick="copyCode(this.id)">复制</button>
                                        <code id="code` + number + `" class="selectable language-java code-class">`+codeHtml+`</code>
                                  </pre>`;
                    html=html2+html3;

                    $('#span'+numId).html(html);

                    hljs.highlightAll();
                    continue;
                }else{
                    code = code +split[0];
                    map.set("code"+number,code)

                    let formatStr = split[0].replaceAll("<","&#60;");
                    codeHtml = codeHtml + formatStr;
                    let html3 =       `<pre class="pre-class">
                                            <img class="img-style" src="image/intelligence/copy.png" id="btn` + number + `" onclick="copyCode(this.id)"/>
                                            <button class="copy-style" id="btn` + number + `"  onclick="copyCode(this.id)">复制</button>
                                        <code id="code` + number + `" class="selectable language-java code-class">`+codeHtml+`</code>
                                  </pre>`;
                    html = html2+html3+split[1];
                    $('#span'+numId).html(html);
                    hljs.highlightAll();
                    continue;
                }
            }
            if(codeFlag){
                //将原始代码存在map中，复制代码时将用到
                code = code + txt;
                map.set("code"+number,code)
                let formatStr = txt.replaceAll("<","&#60;")
                codeHtml = codeHtml + formatStr;
                let html3 =       `<pre class="pre-class">
                                                <img class="img-style" style="display: none" src="image/intelligence/copy.png" id="btn` + number + `" onclick="copyCode(this.id)"/>
                                                <button class="copy-style" style="display: none" id="btn` + number + `"  onclick="copyCode(this.id)">复制</button>
                                            <code id="code` + number + `" class="selectable language-java code-class">`+codeHtml+`</code>
                                  </pre>`;
                html=html2+html3;

                $('#span'+numId).html(html);

                hljs.highlightAll();
            }else{
                txt = txt.replaceAll("**","").replaceAll("<","&#60;").replaceAll("\n\n","<br/>")
                html += txt;
                contentMap.set(numId,content)
                $('#span'+numId).html(html);
                hljs.highlightAll();
            }

            //滚动到底部
            // let dom = document.getElementById('chatContent');
            // console.log("dom.scrollHeight:",dom.scrollHeight)
            dom.scrollTo({
                top: dom.scrollHeight,
                behavior: 'smooth' // 可选，添加平滑滚动效果
            });
        }
        let collectHtml =` <div class = "left-location" onclick="resend(`+numId+`)">
                                    <img class="icon-style" src="image/intelligence/reflesh.png"/>
                                    <span class="marginLeft5">重新生成</span>
                            </div>
                            <div class = "right-location">

                                    <div class="first-icon" onclick="copyAll(`+numId+`)"><img class="icon-style" src="image/intelligence/copy.png"/></div>
                                    <div class="second-icon"><img class="icon-style" src="image/intelligence/good1.png"/></div>
                                    <div class="third-icon"><img class="icon-style" src="image/intelligence/low1.png"/></div>
                           </div>`
        $('#collect'+numId).html(collectHtml);
        let respMes ={};
        respMes.role='assistant';
        respMes.content=content;
        arr.push(respMes);

        console.log("html:",html)
        // $('#span'+numId).html(html);
        dom.scrollTo({
            top: dom.scrollHeight,
            behavior: 'smooth' // 可选，添加平滑滚动效果
        });

    }

    //重新生成
    function resend(nid){
        let imgHtml = `<img src="image/intelligence/loading4.gif" width="50px">`
        $('#span'+nid).html(imgHtml)
        let queryContent = numMap.get(nid);
        ajaxSubmit(queryContent);

    }
    //复制全部
    function copyAll(numVal){
        let content = contentMap.get(numVal);
        copyFn(content)
    }

    //提交回复
    function btnSubmit() {
        let questionContent = $('#questionContent').val();
        if (!questionContent.trim()) {
            dialog.alert({
                title: "提示",
                msg: '输入的内容不能为空',
                buttons: ['确定']
            }, function (ret) {
            });
            return false;
        }

        generateRecords(questionContent);

    }

    document.addEventListener("keyup", function (event) {
        // 判断按下的键是不是 Enter 键（keyCode：13）
        if (event.keyCode === 13) {
            btnSubmit();
        }
    });
</script>
</html>


