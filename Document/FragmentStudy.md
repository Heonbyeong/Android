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



## 프래그먼트 관리 (Fragment manager)

**액티비티와 프레그먼트 중간에서 서로를 이어주는 역할**



**프레그먼트 트랜잭션 (Transaction)**

- 어떤 대상에 대해 추가, 제거, 변경 등의 작업들이 발생(프래그먼트를 추가, 삭제 및 교체) 하는 것을 묶어 말함

- 행해진 트랜잭션 상태를 프래그먼트 백스택(Backstack)을 통해 저장할 수 있음



## 프래그먼트 트랜잭션 (Fragment Transaction)



### 프래그먼트 트랜잭션 설정

* FragmentManager를 통해 FragmentTransaction의 인스턴스를 가져올 수 있음

``` java
//프래그먼트 매니저 선언
FragmentManager fragmentManager = getSupportFragmentManager();

//프래그먼트 트랜잭션 시작
FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

.... // 여기에서 프래그먼트 트랜잭션, 백스택, 애니메이션 등을 설정
    
//프래그먼트 트랜잭션 마무리
fragmentTransaction.commit();

출처: https://tedrepository.tistory.com/6?category=707850
```

* 매니저 선언 후 **beginTransaction()**을 호출 함으로써 프래그먼트 트랜잭션 작업을 시작 할 수 있음

* 트랜잭션 작업과 애니메이션, 프래그먼트 백스택 등을 추가 한 뒤 **commit()**을 호출하여 트랜잭션 마무리

  **(이때 반드시 commit()을 호출해야 트랜잭션 작업을 정상적으로 수행할 수 있다.)**



### 프래그먼트 트랜잭션을 이용한 프래그먼트 추가

**트랜잭션을 위한 메소드는 add() / remove() / replace()가 있고, 각각 추가 / 삭제 / 전환을 의미**



**[트랜잭션을 이용하여 container에 삽입하는 코드]**

```java
// 추가 시켜 줄 fragment 를 생성
FirstFragment firstFragment = new FirstFragment();

FragmentManager fragmentManager = getSupportFragmentManager();
FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

// parameter1 : activity 내에서 fragment 를 삽입할 Layout id
// parameter2 : 삽입할 fragment
fragmentTransaction.add(R.id.fragment_container, firstFragment);

fragmentTransaction.commit();

출처: https://tedrepository.tistory.com/6?category=707850
```



### 프래그먼트 백스택 (Fragment BackStack)

**프래그먼트 백스택이란 현재 실행하려는 트랜잭션 상태를 기억해두기 위해 만들어진 개념**

* 스마트폰의 Back key를 통해 프래그먼트를 이전 상태로 되돌릴 수 있음 (액티비티 사이를 Back key로 돌아갈 수 있는 것과 비슷)

(transaction block 안에 addToBackStack()메소드를 호출하면 된다)

**[프래그먼트 백스택 구현 코드]**

```java
// manager 와 transaction 초기화
FragmentManager manager = getSupportFragmentManager();
FragmentTransaction transaction = manager.beginTransaction();

// 전달받은 fragment 를 replace
transaction.replace(R.id.fragment_container, fragment);

// 해당 transaction 을 Back Stack 에 저장
transaction.addToBackStack(null);

// transaction 마무리
transaction.commit();

출처: https://tedrepository.tistory.com/6?category=707850
```



# 액티비티와의 통신

**액티비티에서 발생한 이벤트가 하나의 프래그먼트에 속한 특정 UI에 변화를 주는 방법**



## 액티비티와 프래그먼트, 서로의 자원에 직접 접근하기

FragmentManager에 속한 **findFragmentById()** 또는 **findFragmentByTag()**로 원하는 참조를 가져올 수 있고, 해당 프래그먼트의 함수, 변수에 접근할 수 있게 됨



**findFragmentById() / findFragmentByTag() 차이점**

ById : 레이아웃이 있는 프래그먼트

ByTag : 레이아웃이 없는 프래그먼트



### 액티비티에서 프래그먼트 자원 접근

```java
FirstFragment fragment = (FirstFragment)getSupportFragmentManager().findFragmentById(R.id.first_fragment);

출처: https://tedrepository.tistory.com/6?category=707850 [Ted's IT Repository]
```



아래와 같이 생성한 프래그먼트에 대해서는 자원에 직접 접근 할 수 없음

```java
mFirstFragment = new FirstFragment();

출처: https://tedrepository.tistory.com/6?category=707850 [Ted's IT Repository]
```



**프래그먼트 내의 세부 컴포넌트에 접근하기 위해서는 꼭 프래그먼트 매니저를 통해 참조값을 받아와야 접근 가능**



### 프래그먼트에서 액티비티 자원 접근

프래그먼트가 종속된 액티비티를 알기 위해 **getActivity()**메소드 사용

```java
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
super.onActivityCreated(savedInstanceState);

TextView textView = (TextView) getActivity().findViewById(R.id.textView1);
textView.setText("applied!!");

}

출처: https://tedrepository.tistory.com/6?category=707850 [Ted's IT Repository]
```



# 출처

https://tedrepository.tistory.com/6?category=707850



# 참고하면 도움되는 자료

https://tedrepository.tistory.com/5  프래그먼트 설명

https://programmingnote.tistory.com/26 프래그먼트 사용방법