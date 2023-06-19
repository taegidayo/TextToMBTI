# TEXT TO MBTI(Frontend)

## 주제
텍스트를 입력하면 MBTI로 변환하여 여러 기능을 수행하는 프로그램

## 제작 기간
2023/03~2023/06

2023년 1학기 선문대학교 모바일프로그래밍 수업에서 제작한 프로그램


## 참여자
장효택,김연희,남영빈

## 사용 기술 및 도구
Android-java
AndroidStudio

## 코드 간단 설명

### Chat.java
 채팅 화면

### ChatMessageWidget.java
채팅 말풍선 위젯
### ChatAdapter.java
채팅위젯을 채팅화면에 추가시킬 Adapter
### DatabaseHelper.java
SQLlite를 사용하기 위한 db헬퍼
### Log.java
사용자가 입력했던 텍스트 데이터 및 결과값을 보여주는 화면
### MainActivity.java
처음 시작하면 나오는 화면. 글을 입력하고 분석하기를 누르면 결과가 나옴

### Result.java
MBTI결과를 그래프화 하여 보여주고, 설명을 보여주는 화면 위젯

## 구조
MainActivity->글 작성 후 분석하기 버튼 클릭->Result-> 채팅하기 버튼 클릭-> Chat화면 실행