package com.blog.myblogsystem.result;

import lombok.Data;

@Data
public class JsonResult<T> {
    private T data;
    private String code;
    private String msg;

    /**
     * 若没有数据返回，默认状态码为0，提示信息为：操作成功！
     */
    public JsonResult() {
        this.code = "200";
        this.msg = "Succeed";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为0，默认提示信息为：操作成功！
     *
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = "200";
        this.msg = "Succeed";
    }

    /**
     * 有数据返回时，自定义！
     *
     * @param data
     * @param code
     */
    public JsonResult(T data,String code) {
        this.data = data;
        this.code = code;
        this.msg = "Succeed";
    }

}
