package net.historm.tk.net;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求的工具类 使用的工具是OkHttp，官网：http://square.github.io/okhttp/
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static final OkHttpClient client = new OkHttpClient();
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	/**
	 * GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		String ret = null;
		try {
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			ret = response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage());
			ret = "";
		}
		return ret;
	}

	/**
	 * POST提交JSON字符串
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String postJson(String url, String json) {
		String ret = null;
		try {
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
			Request request = new Request.Builder().url(url).post(body).build();
			Response response = client.newCall(request).execute();
			ret = response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage());
			ret = "";
		}
		return ret;
	}

	/**
	 * 通过POST提交文件
	 * 
	 * @param url
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public static String postFile(String url, String pathname) throws IOException {
		String ret = null;
		try {
			File file = new File(pathname);
			Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			ret = response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage());
			ret = "";
		}
		return ret;
	}

}
