package it.epicode.phronesis.businesslayer.services.impl.report;

import it.epicode.phronesis.businesslayer.dto.report.UserReportRequestDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.businesslayer.services.interfaces.report.UserReportService;
import it.epicode.phronesis.datalayer.entities.report.UserReport;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.datalayer.repositories.reportRepositories.UserReportRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    UserReportRepository userReportRepository;

    @Autowired
    Mapper<UserReport, UserReportResponseDTO> mapUserReportEntityToUserReportResponseDTO;


    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;


    //Questa getAll la uso lato user per recuperare solo le segnalazioni fatte dall'utente loggato
    @Override
    public Page<UserReportResponsePrj> getAllByUserId(Long id, Pageable p) {
        return userReportRepository.findAllByReportedById(id, p);
    }

    //Questa getAll la uso lato admin per recuperare tutte le segnalazioni fatte
    @Override
    public Page<UserReportResponsePrj> getAllReports(Pageable p) {
        return userReportRepository.findAllBy(p);
    }

    @Override
    public UserReportResponseDTO getById(Long id) {
        var userReport = userReportRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return mapUserReportEntityToUserReportResponseDTO.map(userReport);
    }

    @Override
    public UserReportResponseDTO save(UserReportRequestDTO e) {
        var user = usersRepository.findById(e.getReportedById()).orElseThrow(()-> new NotFoundException(e.getReportedById()));
        var userReported = usersRepository.findById(e.getReportedUserId()).orElseThrow(()-> new NotFoundException(e.getReportedUserId()));

        var userReport = UserReport.builder()
                .withReportedBy(user)
                .withReportedUser(userReported)
                .withReason(e.getReason())
                .build();

        return mapUserReportEntityToUserReportResponseDTO.map(userReportRepository.save(userReport));
    }

    @Override
    public UserReportResponseDTO delete(Long id) {
        var userReport = userReportRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        userReportRepository.delete(userReport);
        return mapUserReportEntityToUserReportResponseDTO.map(userReport);
    }
}
