package com.limengting.serviceimpl;

import com.google.gson.Gson;
import com.limengting.common.Constant;
import com.limengting.service.IQiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class QiniuServiceImpl implements IQiniuService {
    // 设置好账号的ACCESS_KEY和SECRET_KEY
    private String ACCESS_KEY = Constant.QINIU_ACCESS_KEY;
    private String SECRET_KEY = Constant.QINIU_SECRET_KEY;
    // 要上传的空间
    private String BUCKET_NAME = Constant.QINIU_BUCKET_NAME;
    // 密钥配置
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //构造一个带指定Zone对象的配置类
    private Configuration cfg = new Configuration(Zone.zone1());
    //...其他参数参考类注释

    private UploadManager uploadManager = new UploadManager(cfg);
    //...生成上传凭证，然后准备上传
    // 创建上传对象
   // private UploadManager uploadManager = new UploadManager();

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    @Override
    public String upload(byte[] localData, String remoteFileName) throws IOException {
        //Response res = uploadManager.put(localData, remoteFileName, getUpToken());
        // 打印返回的信息
        //System.out.println(res.bodyString());
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            Response response = uploadManager.put(localData, key, getUpToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet.hash;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /*public static void main(String[] args) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

        *//*public static final String QINIU_IMAGE_URL = "http://qiniu.limengting.site/";
    public static final String QINIU_ACCESS_KEY = "4Uk12cIO6mqC3QR0DapG349IFjP5bA_bMwIl0mcj";
    public static final String QINIU_SECRET_KEY = "6-R9_xaEnpkR34WZf8HTTsKCJ2o8cnUk1ZJZYlnO";
    public static final String QINIU_BUCKET_NAME = "photos";*//*
        String accessKey = "4Uk12cIO6mqC3QR0DapG349IFjP5bA_bMwIl0mcj";
        String secretKey = "6-R9_xaEnpkR34WZf8HTTsKCJ2o8cnUk1ZJZYlnO";
        String bucket = "photos";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "/Users/sunnie/Downloads/git.jpeg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }*/
}
