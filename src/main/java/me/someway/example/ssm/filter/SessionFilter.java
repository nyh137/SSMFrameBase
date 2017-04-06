package me.someway.example.ssm.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.someway.example.ssm.constant.Constant;
import me.someway.example.ssm.constant.ResultCode;
import me.someway.example.ssm.constant.ResultMsg;
import me.someway.example.ssm.domain.User;
import me.someway.example.ssm.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/**
 * session拦截器
 */
public class SessionFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	private String[] whitePathList = {"/user/doLogin", "/user/register"};

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = false;

	protected String forwardPath = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null || "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
			this.ignore = true;
		else
			this.ignore = false;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest reqRequest = (HttpServletRequest) request;
		HttpServletResponse reqResponse = (HttpServletResponse) response;
		String path = ((HttpServletRequest) request).getServletPath();
		// 通过检查session中的变量，过虑请求
		HttpSession session = reqRequest.getSession();
		User user = (User)session.getAttribute(Constant.SESSION_USER);
		// 当前会话用户为空而且不是请求登录，退出登录，欢迎页面和根目录则退回到应用的根目录
		if (!isWhitePath(path) && user == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", ResultCode.NOT_ACCESS);
			map.put("message", ResultMsg.SESSION_USER_OVERDUE);
			JSON resData = JsonUtil.newJson().addCode(ResultCode.NOT_ACCESS).addMessage(ResultMsg.SESSION_USER_OVERDUE).toJson();
			printJson(reqResponse, resData.toJSONString());
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isWhitePath(String path) {
		for (String whitePath : whitePathList) {
			if (path.contains(whitePath)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
     * 返回数据
     * 
     * @param response
     */
    private void printJson(HttpServletResponse response, String responseObject) {
        // 将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(responseObject);
            out.flush();
        } catch (IOException e) {
           logger.error("signFilter,error",e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
