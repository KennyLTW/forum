/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author LTW
 */
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postid;

    private String posttype;
    private String topic;
    private String creater;
    private String createddate;
    private String body;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Attachment> attachments = new ArrayList<>();
    //private Map<String, Attachment> attachments = new Hashtable<>();

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public void deleteAttachment(Attachment attachment) {
        attachment.setPost(null);
        this.attachments.remove(attachment);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

}
