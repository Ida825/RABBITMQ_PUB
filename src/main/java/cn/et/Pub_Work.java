package cn.et;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Pub_Work {
	//给队列取个名
	private static final String QUEUE_NAME = "WORK_QUEUE";

	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		//创建管道
		Channel channel = connection.createChannel();
		
		//创建队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//生产10个消息
		for(int i=0;i<10;i++){
			channel.basicPublish("", QUEUE_NAME, null,("这是："+i).getBytes("UTF-8"));			
		}
		
		channel.close();
		connection.close();
	}

}
