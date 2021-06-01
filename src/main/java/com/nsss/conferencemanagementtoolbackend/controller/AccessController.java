package com.nsss.conferencemanagementtoolbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/access")
public class AccessController {
    @GetMapping("/all/home")
    public String allHomeAccess() {
        return "This is where general users land. Display conference details here.";
    }

    @GetMapping("/all/rp")
    public String allRPAccess() {
        return "This is where general users land. Details about Research Presentations.";
    }

    @GetMapping("/all/wp")
    public String allWPAccess() {
        return "This is where general users land. Details about Workshop Presentations.";
    }

    @GetMapping("/all/download")
    public String allDownloadAccess() {
        return "This is where general users land. All template download files here.";
    }

    @GetMapping("/all/contactus")
    public String allContactUsAccess() {
        return "This is where general users land. 'Contact Us' details here.";
    }

    @GetMapping("/payment")
    public String paymentGateway() {
        return "This is the payment gateway.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board. Manage all stats here. Approve changes made to conference details by editor.";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasRole('EDITOR')")
    public String editorAccess() {
        return "Editor Board. Edit conference details here.";
    }

    @GetMapping("/reviewer/rp")
    @PreAuthorize("hasRole('REVIEWER')")
    public String reviewerRPAccess() {
        return "Reviewer Research Report Viewer.";
    }

    @GetMapping("/reviewer/wp")
    @PreAuthorize("hasRole('REVIEWER')")
    public String reviewerWPAccess() {
        return "Reviewer Workshop PowerPoint Viewer.";
    }

    @GetMapping("/rp")
    @PreAuthorize("hasRole('RP')")
    public String rpAccess() {
        return "RP Board. Display approval status of submitted Research Report. Option to make payment if approved.";
    }

    @GetMapping("/wp")
    @PreAuthorize("hasRole('WP')")
    public String wpAccess() {
        return "WP Board. Display approval status of submitted Workshop PowerPoint";
    }

    @GetMapping("/attendee")
    @PreAuthorize("hasRole('ATTENDEE')")
    public String attendeeAccess() {
        return "Attendee Board. Just a message saying something like 'See You At The Convo' and a countdown to the date";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR') or hasRole('REVIEWER') or " +
            "hasRole('RP') or hasRole('WP') or hasRole('ATTENDEE')")
    public String userAccess() {
        return "User Content. Common for all Registered users. Conference Details Here.";
    }
}
