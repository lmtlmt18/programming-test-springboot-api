package com.udomain.test.programmingtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.udomain.test.programmingtest.bean.BlogPost;
import com.udomain.test.programmingtest.db.DBConnector;

public class BlogPostHandler {
	
	public static BlogPost getBlogPostByID(long blogID) {
		BlogPost returnData = null;
		try {
			Connection con = DBConnector.connectDB();
			if (con == null)
				throw new SQLException("Unable to connect DB");
			
			String sql = "SELECT blogID, title, content, createDateTime, modifyDateTime FROM blogPost WHERE blogID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, blogID);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				returnData = new BlogPost(rs.getLong("blogID"), rs.getString("title"), rs.getString("content"), rs.getTimestamp("createDateTime"), rs.getTimestamp("modifyDateTime"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return returnData;
	}
	
	public static List<BlogPost> getBlogPostList() {
		List<BlogPost> returnData = new ArrayList<BlogPost>();
		try {
			Connection con = DBConnector.connectDB();
			if (con == null)
				throw new SQLException("Unable to connect DB");
			
			String sql = "SELECT blogID, title, content, createDateTime, modifyDateTime FROM blogPost ORDER BY modifyDateTime DESC";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				returnData.add(new BlogPost(rs.getLong("blogID"), rs.getString("title"), rs.getString("content"), rs.getTimestamp("createDateTime"), rs.getTimestamp("modifyDateTime")));
			}
		} catch (SQLException se) {
			se.printStackTrace();

		}
		return returnData;
	}
	
	private static long getMaxBlogPostID(Connection con) throws SQLException {
		String sql = "SELECT MAX(blogID) AS maxBlogID FROM blogPost";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		long returnData = 1;
		while(rs.next())
		{
			returnData = rs.getLong("maxBlogID") + 1;
		}
		return returnData;
	}
	
	/** @return blog ID of the new blog post, or < 0 for error **/
	public static long insertBlogPost(String title, String content) throws SQLException {
		long returnData = -1;

		Connection con = DBConnector.connectDB();
		if (con == null)
			throw new SQLException("Unable to connect DB");
	
		returnData = getMaxBlogPostID(con);
		Date currentDateTime = new Date();
		Timestamp currentTimestamp = new Timestamp(currentDateTime.getTime());
		
		String sql = "INSERT INTO blogPost (blogID, title, content, createDateTime, modifyDateTime) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, returnData);
		pstmt.setString(2, title);
		pstmt.setString(3, content);
		pstmt.setTimestamp(4, currentTimestamp);
		pstmt.setTimestamp(5, currentTimestamp);
		int result=pstmt.executeUpdate();
		if (result < 0)
		{
			returnData = result;
			throw new SQLException("Unable to create post");
		}
		return returnData;
	}
	
	public static long updateBlogPost(long blogID, String title, String content) throws SQLException {
		long returnData = -1;

		Connection con = DBConnector.connectDB();
		if (con == null)
			throw new SQLException("Unable to connect DB");
		
		Date currentDateTime = new Date();
		Timestamp currentTimestamp = new Timestamp(currentDateTime.getTime());
		
		String sql = "UPDATE blogPost SET title=?, content=?, modifyDateTime=? WHERE blogID=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setTimestamp(3, currentTimestamp);
		pstmt.setLong(4, blogID);
		returnData=pstmt.executeUpdate();
		if (returnData < 0)
		{
			throw new SQLException("Unable to update post");
		}
		
		return returnData;
	}
	
	public static long deleteBlogPost(long blogID) throws SQLException {
		long returnData = -1;

		Connection con = DBConnector.connectDB();
		if (con == null)
			throw new SQLException("Unable to connect DB");
	
		String sql = "DELETE FROM blogPost WHERE blogID=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, blogID);
		returnData=pstmt.executeUpdate();
		if (returnData < 0)
		{
			throw new SQLException("Unable to delete post");
		}

		return returnData;
	}
}
