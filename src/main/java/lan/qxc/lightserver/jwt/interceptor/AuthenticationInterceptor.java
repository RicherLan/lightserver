package lan.qxc.lightserver.jwt.interceptor;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import lan.qxc.lightserver.common.StatusCode;
import lan.qxc.lightserver.entity.User;
import lan.qxc.lightserver.jwt.JWTUtils;
import lan.qxc.lightserver.jwt.annotation.CheckToken;
import lan.qxc.lightserver.jwt.annotation.PassToken;
import lan.qxc.lightserver.util.Result;
import lan.qxc.lightserver.util.ResultGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        //拦截的如果不是方法   直接放行
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }

//        HandlerMethod handlerMethod = (HandlerMethod)handler;
//        Method method = handlerMethod.getMethod();


        //放行passToken标记的方法
//        if(method.isAnnotationPresent(PassToken.class)){
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if(passToken.required()){
//                return true;
//            }
//        }

//        if(method.isAnnotationPresent(CheckToken.class)){
//            CheckToken checkToken = method.getAnnotation(CheckToken.class);
//            if(checkToken.required()){
//
//                if(!JWTUtils.verifyToken(token)){
//                    response.setStatus(StatusCode.TOKEN_ERROR);
//                    return false;
//                }
//
//            }
//        }

        if(!JWTUtils.verifyToken(token)){
            System.out.println("拦截.......");
            returnJson(response);
            return false;
        }

        return true;
    }


    private void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {

            writer = response.getWriter();

            //我的客户端是retrofit的   onResponse(Call<Result> call, Response<Result> response)
            //                        onFailure(Call<Result> call, Throwable t)
            //这块地方不能直接返回result  要把result显示的转化为json格式   要不然客户端那边会走onFailure的
            //这个东西如果放在controller中会自动转json的
            //这个bug弄了我半个小时啊啊啊啊啊啊啊

            Result result = (ResultGenerator.genErrorResult(StatusCode.TOKEN_ERROR,"token error"));
            Gson gson = new Gson();
            String str = gson.toJson(result);

            writer.print(str);
            writer.flush();
            gson = null;
//            System.out.println("已发送拦截信息给客户端...");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }


}
