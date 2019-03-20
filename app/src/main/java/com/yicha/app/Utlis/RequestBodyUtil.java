package com.yicha.app.Utlis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 谢军 on 2018/11/14.
 * 文件上传
 */

public class RequestBodyUtil {
    /**
     * 将文件路径数组封装为{@link List<MultipartBody.Part>}
     *
     * @param key       对应请求正文中name的值。目前服务器给出的接口中，所有图片文件使用<br>
     *                  同一个name值，实际情况中有可能需要多个
     */

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files, String key) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    /**
     * 将参数封装成requestBody形式上传参数
     * @param param 参数
     * @return RequestBody
     */
    public static RequestBody convertToRequestBody(String param){
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        return requestBody;
    }

    /**
     * 将文件进行转换
     * @param param 为文件类型
     * @return
     */
    public static RequestBody convertToRequestBodyMap(File param){

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), param);
        return requestBody;
    }
}
