package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "messageFiles")
public class MessageFile {
    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    @Column(name = "file")
    private String file;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messageId")
    private Message message;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
