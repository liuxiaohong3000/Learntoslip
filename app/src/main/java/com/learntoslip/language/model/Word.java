package com.learntoslip.language.model;
import java.io.Serializable;
import java.util.Date;

public class Word implements Serializable{

	public Word() {

	}

	/**
	*  ID
	*/
	private Long id;

	private int imageId;
	/**
	*  名称
	*/
	private String name;

	/**
	*  翻译
	*/
	private String translate;
	/**
	 * 音标
	 */
	private String pronunciation;
	/**
	*  类型ID
	*/
	private Integer typeId;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createTime;

	/**
	 *  显示类型
	 */
	private Integer showType;
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
	
	public void setTranslate(String translate){
		this.translate = translate;
	}
	
	public String getTranslate(){
		return translate;
	}
	
	public void setTypeId(Integer typeId){
		this.typeId = typeId;
	}
	
	public Integer getTypeId(){
		return typeId;
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

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	@Override
	public String toString() {
		return "Word{" +
				"id=" + id +
				", imageId=" + imageId +
				", name='" + name + '\'' +
				", translate='" + translate + '\'' +
				", pronunciation='" + pronunciation + '\'' +
				", typeId=" + typeId +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
