package cn.et;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Pub_Work {
	//������ȡ����
	private static final String QUEUE_NAME = "WORK_QUEUE";

	public static void main(String[] args) throws IOException, TimeoutException {
		//��������
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		//�����ܵ�
		Channel channel = connection.createChannel();
		
		//��������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//����10����Ϣ
		for(int i=0;i<10;i++){
			channel.basicPublish("", QUEUE_NAME, null,("���ǣ�"+i).getBytes("UTF-8"));			
		}
		
		channel.close();
		connection.close();
	}

}
