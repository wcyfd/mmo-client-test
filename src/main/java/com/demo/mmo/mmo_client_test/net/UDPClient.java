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

public class UDPClient {

	private IoConnector connector;

	public UDPClient() {
		connector = new NioDatagramConnector();
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new MessageCodecFactory(Charset.forName("UTF-8"))));

	}

	public void connect(InetSocketAddress address, IoHandler handler) {
		connector.setHandler(handler);
		ConnectFuture connFuture = connector.connect(address);
		connFuture.awaitUninterruptibly();
		connFuture.addListener(new IoFutureListener<ConnectFuture>() {

			@Override
			public void operationComplete(ConnectFuture future) {
				// TODO Auto-generated method stub
				System.out.println(future.isConnected());

				Response.Builder resp = Response.newBuilder().setProtocal(301)
						.setData(CS_301.newBuilder().build().toByteString());
				IoSession session = future.getSession();
				session.write(resp);
			}

		});
	}

	public void connect() {
	}

}
