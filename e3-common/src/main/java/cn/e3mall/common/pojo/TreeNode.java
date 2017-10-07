package cn.e3mall.common.pojo;

import java.io.Serializable;
/**
 * 创建一个pojo类里面包含（id，text，state）三个属性作为返回的的数据格式
 * @author Administrator
 *
 */
public class TreeNode implements Serializable {
 private long id;
 private String text;
 private String state;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
 
}
