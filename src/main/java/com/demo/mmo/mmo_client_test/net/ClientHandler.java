package com.demo.mmo.mmo_client_test.net;

import org.apache.mina.core.session.IoSession;

import com.demo.mmo.mmo_entity.game.entity.net.Fight.FightInfo;
import com.demo.mmo.mmo_entity.game.entity.net.Fight.SC_302;
import com.demo.mmo.mmo_entity.game.entity.net.Fight.SC_303;
import com.demo.mmo.mmo_entity.game.entity.net.base.Protocal.Request;
import com.google.protobuf.ByteString;

public class ClientHandler extends IoHandlerAdapter{
	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(iosession, obj);
		
		Request request = (Request)obj;
		int protocal =request.getProtocal();
		ByteString data = request.getData();
		if(protocal == 20303){
			SC_303 sc303 = SC_303.parseFrom(data);
			FightInfo fightInfo = sc303.getFightInfo();
			System.out.println(fightInfo.getTime());
			
			
		}
	}
	
	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
		super.messageSent(iosession, obj);
	}
}
