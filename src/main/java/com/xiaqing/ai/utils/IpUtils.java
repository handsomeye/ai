package com.xiaqing.ai.utils;/**
 * @Description TODO
 * @author Yezhike
 * @Date 2024/7/12 13:51
 */

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author: YeZhiKe
 *@CreateTime: 2024-07-12
 *@Description:
 */
public class IpUtils {

        public static String getRemoteHost(HttpServletRequest request){
            String ip = request.getHeader("x-forwarded-for");
            if(!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)){
                int idx = ip.indexOf(",");
                if(idx != -1){
                    ip = ip.substring(0, idx);
                }
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }
            return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
        }


}
