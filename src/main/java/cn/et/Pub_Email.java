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
	 * ���л���������ת�����ֽ����飩
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
	 * �����л������ֽ�����ת�ɶ���
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
		//(ģ�ⷢ���ʼ�)���������ʼ�������
		Map  map = new HashMap();
		map.put("sendTo", "sunyingida@163.com");
		map.put("subject", "�����ʼ�");
		map.put("content", "������");
		
		//���ӵ�Զ��rabbitmq������
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.128");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//����һ�����д洢�ʼ���Ϣ
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	
		//������Ϣ  ��Ҫ���л� ��seq(map)
		channel.basicPublish("", QUEUE_NAME, null, seq(map));
	}

}
