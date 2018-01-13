package cn.et;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Pub_Log_TopicRoute {

	private static final String EXCHANGE_NAME = "amq_log_topic";

	/**
	 * ·��ģʽ topic
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
		//·��ģʽ ���ͣ�"direct"
		channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);	
		//��routekey:error   
		/*channel.basicPublish(EXCHANGE_NAME, "a.error",MessageProperties.PERSISTENT_TEXT_PLAIN,("ϵͳ����ά��...").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "a.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("����������ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "a.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("���ķ�����ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.error",MessageProperties.PERSISTENT_TEXT_PLAIN,("ϵͳ����ά��...").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("����������ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("���ķ�����ҳ��2018-1-11").getBytes("UTF-8"));
		*/
		
		channel.basicPublish(EXCHANGE_NAME, "a.a1.error",MessageProperties.PERSISTENT_TEXT_PLAIN,("ϵͳ����ά��...").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "a.a2.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("����������ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "a.a3.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("���ķ�����ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.b1.error",MessageProperties.PERSISTENT_TEXT_PLAIN,("ϵͳ����ά��...").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.b2.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("����������ҳ��2018-1-11").getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, "b.b3.info",MessageProperties.PERSISTENT_TEXT_PLAIN,("���ķ�����ҳ��2018-1-11").getBytes("UTF-8"));

		channel.close();
		connection.close();
	}

}
