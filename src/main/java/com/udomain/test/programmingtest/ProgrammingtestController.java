package com.udomain.test.programmingtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udomain.test.programmingtest.bean.BlogPost;
import com.udomain.test.programmingtest.bean.BlogPostResult;

@Controller
public class ProgrammingtestController {

    @GetMapping(value = "/post")
//    @ResponseBody
//    public BlogPostResult getBlogPostList() {
    public ResponseEntity<BlogPostResult> getBlogPostList() {
    	List<BlogPost> postList = BlogPostHandler.getBlogPostList();
    	
    	BlogPostResult returnData = new BlogPostResult();
    	returnData.setBlogPostList(postList);

//        return returnData;
        
        return new ResponseEntity<BlogPostResult>(returnData, HttpStatus.OK);
    }
    
    @PostMapping(value = "/create")
    @ResponseBody
    public BlogPostResult createBlogPost(
//    	@RequestParam(name="content", required=false, defaultValue="") String content
    	@RequestBody Map<String, Object> requestData
    ) {
    	BlogPostResult returnData = new BlogPostResult();
    	
    	try {
    		String title = "";
    		Object titleObj = requestData.get("title");
    		if (titleObj != null)
    			title = (String) titleObj;
    		
    		String content = "";
    		Object contentObj = requestData.get("content");
    		if (contentObj != null)
    			content = (String) contentObj;
    		
    		returnData.setResult(BlogPostHandler.insertBlogPost(title, content));
    		returnData.setMessage("OK");
    	} catch (Exception e)
    	{
    		returnData.setResult(-1);
    		returnData.setMessage(e.getMessage());
    	}

        return returnData;
    }
    
    @PostMapping(value = "/update")
    @ResponseBody
    public BlogPostResult updateBlogPost(
//    		@RequestParam(name="blogID", required=true) long blogID,
//    		@RequestParam(name="content", required=false, defaultValue="") String content
    		
    		@RequestBody Map<String, Object> requestData
    	) {
    	BlogPostResult returnData = new BlogPostResult();
    	
    	try {
    		long blogID = -1;
    		Object blogIDObj = requestData.get("blogID");
    		if (blogIDObj != null)
    			blogID = ((Integer) blogIDObj).intValue();
    		
    		String title = "";
    		Object titleObj = requestData.get("title");
    		if (titleObj != null)
    			title = (String) titleObj;
    		
    		String content = "";
    		Object contentObj = requestData.get("content");
    		if (contentObj != null)
    			content = (String) contentObj;
    		
    		returnData.setResult(BlogPostHandler.updateBlogPost(blogID, title, content));
    		returnData.setMessage("OK");
    	} catch (Exception e)
    	{
    		returnData.setResult(-1);
    		returnData.setMessage(e.getMessage());
    	}

        return returnData;
    }
    
    @PostMapping(value = "/delete")
    @ResponseBody
    public BlogPostResult deleteBlogPost(
//    		@RequestParam(name="blogID", required=true) long blogID
    		@RequestBody Map<String, Object> requestData
    ) {
    	BlogPostResult returnData = new BlogPostResult();
    	
    	try {
    		long blogID = -1;
    		Object blogIDObj = requestData.get("blogID");
    		if (blogIDObj != null)
    			blogID = ((Integer) blogIDObj).intValue();
    		
    		String content = "";
    		Object contentObj = requestData.get("content");
    		if (contentObj != null)
    			content = (String) contentObj;
    		
    		returnData.setResult(BlogPostHandler.deleteBlogPost(blogID));
    		returnData.setMessage("OK");
    	} catch (Exception e)
    	{
    		returnData.setResult(-1);
    		returnData.setMessage(e.getMessage());
    	}

        return returnData;
    }
}
