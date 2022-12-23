package com.codestates.preproject040.domain.Hashtag.dto;

import com.codestates.preproject040.domain.Hashtag.entitiy.Hashtag;

import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.time.LocalDateTime;


public record HashTagdto(
        BigInteger id,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modified_at,
        String modified_by
) {
    public static HashTagdto of(BigInteger id,
                                String hashtag,
                                LocalDateTime createdAt,
                                String createdBy,
                                LocalDateTime modified_at,
                                String modified_by ){
        return new HashTagdto(null, hashtag, createdAt, createdBy,modified_at,modified_by);

    }

    //entity에서 데이터를 call
    public static HashTagdto from(Hashtag entity) {
        return new HashTagdto(
                entity.getId(),
                entity.getContent(),

                )


    }

    //entity로 데이러를 transfer
    public Hashtag toEntity() {
        return Hashtag().of(
          id,
          hashtag
        );
    }
}


