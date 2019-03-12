package com.limengting.service;

import java.io.IOException;

public interface IQiniuService {

    void upload(byte[] localData, String remoteFileName) throws IOException;
}
