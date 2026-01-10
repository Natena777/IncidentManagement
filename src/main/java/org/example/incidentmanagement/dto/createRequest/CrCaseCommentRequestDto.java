package org.example.incidentmanagement.dto.createRequest;


public class CrCaseCommentRequestDto {


    private String commentText;
    private String commentType;


    public CrCaseCommentRequestDto() {}


    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }
}
