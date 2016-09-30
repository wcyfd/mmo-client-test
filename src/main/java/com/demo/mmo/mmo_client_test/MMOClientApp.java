package com.demo.mmo.mmo_client_test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.demo.mmo.mmo_client_test.net.ClientHandler;
import com.demo.mmo.mmo_client_test.net.WanClient;
import com.demo.mmo.mmo_entity.game.entity.net.Fight.CS_301;
import com.demo.mmo.mmo_entity.game.entity.net.Fight.CS_302;
import com.demo.mmo.mmo_entity.game.entity.net.base.Protocal.Response;

/**
 * Hello world!
 *
 */
public class MMOClientApp {
	public static void main(String[] args) {	
		WanClient client = new WanClient();
		client.startIOClient(new ClientHandler(), new InetSocketAddress("10.0.51.100", 10001));
	}
	
	
	
	public static void start(int i ){
		String host = "10.0.51.100";
		int port = 10001;
		try {
			InetAddress ia = InetAddress.getByName(host);
			final DatagramSocket socket = new DatagramSocket(i);

			socket.connect(ia, port);
			send301Data(socket);
			Thread.sleep(2000);
			Thread t = new Thread(new Runnable() {

				public void run() {
					while (true)
						receive(socket);
				}
			});
			Thread t2 = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						send302Data(socket);
					}
				}

			});
			t2.start();
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void send301Data(DatagramSocket socket) {
		Response.Builder resp = Response.newBuilder().setProtocal(301)
				.setData(CS_301.newBuilder().build().toByteString());

		send(socket, resp);

	}

	private static void send302Data(DatagramSocket socket) {
		Response.Builder resp = Response.newBuilder().setProtocal(302)
				.setData(CS_302.newBuilder().setX(3.0f).setY(4.0f).build().toByteString());

		send(socket, resp);
	}

	public static void send(DatagramSocket socket, Response.Builder response) {
		byte[] data = response.build().toByteArray();
		int len = data.length;
		byte[] b = int2bytes(len);
		byte[] buffer = new byte[4 + data.length];
		int i = 0;
		for (i = 0; i < b.length; i++) {
			buffer[i] = b[i];
		}
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < data.length; j++, i++) {
			buffer[i] = data[j];

		}
		for (int k = 0; k < buffer.length; k++) {
			System.out.print(buffer[k]);
			System.out.print(" ");
		}

		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		System.out.println(dp.getLength());
		try {
			socket.send(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] receive(DatagramSocket socket) {

		DatagramPacket dp1 = new DatagramPacket(new byte[22312], 22312);
		try {
			socket.receive(dp1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bb = dp1.getData();
		for (int i = 0; i < dp1.getLength(); i++) {
			System.out.print((char) bb[i]);
		}
		System.out.println();
		return null;
	}

	public static byte[] int2bytes(int res) {
		byte[] t = new byte[4];
		t[0] = (byte) (res & 0xff);// 最低位
		t[1] = (byte) ((res >> 8) & 0xff);// 次低位
		t[2] = (byte) ((res >> 16) & 0xff);// 次高位
		t[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return t;
	}

}
