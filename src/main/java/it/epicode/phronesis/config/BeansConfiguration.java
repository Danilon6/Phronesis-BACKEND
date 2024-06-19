package it.epicode.phronesis.config;

import com.cloudinary.Cloudinary;
import it.epicode.phronesis.businesslayer.dto.*;
import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.PostReportRequestDTO;
import it.epicode.phronesis.businesslayer.dto.report.PostReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.ReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.*;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.datalayer.entities.Post;
import it.epicode.phronesis.datalayer.entities.Roles;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.datalayer.entities.report.PostReport;
import it.epicode.phronesis.datalayer.entities.report.Report;
import it.epicode.phronesis.datalayer.entities.report.UserReport;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Favorite;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.UserPostInteraction;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import it.epicode.phronesis.presentationlayer.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;
import java.util.Properties;


@Configuration
public class BeansConfiguration {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PostRepository postRepository;

	@Value("${cloudinary.url}")
	private String cloudinaryUrl;

	@Bean
	public Pageable defaultPageable() {
		int page = 0; // Numero di pagina predefinito (prima pagina)
		int size = 10; // Dimensione della pagina predefinita
		return PageRequest.of(page, size);
	}

	@Bean
	public Cloudinary cloudinary() {
        return new Cloudinary(cloudinaryUrl);
	}


	@Bean
	public JavaMailSender getJavaMailSender(@Value("${gmail.mail.transport.protocol}" ) String protocol,
											@Value("${gmail.mail.smtp.auth}" ) String auth,
											@Value("${gmail.mail.smtp.starttls.enable}" )String starttls,
											@Value("${gmail.mail.debug}" )String debug,
											@Value("${gmail.mail.from}" )String from,
											@Value("${gmail.mail.from.password}" )String password,
											@Value("${gmail.smtp.ssl.enable}" )String ssl,
											@Value("${gmail.smtp.host}" )String host,
											@Value("${gmail.smtp.port}" )String port){

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(Integer.parseInt(port));

		mailSender.setUsername(from);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.debug", debug);
		props.put("smtp.ssl.enable",ssl);

		return mailSender;
	}

	//Configurazione dei mapper per l'utente
	@Bean
	@Scope("singleton")
	Mapper<RegisterUserDTO, User> mapRegisterUser2UserEntity() {
		return (input) -> User.builder()
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withPassword(input.getPassword())
				.withBio(input.getBio())
				.withProfilePicture(input.getProfilePicture())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, RegisteredUserDTO> mapUserEntity2RegisteredUser() {
		return (input) -> RegisteredUserDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withBio(input.getBio())
				.withProfilePicture(input.getProfilePicture())
				.withRoles(input.getRoles())
				.withEnabled(input.isEnabled())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, LoginResponseDTO> mapUserEntity2LoginResponse() {
		return (input) -> LoginResponseDTO.builder()
				.withUser(RegisteredUserDTO.builder()
						.withId(input.getId())
						.withCreatedAt(input.getCreatedAt())
						.withUpdatedAt(input.getUpdatedAt())
						.withFirstName(input.getFirstName())
						.withLastName(input.getLastName())
						.withUsername(input.getUsername())
						.withEmail(input.getEmail())
						.withUsername(input.getUsername())
						.withBio(input.getBio())
						.withProfilePicture(input.getProfilePicture())
						.withRoles(input.getRoles())
						.withEnabled(input.isEnabled())
						.build())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, UserResponsePartialDTO> mapUserEntity2UserResponsePartialDTO () {
		return (input) -> UserResponsePartialDTO.builder()
				.withId(input.getId())
				.withUsername(input.getUsername())
				.withProfilePicture(input.getProfilePicture())
				.build();
	}

	//Mapper per i roles
	@Bean
	@Scope("singleton")
	Mapper<RolesRequestDTO, Roles> mapRolesRequestDTO2RolesEntity () {
		return (input) -> Roles.builder()
				.withRoleType(input.getRoleType())
				.build();
	}
	@Bean
	@Scope("singleton")
	Mapper<Roles, RolesResponseDTO> mapRolesEntity2RolesResponseDTO () {
		return (input) ->
				RolesResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withRoleType(input.getRoleType())
				.build();
	}


	//mapper per i post
	@Bean
	@Scope("singleton")
	Mapper<PostRequestDTO, Post> mapPostRequestDTO2Post () {
		return (input) -> Post.builder()
				.withTitle(input.getTitle())
				.withContent(input.getContent())
				.build();
	}


	@Bean
	@Scope("singleton")
	Mapper<Post, PostResponseDTO> mapPost2PostResponseDTO() {
		return (input) -> PostResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withTitle(input.getTitle())
				.withContent(input.getContent())
				.withImageUrl(input.getImageUrl())
				.withUser(mapUserEntity2UserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<Post, PostPartialResponseDTO> mapPost2PostPartialResponseDTO() {
		return (input) -> PostPartialResponseDTO.builder()
				.withId(input.getId())
				.withTitle(input.getTitle())
				.build();
	}

	//Mapper per i commenti
	@Bean
	@Scope("singleton")
	Mapper<Comment, CommentResponseDTO> mapCommentEntityToCommentResponseDTO () {
		return (input) -> CommentResponseDTO.commentResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withContent(input.getContent())
				.withUser(mapUserEntity2UserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	//Mapper per i like
	@Bean
	@Scope("singleton")
	Mapper<Like, UserPostInteractionResponseDTO> mapLikeEntity2UserPostInteractionResponseDTO() {
		return (input) -> UserPostInteractionResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withUser(mapUserEntity2UserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	//Mapper per i preferiti
	@Bean
	@Scope("singleton")
	Mapper<Favorite, FavoriteResponseDTO> mapFavoriteEntity2FavoriteResponseDTO(){
		return (input) -> FavoriteResponseDTO.favoriteResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withUser(mapUserEntity2UserResponsePartialDTO().map(input.getUser())
				)
				.withPost(mapPost2PostResponseDTO().map(input.getPost()))
				.build();
	}

	//Mapper per i report
	@Bean
	@Scope("singleton")
	Mapper<PostReport, PostReportResponseDTO> mapPostReportEntity2PostReportResponseDTO(){
		return (input) -> PostReportResponseDTO.postReportResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withReason(input.getReason())
				.withReportedBy(mapUserEntity2UserResponsePartialDTO().map(input.getReportedBy()))
				.withReportedPost(mapPost2PostPartialResponseDTO().map(input.getReportedPost()))
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<UserReport, UserReportResponseDTO> mapUserReportEntity2UserReportResponseDTO(){
		return (input) -> UserReportResponseDTO.userReportResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withReason(input.getReason())
				.withReportedBy(mapUserEntity2UserResponsePartialDTO().map(input.getReportedBy()))
				.withReportedUser(mapUserEntity2UserResponsePartialDTO().map(input.getReportedUser()))
				.build();
	}

}
