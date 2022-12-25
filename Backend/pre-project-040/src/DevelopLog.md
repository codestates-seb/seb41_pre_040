# Hashtag 데이터 테이블

## 매핑
    Questions와 Hashtag 간의 관계는 다대다 매핑이라고
    생각된다. 
    ex> q1 #1 # 4 #5
        q2 #3 # 4 # 6
        q4 #4 # 1 # 6

    그렇기에 중간에 메핑 테이블을 따로 생성하는 것이 좋다.

## 해시태그 생성 로직
    1. Questions를 생성하는 동시에 content 필드르 가져와
    정규식을 활용하여 해시태그 리스를 만듦

    2. 해시태그 리스트를 보면서 해시태그 조회
    기존 HashTag 테이블에 존재하지 않는 데이터라면 Hashtag, HashTagPostMapping
    테이블에 삽입, 이미 있던 해시태그라면 tagPostMapping에 전달

    3. Tag 테이블에 필드 값을 증가