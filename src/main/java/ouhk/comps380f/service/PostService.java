/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.PostNotFound;
import ouhk.comps380f.model.Post;

/**
 *
 * @author LTW
 */
public interface PostService {

    public long createPost(String creater, String topic, String body,
            List<MultipartFile> attachments, String createddate, String posttype) throws IOException;

    // Get all post
    public List<Post> getPosts();
    
    // Get all post of someone type
    public List<Post> getPosts(String posttype);

    public Post getPost(long postid);

    //--String createddate
    public void updatePost(long postid, String topic, String body, List<MultipartFile> attachments) throws IOException, PostNotFound;

    public void delete(long postid) throws PostNotFound;

    public void deleteAttachment(long postid) throws AttachmentNotFound;

}
