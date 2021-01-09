package com.ys.util;

import java.awt.*;
import java.util.Random;

/**
 * 验证码生成器
 * 
 * @author llq
 */
public class CpachaUtil {
	
	/**
	 * 验证码来源
	 */
	final private char[] code = {
		'2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 
		'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	/**
	 * 字体
	 */
	final private String[] fontNames = new String[]{
			"黑体", "宋体", "Courier", "Arial", 
			"Verdana", "Times", "Tahoma", "Georgia"};
	/**
	 * 字体样式
	 */
	final private int[] fontStyles = new int[]{
			Font.BOLD, Font.ITALIC|Font.BOLD
	};
	
	/**
	 * 验证码长度
	 * 默认4个字符
	 */
	private int vcodeLen = 4;
	/**
	 * 验证码图片字体大小
	 * 默认17
	 */
	private int fontsize = 21;
	/**
	 * 验证码图片宽度
	 */
	private int width = (fontsize+1)*vcodeLen+10;
	/**
	 * 验证码图片高度
	 */
	private int height = fontsize+12;


	public CpachaUtil(){}
	
	/**
	 * 指定验证码长度
	 * @param vcodeLen 验证码长度
	 */
	public CpachaUtil(int vcodeLen) {
		this.vcodeLen = vcodeLen;
		this.width = (fontsize+1)*vcodeLen+10;
	}
	
	public CpachaUtil(int vcodeLen, int fontSize) {
		this.vcodeLen = vcodeLen;
		this.fontsize = fontSize;
		this.width = (fontsize+1)*vcodeLen+10;
		height = fontsize+12;
	}

	/**
	 * 生成验证码
	 * @return 验证码
	 */
	public String generatorVCode(){
		int len = code.length;
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < vcodeLen;i++){
			int index = ran.nextInt(len);
			sb.append(code[index]);
		}
		return sb.toString();
	}

	/**
	 * @return 验证码字符个数
	 */
	public int getVcodeLen() {
		return vcodeLen;
	}
	/**
	 * 设置验证码字符个数
	 * @param vcodeLen
	 */
	public void setVcodeLen(int vcodeLen) {
		this.width = (fontsize+3)*vcodeLen+10;
		this.vcodeLen = vcodeLen;
	}
}
