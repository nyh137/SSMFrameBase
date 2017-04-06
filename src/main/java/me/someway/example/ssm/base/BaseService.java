package me.someway.example.ssm.base;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import me.someway.example.ssm.exception.BusinessException;

/**
 * 公共service
 * @author RDuser
 *
 */
public interface BaseService<T> {

	/**
	 * 获取mapper
	 * @return
	 */
	BaseMapper<T> getMapper();

	/**
	 * 获取列表
	 * @param map
	 * @return
	 */
	List<T> findList(HashMap<String, Object> map);

	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param map
	 * @return
	 */
	PageInfo<T> findPageList(Integer currentPage, Integer pageSize, HashMap<String, Object> map);

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	T find(HashMap<String, Object> map);

	/**
	 * 更新
	 * @param map
	 * @throws BusinessException
	 */
	void update(HashMap<String, Object> map) throws BusinessException;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	T findById(Long id);

}
