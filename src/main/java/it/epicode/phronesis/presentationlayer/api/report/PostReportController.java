package it.epicode.phronesis.presentationlayer.api.report;

import it.epicode.phronesis.businesslayer.dto.report.*;
import it.epicode.phronesis.businesslayer.services.interfaces.report.PostReportService;
import it.epicode.phronesis.presentationlayer.api.exceptions.ApiValidationException;
import it.epicode.phronesis.presentationlayer.api.models.report.PostReportModel;
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
@RequestMapping("api/post-report")
public class PostReportController {

    @Autowired
    PostReportService postReportService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<PostReportResponsePrj>> getAllPostReport (Pageable p) {
        var postReport = postReportService.getAllReports(p);
        return new ResponseEntity<>(postReport, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostReportResponseDTO> getPostReportById (@PathVariable Long id) {
        var postReport = postReportService.getById(id);
        return new ResponseEntity<>(postReport, HttpStatus.OK);
    }

    @GetMapping("reported-by/{id}")
    public ResponseEntity<Page<PostReportResponsePrj>> getAllPostReportByUserId (@PathVariable Long id, Pageable p) {
        var postReport = postReportService.getAllByUserId(id, p);
        return new ResponseEntity<>(postReport, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PostReportResponseDTO> addPostReport(@RequestBody @Validated PostReportModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var postReport = postReportService.save(
                PostReportRequestDTO.postReportRequestBuilder()
                        .withReportedById(model.reportedById())
                        .withReportedPostId(model.reportedPostId())
                        .withReason(model.reason())
                        .build());

        return new ResponseEntity<>(postReport, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PostReportResponseDTO> removePostReport(@PathVariable Long id) {
        var postReport = postReportService.delete(id);
        return new ResponseEntity<>(postReport, HttpStatus.OK);
    }
}
