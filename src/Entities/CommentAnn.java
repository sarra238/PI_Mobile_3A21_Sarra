/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class CommentAnn {
    int id;
    private String commentAnn;
    private String date;

    public CommentAnn() {
    }

    public CommentAnn(String commentAnn) {
        this.commentAnn = commentAnn;
    }

    public String getCommentAnn() {
        return commentAnn;
    }

    public void setCommentAnn(String commentAnn) {
        this.commentAnn = commentAnn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
