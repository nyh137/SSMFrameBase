package me.someway.example.ssm.base;

import java.util.HashMap;
import java.util.List;

import me.someway.example.ssm.constant.ResultMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.someway.example.ssm.exception.BusinessException;

/**
 * 公共服务类
 * @author RDuser
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {


	@Override
	abstract public BaseMapper<T> getMapper();
	
	
	@Override
	public List<T> findList(HashMap<String,Object> map){
		return getMapper().find(map);
	}
	
	@Override
	public T find(HashMap<String,Object> map){
		List<T> list = getMapper().find(map);
		if(list.size() == 1){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public T findById(Long  id){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<T> list = getMapper().find(map);
		if(list.size() == 1){
			return list.get(0);
		}else{
			return null;
		}
	}
	

	@Override
	public PageInfo<T> findPageList(Integer currentPage,Integer pageSize,HashMap<String,Object> map){
		PageHelper.startPage(currentPage,pageSize);
		List<T> list = findList(map);
		return new PageInfo<>(list);
	}
	
	@Override
	public void update(HashMap<String,Object> map) throws BusinessException {
		if(getMapper().update(map) != 1){
			throw new BusinessException(ResultMsg.UPDATE_ERROR);
		}
	}
	
}
