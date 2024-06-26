package it.epicode.phronesis.businesslayer.dto;

public interface AdvertResponsePrj extends BaseProjection{
    Long getId();
    String getTitle();
    String getDescription();
    String getImageUrl();

}
