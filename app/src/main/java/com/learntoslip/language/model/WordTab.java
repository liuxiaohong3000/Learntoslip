package com.learntoslip.language.model;
import java.io.Serializable;
import java.util.Date;

public class WordTab implements Serializable{

	public WordTab() {
		
	}

	/**
	*  
	*/
	private Long id;

	/**
	*  同义词
	*/
	private String synonym;

	/**
	*  速记
	*/
	private String shorthand;

	/**
	*  短语（短语、句子），json结构
	*/
	private String phrases;

	/**
	*  词类，json结构
	*/
	private String wordClasses;

	/**
	*  其他信息，json结构
	*/
	private String otherInfo;

	/**
	*  关键词关联ID
	*/
	private Long wordId;

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
	
	public void setSynonym(String synonym){
		this.synonym = synonym;
	}
	
	public String getSynonym(){
		return synonym;
	}
	
	public void setShorthand(String shorthand){
		this.shorthand = shorthand;
	}
	
	public String getShorthand(){
		return shorthand;
	}
	
	public void setPhrases(String phrases){
		this.phrases = phrases;
	}
	
	public String getPhrases(){
		return phrases;
	}
	
	public void setWordClasses(String wordClasses){
		this.wordClasses = wordClasses;
	}
	
	public String getWordClasses(){
		return wordClasses;
	}
	
	public void setOtherInfo(String otherInfo){
		this.otherInfo = otherInfo;
	}
	
	public String getOtherInfo(){
		return otherInfo;
	}
	
	public void setWordId(Long wordId){
		this.wordId = wordId;
	}
	
	public Long getWordId(){
		return wordId;
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
		return "BWordTab{" +
				"id=" + id +
				", synonym='" + synonym + '\'' +
				", shorthand='" + shorthand + '\'' +
				", phrases='" + phrases + '\'' +
				", wordClasses='" + wordClasses + '\'' +
				", otherInfo='" + otherInfo + '\'' +
				", wordId=" + wordId +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
