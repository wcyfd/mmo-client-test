package com.demo.mmo.mmo_client_test.net;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import com.demo.mmo.mmo_client_test.utils.ByteUtils;
import com.demo.mmo.mmo_entity.game.entity.net.Fight.CS_301;
import com.demo.mmo.mmo_entity.game.entity.net.base.Protocal.Response;
import com.google.protobuf.ByteString;

public class WanClient {

	public enum WanType {
		TCP, UDP
	}

	private IoSession session;
	private IoConnector connector;
	
	public WanClient(){
		connector = new NioDatagramConnector();
	}

	public void startIOClient(IoHandler handler, InetSocketAddress address) {
		startClient(new ProtocolCodecFilter(new MessageCodecFactory(Charset.forName("UTF-8"))), handler, address,
				WanType.UDP);
	}

	public void startClient(IoFilter filter, IoHandler handler, InetSocketAddress address, WanType type) {
//		connector = new NioDatagramConnector();
		connector.getFilterChain().addLast("codec", filter);
		connector.setHandler(handler);

		ConnectFuture connFuture = connector.connect(address);
		connFuture.awaitUninterruptibly();
		connFuture.addListener(new IoFutureListener<ConnectFuture>() {

			@Override
			public void operationComplete(ConnectFuture future) {
				// TODO Auto-generated method stub
				if (future.isConnected()) {
					System.out.println("...connected");
					session = future.getSession();

				} else {
					System.out.println("Not connected...exiting");
				}
			}

			// private void sendData() {
			// // TODO Auto-generated method stub
			// ByteString data =
			// Response.newBuilder().setProtocal(301).setData(CS_301.newBuilder().build().toByteString()).build().toByteString();
			// byte[] lenBytes = ByteUtils.int2bytes(data.size());
			// IoBuffer buffer = IoBuffer.allocate(lenBytes.length+data.size());
			//
			// buffer.put(lenBytes).put(data.toByteArray());
			// buffer.flip();
			// session.write(buffer);
			// }
		});
	}
}
