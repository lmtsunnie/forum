package com.limengting.service;

import java.io.IOException;

public interface IQiniuService {

    String upload(byte[] localData, String remoteFileName) throws IOException;
}
