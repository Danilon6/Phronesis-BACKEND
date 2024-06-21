package it.epicode.phronesis.businesslayer.dto.report;


public interface PostReportResponsePrj extends ReportResponsePrj {

    Post getReportedPost();

    interface Post {
        Long getId();
        String getTitle();
    }
}
