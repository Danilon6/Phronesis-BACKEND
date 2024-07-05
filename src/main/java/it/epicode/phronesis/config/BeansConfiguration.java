package it.epicode.phronesis.config;

import com.cloudinary.Cloudinary;
import it.epicode.phronesis.businesslayer.dto.*;
import it.epicode.phronesis.businesslayer.dto.post.PostPartialResponseDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostRequestDTO;
import it.epicode.phronesis.businesslayer.dto.post.PostResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.PostReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.report.UserReportResponseDTO;
import it.epicode.phronesis.businesslayer.dto.userPostInteraction.*;
import it.epicode.phronesis.businesslayer.services.interfaces.Mapper;
import it.epicode.phronesis.datalayer.entities.*;
import it.epicode.phronesis.datalayer.entities.report.PostReport;
import it.epicode.phronesis.datalayer.entities.report.UserReport;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Comment;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Favorite;
import it.epicode.phronesis.datalayer.entities.userPostInteraction.Like;
import it.epicode.phronesis.datalayer.repositories.PostRepository;
import it.epicode.phronesis.datalayer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
		int page = 0;
		int size = 10;
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
	Mapper<RegisterUserDTO, User> mapRegisterUserToUserEntity() {
		return (input) -> User.builder()
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withPassword(input.getPassword())
				.withBio(input.getBio())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, RegisteredUserDTO> mapUserEntityToRegisteredUser() {
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
	Mapper<User, LoginResponseDTO> mapUserEntityToLoginResponse() {
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
	Mapper<User, UserResponsePartialDTO> mapUserEntityToUserResponsePartialDTO () {
		return (input) -> UserResponsePartialDTO.builder()
				.withId(input.getId())
				.withUsername(input.getUsername())
				.withProfilePicture(input.getProfilePicture())
				.build();
	}

	//Mapper per il follow
	@Bean
	@Scope("singleton")
	Mapper<Follow, FollowResponseDTO> mapFollowEntityToFollowResponseDTO() {
		return (input) -> FollowResponseDTO.builder()
				.withFollower(
						mapUserEntityToUserResponsePartialDTO().map(input.getFollower())
				)
				.withFollowing(
						mapUserEntityToUserResponsePartialDTO().map(input.getFollowing())
				)
				.build();
	}

	//Mapper per i roles
	@Bean
	@Scope("singleton")
	Mapper<RolesRequestDTO, Roles> mapRolesRequestDTOToRolesEntity () {
		return (input) -> Roles.builder()
				.withRoleType(input.getRoleType())
				.build();
	}
	@Bean
	@Scope("singleton")
	Mapper<Roles, RolesResponseDTO> mapRolesEntityToRolesResponseDTO () {
		return (input) ->
				RolesResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withRoleType(input.getRoleType())
				.build();
	}

	//mapper per l'advert
	@Bean
	@Scope
	Mapper<AdvertRequestDto, Advert> mapAdvertRequestDTOToAdvert () {
		return (input) -> Advert.builder()
				.withTitle(input.getTitle())
				.withDescription(input.getDescription())
				.build();
	}

	@Bean
	@Scope
	Mapper<Advert, AdvertResponseDto> mapAdvertEntityToAdvertResponseDto () {
		return (input) -> AdvertResponseDto.builder()
				.withId(input.getId())
				.withTitle(input.getTitle())
				.withDescription(input.getDescription())
				.withImageUrl(input.getImageUrl())
				.build();
	}


	//mapper per i post
	@Bean
	@Scope("singleton")
	Mapper<PostRequestDTO, Post> mapPostRequestDTOToPost () {
		return (input) -> Post.builder()
				.withTitle(input.getTitle())
				.withContent(input.getContent())
				.build();
	}


	@Bean
	@Scope("singleton")
	Mapper<Post, PostResponseDTO> mapPostToPostResponseDTO() {
		return (input) -> PostResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withTitle(input.getTitle())
				.withContent(input.getContent())
				.withUser(mapUserEntityToUserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<Post, PostPartialResponseDTO> mapPostToPostPartialResponseDTO() {
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
				.withUser(mapUserEntityToUserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	//Mapper per i like
	@Bean
	@Scope("singleton")
	Mapper<Like, UserPostInteractionResponseDTO> mapLikeEntityToUserPostInteractionResponseDTO() {
		return (input) -> UserPostInteractionResponseDTO.builder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withUser(mapUserEntityToUserResponsePartialDTO().map(input.getUser()))
				.build();
	}

	//Mapper per i preferiti
	@Bean
	@Scope("singleton")
	Mapper<Favorite, FavoriteResponseDTO> mapFavoriteEntityToFavoriteResponseDTO(){
		return (input) -> FavoriteResponseDTO.favoriteResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withUser(mapUserEntityToUserResponsePartialDTO().map(input.getUser())
				)
				.withPost(mapPostToPostResponseDTO().map(input.getPost()))
				.build();
	}

	//Mapper per i report
	@Bean
	@Scope("singleton")
	Mapper<PostReport, PostReportResponseDTO> mapPostReportEntityToPostReportResponseDTO(){
		return (input) -> PostReportResponseDTO.postReportResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withReason(input.getReason())
				.withReportedBy(mapUserEntityToUserResponsePartialDTO().map(input.getReportedBy()))
				.withReportedPost(mapPostToPostPartialResponseDTO().map(input.getReportedPost()))
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<UserReport, UserReportResponseDTO> mapUserReportEntityToUserReportResponseDTO(){
		return (input) -> UserReportResponseDTO.userReportResponseBuilder()
				.withId(input.getId())
				.withCreatedAt(input.getCreatedAt())
				.withUpdatedAt(input.getUpdatedAt())
				.withReason(input.getReason())
				.withReportedBy(mapUserEntityToUserResponsePartialDTO().map(input.getReportedBy()))
				.withReportedUser(mapUserEntityToUserResponsePartialDTO().map(input.getReportedUser()))
				.build();
	}

}
