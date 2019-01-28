package com.limengting.service;

import com.limengting.model.Reply;

import java.util.List;


public interface IReplyService {
    /**
     * 回复
     * @param sessionUid
     * @param pid
     * @param content
     */
    void reply(int sessionUid, int pid, String content);

    /**
     * 评论
     * @param pid
     * @param sessionUid
     * @param rid
     * @param content
     */
    void comment(int pid, int sessionUid, int rid, String content);

    /**
     * 根据pid列出回复
     * @param pid
     * @return
     */
    List<Reply> listReply(int pid);
}

