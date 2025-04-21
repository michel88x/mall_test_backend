package com.michel.mall_test.extra.dto;

public class BaseResponse {
    private boolean success;
    private String message;
    private Object data;

    public BaseResponse() {}

    public BaseResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static final class BaseResponseBuilder {
        private boolean success;
        private String message;
        private Object data;

        private BaseResponseBuilder() {
        }

        public static BaseResponseBuilder aBaseResponse() {
            return new BaseResponseBuilder();
        }

        public BaseResponseBuilder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public BaseResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public BaseResponseBuilder withData(Object data) {
            this.data = data;
            return this;
        }

        public BaseResponse build() {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setSuccess(success);
            baseResponse.setMessage(message);
            baseResponse.setData(data);
            return baseResponse;
        }
    }
}
