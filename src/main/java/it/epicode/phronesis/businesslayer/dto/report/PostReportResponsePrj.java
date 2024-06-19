package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import it.epicode.phronesis.datalayer.entities.Post;

public interface PostReportResponsePrj extends ReportResponsePrj {

    PostPartialResponseDTO getReportedPost();
}
