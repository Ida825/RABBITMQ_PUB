package cn.et;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Pub_Email {
	/**
	 * 序列化（将对象转换成字节数组）
	 * @return
	 * @throws IOException 
	 */
	public static byte[] seq(Object obj) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.toByteArray();
	}
	
	/**
	 * 反序列化（将字节数组转成对象）
	 * @param bt
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object dseq(byte[] bt) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(bt);
		ObjectInputStream ois = new ObjectInputStream(bis);	
		return ois.readObject();
		
	}
	
	private final static String QUEUE_NAME = "MAIL_QUEUE";
	public static void main(String[] args) throws IOException, TimeoutException {
		//(模拟发送邮件)创建发送邮件的任务
		Map  map = new HashMap();
		map.put("sendTo", "sunyingida@163.com");
		map.put("subject", "测试邮件");
		map.put("content", "啦啦啦");
		
		//连接到远程rabbitmq服务器
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//创建一个队列存储邮件信息
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	
		//发送消息  需要序列化 ：seq(map)
		channel.basicPublish("", QUEUE_NAME, null, seq(map));
	}

}
