/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.CommentRepository;
import ouhk.comps380f.dao.PollRepository;
import ouhk.comps380f.dao.User_Poll_Result_Repo;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Comment;
import ouhk.comps380f.model.NewPoll;
import ouhk.comps380f.model.Poll_Result;
import ouhk.comps380f.model.Post;
import ouhk.comps380f.service.AttachmentService;
import ouhk.comps380f.service.PostService;
import ouhk.comps380f.view.DownloadingView;

/**
 *
 * @author LTW
 */
@Controller
@RequestMapping("/forum")

public class ForumController {

    @Resource
    PollRepository pollRepository;
    @Resource
    User_Poll_Result_Repo user_Poll_Result_Repo;
    @Resource
    CommentRepository commentRepository;

    @Autowired
    private PostService postService;
    @Autowired
    private AttachmentService attachmentService;

    public String[][] temp; //2D array to store all question, answer and polled result for each answer
    public int total_question;

    public void ViewPollResult() {
        List<NewPoll> poll = pollRepository.findAll();

        total_question = poll.size();
        temp = new String[total_question][9];

        for (int i = 0; i < poll.size(); i++) {
            temp[i][0] = poll.get(i).getQuestion();
            List<NewPoll> np = pollRepository.findByQuestion(temp[i][0]);
            temp[i][1] = np.get(0).getAns_a();
            temp[i][2] = np.get(0).getAns_b();
            temp[i][3] = np.get(0).getAns_c();
            temp[i][4] = np.get(0).getAns_d();
            temp[i][5] = Integer.toString(user_Poll_Result_Repo.findByQuestionAndUser_result(temp[i][0], temp[i][1]));
            temp[i][6] = Integer.toString(user_Poll_Result_Repo.findByQuestionAndUser_result(temp[i][0], temp[i][2]));
            temp[i][7] = Integer.toString(user_Poll_Result_Repo.findByQuestionAndUser_result(temp[i][0], temp[i][3]));
            temp[i][8] = Integer.toString(user_Poll_Result_Repo.findByQuestionAndUser_result(temp[i][0], temp[i][4]));
        }

        /*  temp array structure
        [question#1] [Ans A] [Ans B] [Ans C] [Ans D] [num of polled Ans A] [num of polled Ans B] [num of polled Ans C] [num of polled Ans D]
        [question#2] [Ans A] [Ans B] [Ans C] [Ans D] [num of polled Ans A] [num of polled Ans B] [num of polled Ans C] [num of polled Ans D]
        [question#3] [Ans A] [Ans B] [Ans C] [Ans D] [num of polled Ans A] [num of polled Ans B] [num of polled Ans C] [num of polled Ans D]
        ......
        ......
         */
    }

    @GetMapping("/index")
    public String forum(ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

        if (request.isUserInRole("ROLE_USER")) {
            //poll 
            List<NewPoll> theNewPoll = pollRepository.findTopByOrderByIdDesc();
            
            String theNewPollQuestion = theNewPoll.get(0).getQuestion();    //The latest one poll question

            String userName = principal.getName();
            List<Poll_Result> userHasPoll = user_Poll_Result_Repo.findByQuestionAndUsername(theNewPollQuestion, userName);

            if (userHasPoll.isEmpty()) { //if user does not poll, display the poll question
                model.addAttribute("AllPoll", pollRepository.findTopByOrderByIdDesc());
            }
            //show poll result
            ViewPollResult();
            model.addAttribute("AllPollResult", temp);
            model.addAttribute("totalquestion", total_question);
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            //show poll result only, admin can't poll
            ViewPollResult();
            model.addAttribute("AllPollResult", temp);
            model.addAttribute("totalquestion", total_question);
        } else {
            //if no login, show poll result only
            ViewPollResult();
            model.addAttribute("AllPollResult", temp);
            model.addAttribute("totalquestion", total_question);
        }
        return "forum";
    }

    @PostMapping("index/{poll_id}")
    public View user_polled(Poll_Form pf) throws IOException {
        Poll_Result pr = new Poll_Result(pf.getId(), pf.getQuestion(), pf.getUser_result(), pf.getUsername());
        user_Poll_Result_Repo.save(pr);
        return new RedirectView("/forum/index", true);
    }

    public static class Poll_Form {

        private int id;
        private String question;
        private String user_result;
        private String username;

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

        public String getUser_result() {
            return user_result;
        }

        public void setUser_result(String user_result) {
            this.user_result = user_result;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

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

    public static class Reply {

        private int commentid;
        private int postid;
        private String creater;
        private String comment;
        private String createddate;

        public int getCommentid() {
            return commentid;
        }

        public void setCommentid(int commentid) {
            this.commentid = commentid;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
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

    //Start of lecture mapping
    @GetMapping("/lecture")
    public String lecture(ModelMap model) {
        model.addAttribute("AllLecturePost", postService.getPosts("lecture"));
        return "lecture";
    }

    @GetMapping("/lecture/{postid}")
    public String lecture(@PathVariable("postid") long postid, ModelMap model) {
        Post post = postService.getPost(postid);
        Reply reply = new Reply();
        model.addAttribute("post", post);
        model.addAttribute("reply", reply);
        model.addAttribute("AllComment", commentRepository.findByPostid(postid));
        return "viewPost";
    }

    @PostMapping("/lecture/{postid}")
    public View reply_lecture(Reply reply, @PathVariable("postid") String postid) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String d = formatter.format(date);
        Comment c = new Comment(reply.getCommentid(), reply.getPostid(),
                reply.getCreater(), reply.getComment(), d);
        commentRepository.save(c);
        return new RedirectView("/forum/lecture/{postid}", true);
    }

    @GetMapping("lecture/{postid}/attachment/{attachment:.+}")
    public View download_lecture(@PathVariable("postid") long ticketId,
            @PathVariable("attachment") String name) {
        Attachment attachment = attachmentService.getAttachment(ticketId, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getFilename(),
                    attachment.getContent_type(), attachment.getContent());
        }
        return new RedirectView("/ticket/list", true);
    }
    //End of lecture mapping

    //Start of lab mapping
    @GetMapping("/lab")
    public String lab(ModelMap model) {
        model.addAttribute("AllLabPost", postService.getPosts("lab"));
        return "lab";
    }

    @GetMapping("/lab/{postid}")
    public String lab(@PathVariable("postid") long postid, ModelMap model) {
        Post post = postService.getPost(postid);
        Reply reply = new Reply();
        model.addAttribute("post", post);
        model.addAttribute("reply", reply);
        model.addAttribute("AllComment", commentRepository.findByPostid(postid));
        return "viewPost";
    }

    @GetMapping("lab/{postid}/attachment/{attachment:.+}")
    public View download_lab(@PathVariable("postid") long ticketId,
            @PathVariable("attachment") String name) {
        Attachment attachment = attachmentService.getAttachment(ticketId, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getFilename(),
                    attachment.getContent_type(), attachment.getContent());
        }
        return new RedirectView("/ticket/list", true);
    }

    @PostMapping("/lab/{postid}")
    public View reply_lab(Reply reply, @PathVariable("postid") String postid) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String d = formatter.format(date);
        Comment c = new Comment(reply.getCommentid(), reply.getPostid(),
                reply.getCreater(), reply.getComment(), d);
        commentRepository.save(c);
        return new RedirectView("/forum/lab/{postid}", true);
    }
    //End of lab mapping

    //Start of other mapping
    @GetMapping("/other")
    public String other(ModelMap model) {
        model.addAttribute("AllOtherPost", postService.getPosts("other"));
        return "other";
    }

    @GetMapping("/other/{postid}")
    public String other(@PathVariable("postid") long postid, ModelMap model) {
        Post post = postService.getPost(postid);
        Reply reply = new Reply();
        model.addAttribute("post", post);
        model.addAttribute("reply", reply);
        model.addAttribute("AllComment", commentRepository.findByPostid(postid));
        return "viewPost";
    }

    @GetMapping("other/{postid}/attachment/{attachment:.+}")
    public View download_other(@PathVariable("postid") long ticketId,
            @PathVariable("attachment") String name) {
        Attachment attachment = attachmentService.getAttachment(ticketId, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getFilename(),
                    attachment.getContent_type(), attachment.getContent());
        }
        return new RedirectView("/ticket/list", true);
    }

    @PostMapping("/other/{postid}")
    public View reply_other(Reply reply, @PathVariable("postid") String postid) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String d = formatter.format(date);
        Comment c = new Comment(reply.getCommentid(), reply.getPostid(),
                reply.getCreater(), reply.getComment(), d);
        commentRepository.save(c);
        return new RedirectView("/forum/other/{postid}", true);
    }
    //End of other mapping

}
