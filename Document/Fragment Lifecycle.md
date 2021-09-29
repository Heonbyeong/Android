# Fragment Lifecycle

> 많은 앱이 **single activity application**을 지향한다. 따라서 대부분 Fragment로 UI를 구성한다.



**생명주기 상태는 다음과 같다.**

- INITIALIZED
- CREATED
- STARTED
- RESUMED
- DESTROYED

## Fragment Lifecycle

> Android Developer Lifecycle 문서

**생명주기의 진행 방향 : 위 → 아래**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/60f82775-5636-4bde-bebf-3da55a41a0f9/Untitled.png)

## onCreate()

- Fragment만 생성된 상태
- onCreate() 이전에 onAttach()가 먼저 호출
- Fragment View가 생성되지 않았기 때문에 Fragment의 View 작업이 부적절함
- saveInstanceState 파라미터는 프래그먼트가 처음 생성 됐을 때만 null로 넘어옴

## onCreateView(), onViewCreated()

- onCreateView()를 재정의 하여 Fragment View를 직접 생성 및 inflate 가능
- onCreateView()를 통해 반환된 View 객체는 onViewCreated()의 파라미터로 전달됨
- 이 시점부터는 생명주기가 INITIALIZED 상태로 업데이트 됨 → View 초기화, LiveData Observing, Adapter 설정은 onViewCreated()에서 해야함

## onViewStateRestored()

- 저장한 모든 state 값이 Fragment의 View 계층구조에 복원 됐을 때 호출
- 뷰의 상태값을 체크할 수 있음
- Fragment View 생명주기 INITIALIZED → CREATED 상태 변경

## onStart()

- Fragment가 사용자에게 보여질 수 있을 때 호출
- child FragmentManager를 통해 Transaction을 안전하게 수행 가능
- CREATED → STARTED 상태 변경

## onResume()

**Resumed 상태가 됐다는 것은 사용자가 Fragment와 상호작용 하기 적절한 상태가 됐다는 것**

→ onResume()가 호출되기 전까지 입력 시도, 포커스 설정 등의 작업을 임의로 하면 안됨4

- Fragment가 보이는 상태에서 모든 Animator와 Transition 효과가 종료되고, 프래그먼트 & 사용자가 서로 상호작용이 가능할 때 호출됨
- STARTED → RESUME 상태 변경

## onPause()

- 사용자가 Fragment를 떠나기 시작했지만 Fragment는 여전히 Visible일 때 호출
- Fragment, View의 생명주기 RESUME → STARTED 상태 변경

## onStop()

- Fragmenrt가 화면에 보여지지 않음 → Fragment, View의 생명주기 STARTED → CREATED
- 부모 액티비티나 프래그먼트가 중단, 상태가 저장될 때 호출

> API 28부터 onSaveInstanceState() 함수와 onStop() 함수 호출 순서가 달라짐

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6a6a0035-9ca4-4cf1-aedd-3a9c1831b6a8/Untitled.png)

**onStop()이 FragmentTransaction을 안전하게 수행할 수 있는 마지막 지점이 됨**

## onDestroyView()

- 모든 Exit anim, Transition이 완료되고 Fragment가 화면으로부터 벗어남 → Fragment View의 생명주기는 CREATED → DESTROYED
- getViewLifecycleOwnerLiveData()의 리턴값 = 0
- 가비지 컬렉터에 수거될 수 있도록 Fragment View에 대한 모든 참조가 제거되어야 함

## onDestroy()

- Fragment 제거 혹은 FragmentManager가 destroy 됐을 때 → Fragment 생명주기 CREATED → DESTROYED
- 해당 지점은 Fragment 생명주기의 끝을 알림
- onDetach()는 onDestroy() 이후에 호출됨



## 참고

https://readystory.tistory.com/199