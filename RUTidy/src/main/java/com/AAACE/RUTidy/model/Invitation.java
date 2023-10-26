package com.AAACE.RUTidy.model;
//import com.AAACE.RUTidy.Groups.Group;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Invitations")
public class Invitation {
    
    final String NOT_SENT = "not sent";
    final String SENT = "sent";
    final String ACCEPTED = "accepted";
    final String REJECTED = "rejected";

    @Id
    @Column(name = "invitationID", length=255)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invitationID;

    @Column(name = "email", length=255)
    private String email;

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

    public Group getGroup() {
        return group;
    }

    public String toString(){
        return "Invitation: " + this.email + " " + this.status;
    }

}
