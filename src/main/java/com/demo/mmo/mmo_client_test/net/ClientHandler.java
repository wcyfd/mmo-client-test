package com.demo.mmo.mmo_client_test.net;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientHandler implements IoHandler {

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		System.out.println("sessionOpened");
	}

	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		System.out.println("sessionClosed");
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		System.out.println("sessionIdle");
	}

	@Override
	public void exceptionCaught(IoSession iosession, Throwable throwable) throws Exception {
		System.out.println("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		System.out.println("messageReceived");
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		System.out.println("messageSent");
	}

}
