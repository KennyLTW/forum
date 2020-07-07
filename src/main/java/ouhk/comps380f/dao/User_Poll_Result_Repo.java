/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ouhk.comps380f.model.Poll_Result;

/**
 *
 * @author LTW
 */
public interface User_Poll_Result_Repo extends JpaRepository<Poll_Result, String> {

    //Find the record who polled or not for the new one poll
    List<Poll_Result> findByQuestionAndUsername(String question, String username);

    //Find out there are how many question in database
    //@Query("select pr.question from Poll_Result pr group by pr.question")
    @Query("select DISTINCT pr.question from Poll_Result pr ")
    String[] findByQuestion();

    @Query("select count(*) from Poll_Result where question =?1 and user_result =?2")
    int findByQuestionAndUser_result(String question, String user_result);


}
