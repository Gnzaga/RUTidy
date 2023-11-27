package com.AAACE.RUTidy.model;
//import com.AAACE.RUTidy.Groups.Group;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Invitations")
public class Invitation {
    
    public static final String NOT_SENT = "not sent";
    public static final String SENT = "sent";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    @Id
    @Column(name = "invitationID", length=255)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invitationID;

    @Column(name = "email", length=255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(name = "status", length=255)
    private String status;

    @ManyToOne
    @JoinColumn(name = "groupID")
    private Group group;

    public Invitation() {
        this.status = NOT_SENT;
    }

    public Invitation(String email, Group group) {
        this.email = email;
        this.group = group;
        this.status = SENT;
        this.user = null;
    }

    public int getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(int invitationID) {
        this.invitationID = invitationID;
    }

    public String getEmail() {
        return email;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Group getGroup() {
        return group;
    }

    public String toString(){
        return "Invitation: " + this.email + " " + this.status;
    }

}
