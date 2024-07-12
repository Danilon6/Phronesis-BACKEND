package it.epicode.phronesis.businesslayer.dto.report;



public interface UserReportResponsePrj extends ReportResponsePrj {

    User getReportedUser();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
        boolean getBanned();
    }
}
