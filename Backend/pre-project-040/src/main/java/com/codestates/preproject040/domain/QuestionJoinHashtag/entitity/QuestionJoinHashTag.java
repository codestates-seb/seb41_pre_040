package com.codestates.preproject040.domain.QuestionJoinHashtag.entitity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/*
* HashTag와  Questionr 테이블과의 매핑을 위한 table
* */
@Getter
@Entity
@NoArgsConstructor()
@Table(indexes = @Index(columnList = "questioId"))
public class QuestionJoinHashTag {
}
