
package me.someway.example.ssm.common.druid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.filter.config.ConfigTools;


public class DruidUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(DruidUtil.class);

	public static void main(String[] args) throws Exception {
		String userName = "root";
		String password = "";
	    String[] arr = ConfigTools.genKeyPair(512);
	    LOG.debug("privateKey:" + arr[0]);
	    LOG.debug("publicKey:" + arr[1]);
	    LOG.debug("name:" + ConfigTools.encrypt(arr[0], userName));
	    LOG.debug("password:" + ConfigTools.encrypt(arr[0], password));
	}

}
