package com.bonc.ioc.util;

import com.bonc.ioc.model.Account;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p> 名       称：项目Shiro组件自定义的密码生成器类
 * <p> 功       能：生成密码，加密
 * <p> 作       者： 左明强
 * <p> 联系方式：13522126905
 * <p> 创建时间：2016/08/22 10:00:00 
 * <p> 特殊情形： 无
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
    
    public String getAlgorithmName() {
		return algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void encryptPassword(Account user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getAccPwd(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setAccPwd(newPassword);
    }
	
	public String getEncryptPassword(Account user) {
        return new SimpleHash(algorithmName, user.getAccPwd(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
    }
	
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setAccNum("sysadmin");
		acc.setAccPwd("123456");
		new PasswordHelper().encryptPassword(acc);
		System.out.println("密码："+acc.getAccPwd());
		System.out.println("salt："+acc.getSalt());
	}
}
