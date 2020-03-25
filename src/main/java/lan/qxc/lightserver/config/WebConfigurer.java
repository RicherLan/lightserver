package lan.qxc.lightserver.config;


import lan.qxc.lightserver.common.Constants;
import lan.qxc.lightserver.jwt.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(authenticationInterceptor)
//                .addPathPatterns("/user/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(Constants.DONGTAI_IC_FILE_ACCESS_PATH+"**")
                .addResourceLocations("file:"+ Constants.DONGTAI_IC_FILE_UPLOAD_PATH);

        registry.addResourceHandler(Constants.DONGTAI_IC_SL_FILE_ACCESS_PATH+"**")
                .addResourceLocations("file:"+ Constants.DONGTAI_IC_SL_FILE_UPLOAD_PATH);

        registry.addResourceHandler(Constants.HEADIC_FILE_ACCESS_PATH+"**")
                .addResourceLocations("file:"+ Constants.HEADIC_FILE_UPLOAD_PATH);




    }




}
