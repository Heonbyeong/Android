# 프로젝트 Hensel

* route add페이지에서 키보드 엔터누를 때 커서 위치 재조정하기
* detail route 페이지에서 AM PM 표시할 때 낮 12시나 밤 12시같은 경우 생각하기
* 스피너를 이용하여 AM, PM 선택가능하게 하기
* https://ppizil.tistory.com/38  << 리사이클러뷰 개념 바로잡기
* http://www.incree.com/tc/incree/100 << 배경 터치시 키보드 감추기 
* https://dev-imaec.tistory.com/27 << 리사이클러뷰 예제
* https://www.youtube.com/watch?v=7XBX4XZTnd8 << 리사이클러뷰 강의
* https://medium.com/@logishudson0218/intent-flag%EC%97%90-%EB%8C%80%ED%95%9C-%EC%9D%B4%ED%95%B4-d8c91ddd3bfc << intent-flag 이해
* https://kylblog.tistory.com/21 << flag, task 등등 정리
* https://atomic0x90.github.io/android-studio/2020/02/28/android-studio-back-button.html << 뒤로 가기 버튼 2번 입력 후 앱 종료
* https://like-tomato.tistory.com/156 << 액티비티 중복 실행 문제 해결
* https://m.blog.naver.com/PostView.nhn?blogId=booboo84&logNo=30172704750&proxyReferer=https:%2F%2Fwww.google.com%2F << intent시 액티비티 재사용 막기
* https://www.youtube.com/watch?v=pG6OkJ3rSjg << Room 사용 예제(영상)
* https://medium.com/%EC%8A%AC%EA%B8%B0%EB%A1%9C%EC%9A%B4-%EA%B0%9C%EB%B0%9C%EC%83%9D%ED%99%9C/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-room-%EC%82%AC%EC%9A%A9%EB%B2%95-1b7bd07b4cee << Room 사용법(글)
* https://vmao.tistory.com/762 << dialog 배경터치, back키 무력화
* https://stackoverrun.com/ko/q/13111521 << diglog 버튼 색상 커스텀
* https://kobbi-reply.tistory.com/8 << 리사이클러뷰 끊김현상 제거
* https://developer.android.com/jetpack/androidx/releases/recyclerview?hl=ko << setStateRestorationStrategy (지연상태 복원)
* https://stackoverflow.com/questions/28236390/recyclerview-store-restore-state-between-activities << 스크롤 상태저장
* https://ddangeun.tistory.com/68 << Room RecyclerView
* https://dreamaz.tistory.com/63 << dialog AppCompat 오류에 대해

```
recyclerView.setLayoutManager(new LinearLayoutManager(this));
bool = intent.getStringExtra("bool");
if("1".equals(bool)){
    recyclerView.setAdapter(adapter);
    month = intent.getStringExtra("month");
    date = intent.getStringExtra("date");
    memo = intent.getStringExtra("memo");
    address = intent.getStringExtra("address");
    startHour = intent.getStringExtra("startHour");
    startMin = intent.getStringExtra("startMin");
    endHour = intent.getStringExtra("endHour");
    endMin = intent.getStringExtra("endMin");

    intent3.putExtra("memo", memo);
    intent3.putExtra("address", address);
    intent3.putExtra("startH", startHour);
    intent3.putExtra("startM", startMin);
    intent3.putExtra("endH",endHour);
    intent3.putExtra("endM", endMin);
    dataString = (month + "월 " + date + "일");

    adapter.addItem(new Data(dataString, count));
}
```