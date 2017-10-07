import org.apache.ibatis.transaction.Transaction;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {

	@Test
	public void test() throws Exception {
//		1、加载配置文件，配置文件中的内容就是tracker服务的地址。
//		配置文件内容：tracker_server=192.168.25.133:22122
		ClientGlobal.init("D:/传智就业/练习/宜立方项目/e3-manager-web/src/main/resources/conf/client.conf");
//		2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
//		3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
//		4、创建一个StorageServer的引用，值为null
		StorageServer storageServer=null;
//		5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 6、使用StorageClient上传文件，返回组名和路径及文件名。
//参数1：本地文件路径 参数2：文件扩展名，不包含“.” 参数3：元数据，如果没有数据可以为null
		String[] strings = storageClient.upload_file("F:/新建文件夹/图片/10.jpg", "jpg", null);
//		7、返回数组。包含组名和图片的路径。
		for (String string : strings) {
			System.out.println(string);
		}
	}
	@Test
	public void Test1() throws Exception{
		//创建一个FastDFSClient对象
		FastDFSClient fastDFSClient = new FastDFSClient("D:/传智就业/练习/宜立方项目/e3-manager-web/src/main/resources/conf/client.conf");
		//使用对象上传文件
		String string = fastDFSClient.uploadFile("F:/新建文件夹/图片/104.jpg");
		System.out.println(string);
	}

}
