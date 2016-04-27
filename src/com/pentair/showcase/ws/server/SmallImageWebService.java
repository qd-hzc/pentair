package com.pentair.showcase.ws.server;

import javax.jws.WebService;

import com.pentair.showcase.ws.server.result.SmallImageResult;

/**
 * 演示对小图片以Base64Binary直接编码整个byte数组的二进制数据传输方式.
 * 
 * @author calvin
 */
@WebService(name = "SmallImageService", targetNamespace = WsConstants.NS)
public interface SmallImageWebService {

	SmallImageResult getImage();
}
