package it.epicode.phronesis.businesslayer.services.interfaces;

public interface Mapper<D,S > {
    S map(D input);
}

