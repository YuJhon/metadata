package com.ryo.metadata.web.dto.response;


import com.ryo.metadata.web.dto.BaseDto;

/**
 * Created by bbhou on 2017/9/13.
 */
public class BaseResponse extends BaseDto {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 错误编码
     */
    private String errCode;

    /**
     * 错误消息
     */
    private String errMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
