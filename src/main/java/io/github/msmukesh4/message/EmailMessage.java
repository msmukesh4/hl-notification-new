package io.github.msmukesh4.message;

import java.io.File;

public class EmailMessage extends Message {
    private String from;
    private String to;
    private String CC;
    private String BCC;
    private String subject;
    private boolean html;
    private File[] attachments;

    public EmailMessage(String message) {
        super(message);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String s) {
        this.to = s;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String cc) {
        this.CC = cc;
    }

    public String getBCC() {
        return BCC;
    }

    public void setBCC(String bcc) {
        this.BCC = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public File[] getAttachments() {
        return attachments;
    }

    public void setAttachments(File[] attachments) {
        this.attachments = attachments;
    }
}
