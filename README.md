# aroundog_server
[aroundog](https://github.com/qqqqlss/arounDog)에서 사용하는 서버
<br><br>

## 데이터베이스
![aroundog](https://user-images.githubusercontent.com/58110946/204068160-ab257cfc-eb57-4d8e-a67c-941de43777a9.png)
|테이블|설명|
| --- | --- |
| walk | 산책 후 저장되는 산책에 대한 정보 |
| walk_deduplication | 중복을 제거한 산책정보 |
| user | 사용자 정보 |
| coordinate | 사용자별 마지막 산책 위치, 산책 여부 |
| user_dog | 사용자의 반려견 정보 |
| dog_img | 강아지 사진, 사진이 저장된 경로 |
| dog | 강아지 종 |
| good | 유저가 좋아요를 누른 산책 |
| bad | 유저가 싫어요를 누른 산책 |
