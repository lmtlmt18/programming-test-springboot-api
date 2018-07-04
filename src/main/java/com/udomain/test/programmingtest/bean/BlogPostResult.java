package com.udomain.test.programmingtest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * bean/BlogPostResult.java
 **/
public class BlogPostResult implements Serializable {
	private static final long serialVersionUID = -6297252923954905142L;
	
	private List<BlogPost> blogPostList;
	/** Update blog post result. Blog ID for INSERT, 1 for UPDATE / DELETE, < 0 for ERROR. **/
	private long result;
	/** Error message, or "OK" for SUCCESS. **/
	private String message;
	
	public BlogPostResult()
	{
		
	}

	public BlogPostResult(long result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public List<BlogPost> getBlogPostList() {
		return blogPostList;
	}

	public void setBlogPostList(List<BlogPost> blogPostList) {
		this.blogPostList = blogPostList;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}