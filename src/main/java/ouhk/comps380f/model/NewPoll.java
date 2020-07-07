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
import javax.persistence.Table;

/**
 *
 * @author LTW
 */
@Entity
@Table(name = "poll")
public class NewPoll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;

    public NewPoll() {
    }

    public NewPoll(int id, String question, String ans_a, String ans_b, String ans_c, String ans_d) {
        this.id = id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
    }

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
