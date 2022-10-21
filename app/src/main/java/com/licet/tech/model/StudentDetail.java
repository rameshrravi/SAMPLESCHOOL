package com.licet.tech.model;

public class StudentDetail {
    int code;
    String response;
    StudentData data;

    public StudentData getData() {
        return data;
    }

    public void setData(StudentData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
