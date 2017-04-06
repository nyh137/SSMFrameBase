package me.someway.example.ssm.base;

import java.util.HashMap;
import java.util.List;

/**
 * 公共查询，增删改查
 * @author RDuser
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public List<T> find(HashMap<String, Object> map);
	
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public int save(T t);
	
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	public int update(HashMap<String, Object> map);

}
