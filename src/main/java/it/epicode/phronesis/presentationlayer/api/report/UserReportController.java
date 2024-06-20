package it.epicode.phronesis.presentationlayer.api.report;

import it.epicode.phronesis.businesslayer.dto.report.UserReportRequestDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.report.UserReportService;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.report.UserReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-report")
public class UserReportController {

    @Autowired
    UserReportService userReportService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<UserReportResponsePrj>> getAllUserReport (Pageable p) {
        var userReport = userReportService.getAllReports(p);
        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserReportResponseDTO> getUserReportById (@PathVariable Long id) {
        var userReport = userReportService.getById(id);
        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }

    @GetMapping("reported-by/{id}")
    public ResponseEntity<Page<UserReportResponsePrj>> getAllUserReportByUserId (@PathVariable Long id, Pageable p) {
        var userReport = userReportService.getAllByUserId(id, p);
        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserReportResponseDTO> addUserReport(@RequestBody @Validated UserReportModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var userReport = userReportService.save(
                UserReportRequestDTO.userReportRequestBuilder()
                        .withReportedById(model.reportedById())
                        .withReportedUserId(model.reportedUserId())
                        .withReason(model.reason())
                        .build());

        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserReportResponseDTO> removeUserReport(@PathVariable Long id) {
        var userReport = userReportService.delete(id);
        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }
}
