package com.xxxx.server.config.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.server.pojo.ResBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 未登录或者token失效时访问接口时，自定义的返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("RestAuthorizationEntryPoint");
//        Map<String,String> param= new HashMap<>();
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while(parameterNames.hasMoreElements()){
//            String paramName = parameterNames.nextElement();
//            String[] values = request.getParameterValues(paramName);
//            if (values.length == 1){
//                if (values[0].length() != 0){
//                    param.put(paramName,values[0]);
//                }
//            }
//        }
//        for (Map.Entry<String,String> entry: param.entrySet()){
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ResBean bean = ResBean.error("尚未登陆，请登录");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
