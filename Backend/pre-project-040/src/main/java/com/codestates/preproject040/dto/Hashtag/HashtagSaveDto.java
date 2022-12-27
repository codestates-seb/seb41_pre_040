package com.codestates.preproject040.dto.Hashtag;

import com.codestates.preproject040.domain.Hashtag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HashtagSaveDto {

    private String tagName;
    public Hashtag toEntity(){
        return Hashtag.builder()
                .tagName(tagName)
                .build();
    }
}
