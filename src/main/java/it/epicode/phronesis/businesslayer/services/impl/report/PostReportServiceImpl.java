package it.epicode.phronesis.businesslayer.services.impl.report;

import it.epicode.phronesis.businesslayer.dto.report.PostReportRequestDTO;
import it.epicode.phronesis.businesslayer.dto.report.PostReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.PostReportResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.report.PostReportService;
import it.epicode.phronesis.datalayer.entities.report.PostReport;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.datalayer.repositories.reportRepositories.PostReportRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostReportServiceImpl implements PostReportService {

    @Autowired
    PostReportRepository postReportRepository;

    @Autowired
    Mapper<PostReport, PostReportResponseDTO> mapPostReportEntity2PostReportResponseDTO;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;

    //Questa getAll la uso lato user per recuperare solo le segnalazioni fatte dall'utente loggato
    @Override
    public Page<PostReportResponsePrj> getAllByUserId(Long id, Pageable p) {
        return postReportRepository.findAllByReportedById(id, p);
    }

    //Questa getAll la uso lato admin per recuperare tutte le segnalazioni fatte
    @Override
    public Page<PostReportResponsePrj> getAllReports(Pageable p) {
        return postReportRepository.findAllBy(p);
    }

    @Override
    public PostReportResponseDTO getById(Long id) {
        var postReport = postReportRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return mapPostReportEntity2PostReportResponseDTO.map(postReport);
    }

    @Override
    public PostReportResponseDTO save(PostReportRequestDTO e) {
        var user = usersRepository.findById(e.getReportedById()).orElseThrow(()-> new NotFoundException(e.getReportedById()));
        var post = postRepository.findById(e.getReportedPostId()).orElseThrow(()-> new NotFoundException(e.getReportedPostId()));

        var postReport = PostReport.builder()
                .withReportedBy(user)
                .withReportedPost(post)
                .withReason(e.getReason())
                .build();
        return mapPostReportEntity2PostReportResponseDTO.map(postReportRepository.save(postReport));
    }

    @Override
    public PostReportResponseDTO delete(Long id) {
        var postReport = postReportRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        postReportRepository.delete(postReport);
        return mapPostReportEntity2PostReportResponseDTO.map(postReport);
    }
}
