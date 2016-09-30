package com.demo.mmo.mmo_client_test.utils;

public class ByteUtils {
	public static byte[] int2bytes(int res) {
		byte[] t = new byte[4];
		t[0] = (byte) (res & 0xff);// 最低位
		t[1] = (byte) ((res >> 8) & 0xff);// 次低位
		t[2] = (byte) ((res >> 16) & 0xff);// 次高位
		t[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return t;
	}
}
