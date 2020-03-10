package lan.qxc.lightserver.util;

import org.springframework.util.StringUtils;

public class ResultGenerator {

    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_ERROR = 500;

    private static final String DEFAULT_MESSAGE_SUCCESS = "SUCCESS";
    private static final String DEFAULT_MESSAGE_FAIL = "FAIL";

    
    public static Result genSuccessResult(){
        Result result= new Result();
        result.setMessage(DEFAULT_MESSAGE_SUCCESS);
        result.setResultCode(RESULT_CODE_SUCCESS);
        return result;
    }

    public static Result genSuccessResult(Object data) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_MESSAGE_SUCCESS);
        result.setData(data);
        return result;
    }
        
    public static Result genSuccessResult(String message){
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        return result;
    }
    
    
    //error  fail
    public static Result genFailResult(String message) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_ERROR);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_MESSAGE_FAIL);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static Result genErrorResult(int code, String message) {
        Result result = new Result();
        result.setResultCode(code);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_MESSAGE_FAIL);
        } else {
            result.setMessage(message);
        }
        return result;
    }
    
    
}
