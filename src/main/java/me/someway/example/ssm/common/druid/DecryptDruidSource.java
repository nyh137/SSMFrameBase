package me.someway.example.ssm.common.druid;

import me.someway.example.ssm.common.property.PropertyCtx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

import static com.alibaba.druid.filter.config.ConfigTools.decrypt;

/**
 * 用来解密配置中的密文(重点配置，在这里扩展用户名的解密) setUsername(name)
 * 方法对应xml中的一个property属性，password默认加密不需要重写， 还可以加密url 重写setUrl(url)
 */
@SuppressWarnings("all")
public class DecryptDruidSource extends DruidDataSource {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(DecryptDruidSource.class);

	@Override
	public void setUsername(String username) {
		try {
			String publicKeyText = PropertyCtx.getContextProperty("db.publicKey");
			username = decrypt(publicKeyText, username);
		} catch (Exception e) {
			logger.error("db.properties.loading",e);
		}
		super.setUsername(username);
	}
}
