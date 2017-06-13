package com.learntoslip.language.model;
import java.io.Serializable;
import java.util.Date;

public class ForgettingCurve implements Serializable{

	public ForgettingCurve() {
		
	}

	/**
	*  ID
	*/
	private Long id;

	/**
	*  名称
	*/
	private String name;

	/**
	*  间隔时间
	*/
	private Long intervalTime;

	/**
	*  间隔类型
	*/
	private Integer intervalType;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createTime;
	
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
	
	public void setIntervalTime(Long intervalTime){
		this.intervalTime = intervalTime;
	}
	
	public Long getIntervalTime(){
		return intervalTime;
	}
	
	public void setIntervalType(Integer intervalType){
		this.intervalType = intervalType;
	}
	
	public Integer getIntervalType(){
		return intervalType;
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


	@Override
	public String toString() {
		return "BForgettingCurve{" +
				"id=" + id +
				", name='" + name + '\'' +
				", intervalTime=" + intervalTime +
				", intervalType=" + intervalType +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
