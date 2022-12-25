package com.test.preproject040.domain.Hashtag.DTO;


import com.test.preproject040.domain.Hashtag.model.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HashtagSaveDto {

    private String tagName;
    public Hashtag toEntity() {
        return  Hashtag.builder()
                .tagName(tagName)
                .build();

    }
}
