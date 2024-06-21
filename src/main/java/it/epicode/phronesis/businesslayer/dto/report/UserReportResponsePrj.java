package it.epicode.phronesis.businesslayer.dto.report;

import it.epicode.phronesis.businesslayer.dto.UserResponsePartialDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.UserPostInteractionResponsePrj;
import it.epicode.phronesis.datalayer.entities.User;

public interface UserReportResponsePrj extends ReportResponsePrj {

    User getReportedUser();

    interface User {
        Long getId();
        String getUsername();
        String getProfilePicture();
    }
}
