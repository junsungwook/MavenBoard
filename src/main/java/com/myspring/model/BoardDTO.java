package com.myspring.model;

public class BoardDTO {
 private int seq,groups,levels,steps,hitcount;
 public int getSeq() {
	return seq;
}
public void setSeq(int seq) {
	this.seq = seq;
}
public int getGroups() {
	return groups;
}
public void setGroups(int groups) {
	this.groups = groups;
}
public int getLevels() {
	return levels;
}
public void setLevels(int levels) {
	this.levels = levels;
}
public int getSteps() {
	return steps;
}
public void setSteps(int steps) {
	this.steps = steps;
}
public int getHitcount() {
	return hitcount;
}
public void setHitcount(int hitcount) {
	this.hitcount = hitcount;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getRegdate() {
	return regdate;
}
public void setRegdate(String regdate) {
	this.regdate = regdate;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
private String title,content,writer,regdate,password;
}
