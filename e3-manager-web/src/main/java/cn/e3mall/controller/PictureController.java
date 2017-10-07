package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

/**
 * 文件上傳
 * 
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	@Value("${image.server.url}")
	private String imageServerUrl;

	@RequestMapping(value = "/pic/upload", produces = "text/plain;charset=utf-8")//当返回值是字符串是可以用
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		try {
			// 1）取文件原始名称
			String filename = uploadFile.getOriginalFilename();
			// 2）从原始名称中取扩展名
			String extName = filename.substring(filename.lastIndexOf(".") + 1);
			// 3）把文件上传到图片服务器，使用FastDFSClient工具类上传
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			// 4）得到返回的url，拼接图片服务器的地址
			url = imageServerUrl + url;
			// 5）创建一个Map对象
			Map result = new HashMap<>();
			// 6）设置返回值
			result.put("error", 0);
			result.put("url", url);
			// 7）返回结果
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			// 6）设置返回值
			result.put("error", 1);
			result.put("message", "上传文件失败：" + e.getMessage());
			return JsonUtils.objectToJson(result);
		}
	}
}
