# Fragment 공부



## 특징

* 레이아웃, 동작 처리, 생명주기를 가지는 독립적인 모듈
* 다른 액티비티에서도 사용 가능
* 액티비티 내에서 실행 중 추가 제거 가능
* 프래그먼트는 필수적으로 하나의 액티비티에 종속되어야 함



## 중요한 생명주기 메소드들

**void onAttatch(Activity activity)**

* 프래그먼트가 처음 액티비티에 부착 될 때 호출
* 매개변수에 종속될 액티비티를 전달받음 -> 재사용 가능



**void onCreate(Bundle savedInstanceState)**

	* 필요한 요소를 먼저 초기화 하고 시작
	* 이 단계는 생략 가능하지만 액티비티도 이 단계에서 생성 중에 있기 때문에 참조, 초기화 할 때 불안정함
	* savedInstanceState : 이 변수를 참조하여 이전 내용을 복구함



**View onCreateView(LayoutInflater inflater, VIewGroup container, Bundle savedInstance)**

 * 프래그먼트에 쓰일 view 정의 및 초기화, 프래그먼트는 자신의 레이아웃을 루트 뷰로 설정/inflate 함
 * container를 통해 액티비티의 어느 위치에 자리 잡아야 하는지 전달 받음
 * 프래그먼트 상에 생성된 뷰를 종속 된 액티비티 뷰에 리턴해 줌



**void onActivityCreate(Bundle savedInstanceState)**

 * 액티비티가 완전히 생성된 이후에 프래그먼트의 구성요소를 초기화 (안전성 보장)



**void onPause()**

	* 프래그먼트가 정지 되는 시점을 정의
	* 남겨두거나 영구적으로 보존해야 할 자료들을 저장



# 참고하면 도움되는 자료

https://tedrepository.tistory.com/5  프래그먼트 설명

https://programmingnote.tistory.com/26 프래그먼트 사용방법