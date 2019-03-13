package com.limengting.service;

import com.limengting.model.PageBean;
import com.limengting.model.Post;

import java.util.List;


public interface IPostService {

    /**
     * 根据uid获得帖子列表
     * @param uid
     * @return
     */
    List<Post> getPostList(int uid);

    /**
     *
     * @param post
     * @return
     */
    int publishPost(Post post);

    /**
     * 按时间列出帖子
     * @param curPage
     * @return
     */
    PageBean<Post> listPosts(int curPage, String order);

    /**
     *
     * @param pid
     * @return
     */
    Post getPostByPid(int pid);

    /**
     * 点赞
     * @param pid
     * @param sessionUid
     * @return
     */
    String clickLike(int pid, int sessionUid);

    /**
     * 某用户是否赞过某帖子
     * @param pid
     * @param sessionUid
     * @return
     */
    boolean getLikeStatus(int pid, int sessionUid);
}

