package com.learntoslip.language.model;
import java.io.Serializable;
import java.util.Date;

public class UserWord implements Serializable{

	public UserWord() {
		
	}

	/**
	*  ID
	*/
	private Long id;

	/**
	*  用户ID
	*/
	private Long userId;

	/**
	*  关键词ID
	*/
	private Long wordId;

	/**
	*  关键词类型
	*/
	private Integer wordType;
	/**
	 *  关键词名称
	 */
	private String wordName;

	/**
	*  记忆时间
	*/
	private Date memoryTime;
	/**
	 *  翻译
	 */
	private String translate;

	/**
	 * 音标
	 */
	private String pronunciation;
	/**
	*  回忆时间
	*/
	private Date recallTime;

	/**
	*  用户遗忘曲线ID
	*/
	private Long userForgettingCurveId;

	/**
	*  备注
	*/
	private String note;

	/**
	*  创建时间
	*/
	private Date createTime;

	/**
	*  更新时间
	*/
	private Date updateTime;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setWordId(Long wordId){
		this.wordId = wordId;
	}
	
	public Long getWordId(){
		return wordId;
	}
	
	public void setWordType(Integer wordType){
		this.wordType = wordType;
	}
	
	public Integer getWordType(){
		return wordType;
	}

	public void setMemoryTime(Date memoryTime){
		this.memoryTime = memoryTime;
	}
	
	public Date getMemoryTime(){
		return memoryTime;
	}

	public void setRecallTime(Date recallTime){
		this.recallTime = recallTime;
	}
	
	public Date getRecallTime(){
		return recallTime;
	}
	
	public void setUserForgettingCurveId(Long userForgettingCurveId){
		this.userForgettingCurveId = userForgettingCurveId;
	}
	
	public Long getUserForgettingCurveId(){
		return userForgettingCurveId;
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

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	@Override
	public String toString() {
		return "UserWord{" +
				"id=" + id +
				", userId=" + userId +
				", wordId=" + wordId +
				", wordType=" + wordType +
				", wordName='" + wordName + '\'' +
				", memoryTime=" + memoryTime +
				", translate='" + translate + '\'' +
				", pronunciation='" + pronunciation + '\'' +
				", recallTime=" + recallTime +
				", userForgettingCurveId=" + userForgettingCurveId +
				", note='" + note + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
