package com.project.teststudent.studentmanger.entitites.response;

public class SuccessResponseObject<T> extends BaseReponse{
    private T response;
    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
