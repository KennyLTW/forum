/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Post;
import ouhk.comps380f.service.AttachmentService;
import ouhk.comps380f.service.PostService;

/**
 *
 * @author LTW
 */
@Controller
@RequestMapping("/forum/other")
public class OtherController {

    @Autowired
    private PostService postService;
    @Autowired
    private AttachmentService attachmentService;

    /*
    @GetMapping("/openpost")
    public String openpost() {
        return "openpost";
    }
     */
    @GetMapping("/openpost")
    public ModelAndView create() {
        return new ModelAndView("openpost", "postForm", new Form());
    }

    @PostMapping("/openpost")
    public View create_post(Form form) throws IOException {
        long postid = postService.createPost(form.getCreater(), form.getTopic(),
                form.getBody(), form.getAttachments(), form.getCreateddate(),"other");

        return new RedirectView("/forum/other/", true);

    }

    public static class Form {

        private String posttype;
        private String topic;
        private String creater;
        private String createddate;
        private String body;
        private List<MultipartFile> attachments;

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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }

}
