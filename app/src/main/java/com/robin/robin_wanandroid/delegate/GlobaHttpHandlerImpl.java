package com.robin.robin_wanandroid.delegate;

import com.robin.rbase.CommonBase.utils.GlobalHttpHandler;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.util.CacheUtil;

import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class GlobaHttpHandlerImpl implements GlobalHttpHandler {
    /**
     * 这里可以先客户端一步拿到每一次 Http 请求的结果, 可以先解析成 Json, 再做一些操作, 如检测到 token 过期后
     * 重新请求 token, 并重新执行请求
     *
     * @param httpResult 服务器返回的结果 (已被框架自动转换为字符串)
     * @param chain      [okhttp3.Interceptor.Chain]
     * @param response   [Response]
     * @return [Response]
     */
    @NonNull
    @Override
    public Response onHttpResultResponse(@Nullable String httpResult, @NonNull Interceptor.Chain chain, @NonNull Response response) {
        //如果是登录的话，需要保存cookie
        Logger.i("iscollect : cookie"+chain.request().url());
        if (chain.request().url().toString().contains("user/login")) {
            Logger.i("request url :"+chain.request().url().encodedPath());
            if (!response.headers("set-cookie").isEmpty()) {
                //保存Cookie做持久化操作 set-cookie可能为多个
                List<String> cookies = response.headers("set-cookie");
                Logger.i("iscollect : save "+chain.request().url().toString());

                CacheUtil.setCookie(chain.request().url().toString(),cookieUtil(cookies));
            }
        }

        return response;
    }

    @NonNull
    @Override
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        /* 如果需要在请求服务器之前做一些操作, 则重新构建一个做过操作的 Request 并 return, 如增加 Header、Params 等请求信息, 不做操作则直接返回参数 request*/
       Logger.i("iscollect respon: "+CacheUtil.isLogin()+"   11  "+ CacheUtil.getCookie(chain.request().url().toString())+" 22 "+chain.request().url().toString());
        if (CacheUtil.isLogin()) {
            String cookies = CacheUtil.getCookie("https://www.wanandroid.com/user/login");
            //如果已经登录过了，那么请求的时候可以带上cookie 参数
                return chain.request().newBuilder()
                        .addHeader("Cookie", cookies)
                        .build();
        }

        return request;
    }

    private String cookieUtil(List<String> cookies){
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<String>();
        for (String cookie : cookies) {
            String [] arr=cookie.split(",");
            for (String s : arr) {
                if ( s.isEmpty()||set.contains(s)){
                    continue;
                }
               set.add(s);
            }
        }

        for (String o : set) {
            sb.append(o).append(";");
        }
        int last=sb.lastIndexOf(";");
        if (sb.length()-1==last){
            sb.deleteCharAt(last);
        }
        return sb.toString();
    }
}
