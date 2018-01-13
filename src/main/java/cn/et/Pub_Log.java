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
	 * ��������ģʽ
	 * @param args
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, TimeoutException {
		//��������
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//����һ�������� (���������֣����������ͣ��Ƿ񽫽�������Ϣ���ñ����ڷ�����������)
		//��������ģʽ"fanout"
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
		String message = null;
		//ͬʱ����5����Ϣ
		for(int i=0;i<5;i++){
			message = "��"+i+"����Ϣ";
			channel.basicPublish(EXCHANGE_NAME, "",MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
		}
		
		channel.close();
		connection.close();
	}

}
