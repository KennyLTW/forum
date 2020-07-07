/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author LTW
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentid;
    private long postid;    //int -> long
    private String creater;
    private String comment;
    private String createddate;

    public Comment() { }
    
    public Comment(long commentid, long postid, String creater, String comment, String createddate) {
        this.commentid = commentid;
        this.postid = postid;
        this.creater = creater;
        this.comment = comment;
        this.createddate = createddate;
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

}
