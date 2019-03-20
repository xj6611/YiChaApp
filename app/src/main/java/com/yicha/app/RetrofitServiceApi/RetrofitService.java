package com.yicha.app.RetrofitServiceApi;

import com.yicha.app.Entity.BannerEntity;
import com.yicha.app.Entity.Base2Entity;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.DatalisEntity;
import com.yicha.app.Entity.HoemPageEntity;
import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.Entity.PC_GuJiaEntity;
import com.yicha.app.Entity.QueryLog_Entity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Entity.getMobleListEntity;
import com.yicha.app.Utlis.BaseApi;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface RetrofitService {
    //    //TODO 登录接口
//    @FormUrlEncoded
//    @POST(BaseApi.URL_LOGIN)
//    Observable<BaseEntity<UserEntity>> login(@Field("username") String username, @Field("pwd") String pwd);
//
//    //TODO 首页手机列表接口
//    @FormUrlEncoded
//    @POST("Index/getMobileList")
//    Observable<BaseEntity<List<getMobleListEntity>>> HoemPage(@Field("uid") int uid, @Field("token") String token,@Field("limits") int  limits,@Field("num") int num,@Field("model") String model);
//     //TODO 首页手机型号接口
//    @FormUrlEncoded
//    @POST("Index/getMobileModelList")
//    Observable<BaseEntity<List<MobleListEntity>>> ModelList(@Field("uid") int uid, @Field("token") String token, @Field("limits") int  limits, @Field("num") int num);
//     //TODO 首页电脑列表接口
//    @FormUrlEncoded
//    @POST("Index/getPCList")
//    Observable<BaseEntity<List<getMobleListEntity>>> PCList(@Field("uid") int uid, @Field("token") String token,@Field("limits") int  limits,@Field("num") int num,@Field("model") String model);
//     //TODO 首页电脑型号接口
//    @FormUrlEncoded
//    @POST("Index/getPCModelList")
//    Observable<BaseEntity<List<MobleListEntity>>> PCModelList(@Field("uid") int uid, @Field("token") String token, @Field("limits") int  limits, @Field("num") int num);
//
//    //TODO 手机详情页面接口
//    @FormUrlEncoded
//    @POST("Index/getMobileInfo")
//    Observable<BaseEntity<DatalisEntity>> datalis(@Field("uid") int uid, @Field("token") String token, @Field("id") int id);
//     //TODO PC详情页面接口
//    @FormUrlEncoded
//    @POST("Index/getPCInfo")
//    Observable<BaseEntity<PC_DatalisEntity>> PCInfo(@Field("uid") int uid, @Field("token") String token, @Field("id") int id);
//     //TODO 忘记密码
//    @FormUrlEncoded
//    @POST("Index/getPass")
//    Observable<BaseEntity> getPass(@Field("phone") String phone, @Field("pwd") String pwd, @Field("code") int code);
//     //TODO 更改密码
//    @FormUrlEncoded
//    @POST("Index/editPass")
//    Observable<BaseEntity> editPass(@Field("uid") int uid, @Field("token") String token,@Field("oldpass") String oldpass, @Field("newpass") String newpass);
//
//     //TODO 获取验证码
//    @FormUrlEncoded
//    @POST("Index/getPhoneCode")
//    Observable<BaseEntity> getPhoneCode( @Field("phone") String phone);
//
//    //TODO 上传头像
//    @Multipart
//    @POST("Index/uploadHeader")
//    Observable<BaseEntity> uploadHeader(@PartMap Map<String, RequestBody> params,
//                                        @Part List<MultipartBody.Part> soundRecordFile);
    @FormUrlEncoded
    @POST("Index/getMobileList")
    Observable<BaseEntity<List<getMobleListEntity>>> HoemPage(@Field("uid") int paramInt1, @Field("token") String paramString1, @Field("limits") int paramInt2, @Field("num") int paramInt3, @Field("model") String paramString2);

    @FormUrlEncoded
    @POST("Index/getMobileModelList")
    Observable<BaseEntity<List<MobleListEntity>>> ModelList(@Field("uid") int paramInt1, @Field("token") String paramString, @Field("limits") int paramInt2, @Field("num") int paramInt3);

    @FormUrlEncoded
    @POST("Index/getPCOptions")
    Observable<BaseEntity<PC_DatalisEntity>> PCInfo(@Field("uid") int paramInt, @Field("token") String paramString1, @Field("modelName") String paramString2);

    @FormUrlEncoded
    @POST("Index/getPCList")
    Observable<BaseEntity<List<getMobleListEntity>>> PCList(@Field("uid") int paramInt1, @Field("token") String paramString1, @Field("limits") int paramInt2, @Field("num") int paramInt3, @Field("model") String paramString2);

    @FormUrlEncoded
    @POST("Index/getPCModelList")
    Observable<BaseEntity<List<MobleListEntity>>> PCModelList(@Field("uid") int paramInt1, @Field("token") String paramString, @Field("limits") int paramInt2, @Field("num") int paramInt3);

    @FormUrlEncoded
    @POST("Index/getMobileOptions")
    Observable<BaseEntity<PC_DatalisEntity>> datalis(@Field("uid") int paramInt, @Field("token") String paramString1, @Field("modelName") String paramString2);

    @FormUrlEncoded
    @POST("Index/editPass")
    Observable<BaseEntity> editPass(@Field("uid") int paramInt, @Field("token") String paramString1, @Field("oldpass") String paramString2, @Field("newpass") String paramString3);

    @FormUrlEncoded
    @POST("Index/getBannerList")
    Observable<BaseEntity<List<BannerEntity>>> getBannerList(@Field("uid") int paramInt, @Field("token") String paramString);

    @FormUrlEncoded
    @POST("Index/getBikeList")
    Observable<BaseEntity<List<getMobleListEntity>>> getBikeList(@Field("uid") int paramInt1, @Field("token") String paramString1, @Field("limits") int paramInt2, @Field("num") int paramInt3, @Field("model") String paramString2);

    @FormUrlEncoded
    @POST("Index/getBikeModelList")
    Observable<BaseEntity<List<MobleListEntity>>> getBikeModelList(@Field("uid") int paramInt1, @Field("token") String paramString, @Field("limits") int paramInt2, @Field("num") int paramInt3);

    @FormUrlEncoded
    @POST("Index/getBikeOptions")
    Observable<BaseEntity<PC_DatalisEntity>> getBikeOptions(@Field("uid") int paramInt, @Field("token") String paramString1, @Field("modelName") String paramString2);

    @Multipart
    @POST("Index/getBikePrice")
    Observable<BaseEntity<PC_GuJiaEntity>> getBikePrice(@PartMap Map<String, RequestBody> paramMap);

    @Multipart
    @POST("Index/getMobilePrice")
    Observable<BaseEntity<PC_GuJiaEntity>> getMobilePrice(@PartMap Map<String, RequestBody> paramMap);

    @Multipart
    @POST("Index/getPCPrice")
    Observable<BaseEntity<PC_GuJiaEntity>> getPCPrice(@PartMap Map<String, RequestBody> paramMap);

    @FormUrlEncoded
    @POST("Index/getPass")
    Observable<BaseEntity> getPass(@Field("phone") String paramString1, @Field("pwd") String paramString2, @Field("code") int paramInt);

    @FormUrlEncoded
    @POST("Index/getPhoneCode")
    Observable<BaseEntity> getPhoneCode(@Field("phone") String paramString);

    @FormUrlEncoded
    @POST("Index/getQueryLogList")
    Observable<BaseEntity<List<QueryLog_Entity>>> getQueryLogList(@Field("uid") int paramInt1, @Field("token") String paramString, @Field("limits") int paramInt2, @Field("num") int paramInt3);

    @FormUrlEncoded
    @POST("Index/login")
    Observable<BaseEntity<UserEntity>> login(@Field("username") String paramString1, @Field("pwd") String paramString2);

    @Multipart
    @POST("Index/uploadHeader")
    Observable<BaseEntity> uploadHeader(@PartMap Map<String, RequestBody> paramMap, @Part List<MultipartBody.Part> paramList);
}
