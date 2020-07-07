/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.CommentRepository;
import ouhk.comps380f.dao.NewUserRepository;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.PostRepository;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.PostNotFound;
import ouhk.comps380f.model.NewPoll;
import ouhk.comps380f.model.NewUser;
import ouhk.comps380f.service.PostService;

/**
 *
 * @author LTW
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Resource
    NewUserRepository newUserRepository;
    @Resource
    PollRepository pollRepository;
    @Resource
    PostRepository postRepository;
    @Resource
    CommentRepository commentRepository;
    @Autowired
    PostService postService;
    
    public static class Poll {
        
        private int id;
        private String question;
        private String ans_a;
        private String ans_b;
        private String ans_c;
        private String ans_d;
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getQuestion() {
            return question;
        }
        
        public void setQuestion(String question) {
            this.question = question;
        }
        
        public String getAns_a() {
            return ans_a;
        }
        
        public void setAns_a(String ans_a) {
            this.ans_a = ans_a;
        }
        
        public String getAns_b() {
            return ans_b;
        }
        
        public void setAns_b(String ans_b) {
            this.ans_b = ans_b;
        }
        
        public String getAns_c() {
            return ans_c;
        }
        
        public void setAns_c(String ans_c) {
            this.ans_c = ans_c;
        }
        
        public String getAns_d() {
            return ans_d;
        }
        
        public void setAns_d(String ans_d) {
            this.ans_d = ans_d;
        }
        
    }
    
    public static class Form {
        
        private String username;
        private String password;
        private String role;
        
        public String getUsername() {
            return username;
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
        
        public String getPassword() {
            return password;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
        
        public String getRole() {
            return role;
        }
        
        public void setRole(String role) {
            this.role = role;
        }
        
    }
    
    @GetMapping("add_user")
    public ModelAndView admin_add_user() {
        return new ModelAndView("add_user", "newuser_admin", new Form());
    }
    
    @PostMapping("add_user")
    public View admin_add_user(Form form) throws IOException {
        NewUser admin_add = new NewUser(form.getUsername(), form.getPassword(), form.getRole());
        newUserRepository.save(admin_add);
        return new RedirectView("/admin/manage_ac", true);
    }
    
    @GetMapping("update_user/{username}")
    public String update_user(@PathVariable("username") String username, ModelMap model) {
        model.addAttribute("pre_update_user", newUserRepository.findById(username).orElse(null));
        return "update_user";
    }
    
    @PostMapping("update_user/{username}")
    public View update_user(Form form) throws IOException {
        NewUser n = new NewUser(form.getUsername(), form.getPassword(), form.getRole());
        newUserRepository.save(n);
        return new RedirectView("/admin/manage_ac", true);
    }
    
    @GetMapping("remove_user/{username}")
    public View remove_user(@PathVariable("username") String username) {
        newUserRepository.delete(newUserRepository.findById(username).orElse(null));
        return new RedirectView("/admin/manage_ac", true);
    }
    
    @GetMapping("/manage_post")
    public String manage_post() {
        return "manage_post";
    }
    
    @GetMapping("/manage_ac")
    public String list(ModelMap model) {
        model.addAttribute("ForumAllUser", newUserRepository.findAll());
        return "manage_ac";
    }
    
    @GetMapping("/create_poll")
    public ModelAndView create() {
        return new ModelAndView("create_poll", "poll_question", new Poll());
    }
    
    @PostMapping("/create_poll")
    public View create(Poll poll) throws IOException {
        NewPoll p = new NewPoll(poll.getId(), poll.getQuestion(),
                poll.getAns_a(), poll.getAns_b(), poll.getAns_c(), poll.getAns_d());
        pollRepository.save(p);
        return new RedirectView("/forum/index", true);
    }
    
    @GetMapping("/remove_post")
    public String remove() {
        return "remove_post";
    }
    
    @GetMapping("/remove_post/{posttype}")
    public String remove_list(@PathVariable("posttype") String posttype, ModelMap model) {
        model.addAttribute("AllPost", postRepository.findByPosttype(posttype));
        return "/remove_post";
    }
    
    @GetMapping("/remove_post/{posttype}/{postid}")
    public View removePost(@PathVariable("posttype") String posttype,
            @PathVariable("postid") long postid, ModelMap model) throws AttachmentNotFound, PostNotFound {
        //commentRepository.deleteById(postid);
        postService.delete(postid);
        //postService.deleteAttachment(postid);
        //postRepository.deleteById(postid);
        return new RedirectView("/admin/remove_post/" + posttype, true);
    }
    
    @GetMapping("/deleteComment/{commentid}")
    public String remove_comment(@PathVariable("commentid") long commentid, HttpServletRequest request) {
        commentRepository.deleteById(commentid);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
    
}
