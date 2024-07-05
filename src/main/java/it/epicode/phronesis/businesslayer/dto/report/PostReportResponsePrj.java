package it.epicode.phronesis.businesslayer.dto.report;


public interface PostReportResponsePrj extends ReportResponsePrj {

    Post getReportedPost();

    interface Post {
        Long getId();
        String getTitle();
        User getUser();
        interface User {
            Long getId();
            String getUsername();
            String getProfilePicture();
        }
    }
}
