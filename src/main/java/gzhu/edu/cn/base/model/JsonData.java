package gzhu.edu.cn.base.model;

import java.util.List;

/** 
 * json数据格式
 * @author 丁国柱 E-mail:
 * @date 创建时间：2020年2月25日 上午12:14:58 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return   
 */
public class JsonData<T> {
	
	private int code;
	
	private String msg;
	
	private long count; 
	
	private List<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	

}
