package com.udomain.test.programmingtest.bean;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;

/**
 * bean/BlogPost.java
 **/
@Entity
@Table(name = "blogPost")
@Inheritance(strategy = InheritanceType.JOINED)
public class BlogPost implements Serializable {
	private static final long serialVersionUID = -4135849579031409418L;
	
	@Id
	private long blogID;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private Date createDateTime;
	@Column(nullable = false)
	private Date modifyDateTime;
	
	public BlogPost(long blogID, String title, String content, Date createDateTime, Date modifyDateTime) {
		super();
		this.blogID = blogID;
		this.title = title;
		this.content = content;
		this.createDateTime = createDateTime;
		this.modifyDateTime = modifyDateTime;
	}
	
	public long getBlogID() {
		return blogID;
	}
	public void setBlogID(long blogID) {
		this.blogID = blogID;
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
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
}