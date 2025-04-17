package com.example.dbs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@IdClass(ClubMembershipId.class)
public class ClubMembership {

    @Id
    private String stuEmail;  // student email (FK to Student)

    @Id
    private String clubName;  // club name (FK to Club)

    @ManyToOne
    @JoinColumn(name = "stu_email", referencedColumnName = "email", insertable = false, updatable = false)
    private Student student;  // relationship to Student

    @ManyToOne
    @JoinColumn(name = "club_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Club club;  // relationship to Club


    public ClubMembership() {
    }

    public ClubMembership(String stuEmail, String clubName, Student student, Club club) {
        this.stuEmail = stuEmail;
        this.clubName = clubName;
        this.student = student;
        this.club = club;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
