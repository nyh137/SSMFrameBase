package me.someway.example.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 编号生成器
 *
 */
public class NidGenerator {

    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    private static int getHashCode() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode;
    }
    
    /**
     * 生成用户名
     * @return
     */
    public static String getUserName() {
        int hashCode = getHashCode();
        return "AZP" + String.format("%011d", hashCode);
    }
    /**
	 * 产生唯一 的序列号。
	 * 
	 * @return String
	 */
	public static String getSerialNumber() {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		return dateFormat.format(new Date()).substring(2, 8) + String.format("%010d", hashCode);
	}
	
	  /**
		 * 产生唯一 的协议号。
		 * 
		 * @return String
		 */
		public static String getPayProtocol() {
			int hashCode = UUID.randomUUID().toString().hashCode();
			if (hashCode < 0) {
				hashCode = -hashCode;
			}
			return "YSPAY_"+ getSerialNumber();
		}
		
		public static void main(String[] args) {
			System.err.println(getPayProtocol());
		}
}
