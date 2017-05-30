package com.ikilun.http;

public interface HttpResultCallback {
	void processResult(HttpResult result);
	void processError(Throwable t);
}
