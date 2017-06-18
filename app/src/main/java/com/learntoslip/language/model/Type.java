package com.learntoslip.language.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Type  implements Serializable{

	public Type() {
		
	}

	/**
	*  类型ID
	*/
	private Long id;

	/**
	*  类型名称
	*/
	private String name;

	/**
	*  父ID
	*/
	private Long parentId;

	/**
	*  生效、不生效
	*/
	private Integer status;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createTime;

	private List<Type> types;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return parentId;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getNote(){
		return note;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "BType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status=" + status +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				'}';
	}

}
