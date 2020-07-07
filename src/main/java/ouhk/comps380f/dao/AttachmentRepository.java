/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ouhk.comps380f.model.Attachment;

/**
 *
 * @author LTW
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    //public Attachment findByPostIdAndTopic(long postid, String topic);
    //@Query("select a from Attachment a where a.post_id = : postid and a.topic = :topic")
    //Attachment findByPostidAndTopic(@Param("postid") long postid, @Param("topic") String topic);
    //public Attachment findByPostIdAndFilename(long post_id, String filename);
    public Attachment findByPostidAndFilename(long postid, String topic);

}
