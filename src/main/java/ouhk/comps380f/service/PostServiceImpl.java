/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ouhk.comps380f.dao.AttachmentRepository;
import ouhk.comps380f.dao.CommentRepository;
import ouhk.comps380f.dao.PostRepository;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.CommentNotFound;
import ouhk.comps380f.exception.PostNotFound;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Comment;
import ouhk.comps380f.model.Post;

/**
 *
 * @author LTW
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostRepository postRepository;
    @Resource
    private AttachmentRepository attachmentRepo;
    @Resource
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public List<Post> getPosts(String posttype) {
        return postRepository.findByPosttype(posttype);
    }

    @Override
    @Transactional
    public Post getPost(long postid) {
        return postRepository.findById(postid).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = PostNotFound.class)
    public void delete(long postid) throws PostNotFound {
        Post deletedpost = postRepository.findById(postid).orElse(null);
        List<Comment> c = commentRepository.findByPostid(postid);
        if (deletedpost == null) {
            throw new PostNotFound();
        }
        for (int i = 0; i < c.size(); i++) {
            commentRepository.delete(c.get(i));
        }
        postRepository.delete(deletedpost);
    }

    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long postid) throws AttachmentNotFound {
        Post post = postRepository.findById(postid).orElse(null);
        for (Attachment attachment : post.getAttachments()) {
            post.deleteAttachment(attachment);
            postRepository.save(post);
            return;

        }
        throw new AttachmentNotFound();
    }

    @Override
    @Transactional
    public long createPost(String creater, String topic, String body,
            List<MultipartFile> attachments, String createddate, String posttype) throws IOException {
        Post post = new Post();
        post.setCreater(creater);
        post.setTopic(topic);
        post.setBody(body);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String d = formatter.format(date);
        post.setCreateddate(d);
        post.setPosttype(posttype);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setFilename(filePart.getOriginalFilename());
            attachment.setContent_type(filePart.getContentType());
            attachment.setContent(filePart.getBytes());
            attachment.setPost(post);
            if (attachment.getFilename() != null && attachment.getFilename().length() > 0
                    && attachment.getContent() != null
                    && attachment.getContent().length > 0) {
                post.getAttachments().add(attachment);
            }
        }
        Post savedPost = postRepository.save(post);
        return savedPost.getPostid();
    }

    @Override
    @Transactional(rollbackFor = PostNotFound.class)
    public void updatePost(long postid, String topic,
            String body, List<MultipartFile> attachments)
            throws IOException, PostNotFound {
        Post updatedPost = postRepository.findById(postid).orElse(null);
        if (updatedPost == null) {
            throw new PostNotFound();
        }
        updatedPost.setPosttype(topic);
        updatedPost.setBody(body);
        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setFilename(filePart.getOriginalFilename());
            attachment.setContent_type(filePart.getContentType());
            attachment.setContent(filePart.getBytes());
            attachment.setPost(updatedPost);

            if (attachment.getFilename() != null && attachment.getFilename().length() > 0
                    && attachment.getContent() != null
                    && attachment.getContent().length > 0) {
                updatedPost.getAttachments().add(attachment);
            }
        }
        postRepository.save(updatedPost);
    }

}
