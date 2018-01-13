package cn.et;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Pub_Log {

	private static final String EXCHANGE_NAME = "amq_log";

	/**
	 * 发布订阅模式
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//创建一个交换器 (交换器名字，交换器类型，是否将交换器信息永久保存在服务器磁盘上)
		//发布订阅模式"fanout"
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
		String message = null;
		//同时发送5条消息
		for(int i=0;i<5;i++){
			message = "第"+i+"条消息";
			channel.basicPublish(EXCHANGE_NAME, "",MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
		}
		
		channel.close();
		connection.close();
	}

}
