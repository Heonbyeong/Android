# Android MVVM 

**MVVM (Model, View, ViewModel) 이란 디자인 패턴의 한 종류로 MVP에서 파생된 패턴이다.** 

> MVP 패턴의 View - Presenter 간의 높은 결합도를 줄이기 위해 나온 패턴
>
> MVP 패턴? **Model, View, Presenter 으로 UI(View)와 비지니스 로직(Model)을 분리하고, 서로 간의 상호작용을 다른 객체(Presenter)에 역할을 줌으로써 서로의 의존성을 최소화 하는 것 **



## View - 뷰

**UI를 담당하는 Activity, Fragment 등을 말한다. 화면에 무엇을 그릴지 결정하고 사용자와 상호작용을 하는 부분이다. **

* View는 기본적으로 비즈니스 로직을 포함하지 않지만 UI변경과 관련된 일부 로직은 포함될 수 있다.
* View는 ViewModel을 관찰하고 있다가 상태 변화가 전달되면 화면을 갱신해야 한다.



## ViewModel - 뷰모델

**모든 View와 관련된 비즈니스 로직은 ViewModel에 들어가며, 데이터를 가공해서 View에서 뿌리기 쉬운 Model로 바꾸는 역할을 한다.**

* View와 ViewModel은 1:n 관계를 가진다. 
* 여러 개의 Fragment가 하나의 ViewModel을 가질 수 있다.
* ViewModel은 View가 데이터 바인딩(Data Binding)할 수 있는 속성, 멍령으로 구성되어 있다.



## Model - 모델

**DataModel이라고도 불리며, DB, Network, SharedPreference등 다양한 데이터 소스로부터 필요한 데이터를 준비한다.**

* ViewModel에서 데이터를 가져갈 수 있게 데이터를 준비하고 그에 대한 이벤트를 보낸다.





## MVVM의 장점

**Model - View, ViewModel - View 사이의 의존성이 없으므로 UI 컨트롤러의 과도한 책임을 분담하여 클래스가 무거워지는 것을 방지하고 유지보수, 재사용성, 테스트를 용이하게 만들어 준다.**

## MVVM의 단점

**ViewModel이 설계하기가 어렵고, 시간이 지남에 따라 관계없는 Presentation Logic이 늘어날 수 있다.**





## ACC ViewModel 과 ViewModel

## ACC ViewModel 

> The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations. 
>
> 화면 회전같은 환경 변화에서 뷰의 사용되는 데이터를 유지시키기 위한, 라이프사이클을 알고 있는 클래스이다. 
>
> (안드로이드 공식 문서 내용 인용)

기존 View에는 로그인 된 사용자의 정보를 가지고 있다. 사용자가 화면을 회전 시킬 경우 액티비티는 종료되었다가 다시 생성된다. 그렇기에 다시 데이터를 받아와야 한다. 그러나 **AAC ViewModel은 화면 회전이 있어도 로그인 정보를 그대로 보존하기 때문에 다시 정보를 불러오지 않아도 된다. ACC ViewModel은 딱 이 역할만 하는 클래스다.** ViewModel을 생성하고 이곳에 로그인 정보를 넣었을 뿐이다.(**MVVM 패턴이 되는 것이 아니다.**)

**ACC ViewModel은 컴포넌트들의 생명 주기에 따라 상태 정보나 데이터를 관리하기 쉽도록 사용하는 클래스다.**

결과적으로, **ACC ViewModel과 MVVM ViewModel은 다르다**는 것이다.



## DataBinding

DataBinding이란, 간단하게 **xml파일에 Data를 연결하여 사용할 수 있게 도와주는** Android JetPack 라이브러리중 하나의 기능이다. 애플리케이션 로직과 레이아웃을 연결하는데 필요한 *글루 코드를 최소화 시키는 것이 목적이다.

> **글루 코드란**
>
> 프로그램의 요구사항 구현에는 기여하지 않지만, 호환성이 없는 부분끼리 결합하기 위해 작동하는 코드

**안드로이드에서 MVVM 패턴을 구현할 때 LiveData와 함께 거의 필수적으로 사용한다. **



### 기본 사용법

```kotlin
//graddle에서 아래와 같이 세팅한다.
android {
    ...
    dataBinding{
        enabled true
    }
}

//kotlin-kapt plugin이 설치되어 있지 않다면 추가적으로 설치가 필요하다.
```

DataBinding을 설정할 액티비티의 xml로 이동하여 **최상단 루트를 layout 태그**로 감싼다.

이벤트를 만들 때 참조할 DataBinding 변수명이 필요하므로 **data, variable** 태그를 추가, **name에는 변수명**을, **type에는 DataBinding을 통한 이벤트를 세팅할 패키지명 + 액티비티 명(프래그먼트 명)** 을 적어준다.

```xml
<!-- 변경 전 -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout     xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
    ...
    
</androidx.constraintlayout.widget.ConstraintLayout>


<!-- 변경 후 -->
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
    	<variable
            name="main"
            type="com.패키지명.MainActivity(액티비티명)"/>
    </data>
    
    <androidx.constraintlayout.widget.ConstraintLayou     
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
    	<TextView
            android:id="@+id/hello_text_view"
            android:text="Hello!"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"/>
      
        <Button
            android:id="@+id/btn_change"
            android:text="Text Change!"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/hello_text_view"
            app:layout_constraintBottom_toBottomOf="parent"/>
    
    </androidx.constraintlayout.widget.ConstraintLayou>
                                                       

</layout>
        

```



마지막으로 세팅한 xml의 액티비티 소스로 이동하여 작성한다.

```kotlin
class MainActivity : AppCompatActivity() {
    //xml 파일명이 카멜케이스로 클래스가 자동생성 된다.
    private lateinit var binding: ActivityMainBinding
    
    var text = "Hello!"
    
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        //binding 세팅
        binding = DataBindingUtill.setContentView(this, R.layout.activity_main)
        
        //현재 binding시킨 xml의 variable name
        binding.main = this
        
        //binding 버튼 클릭 이벤트
        binding.btnChange.setOnClickListener {
            text = "Hello Binding!"
            
            //Data가 변동될 경우 binding된 View들에 Data 변화를 알려준다.
           binding.invalidateAll()
        }
    }
}
```



## LiveData

LiveData는 **Data의 변경을 관찰** 할 수 있는 Data Holder 클래스이며, Android JetPack 라이브러리의 하나의 기능이다. 

**Observavle** 형태로 사용한다. 그러나, **일반적인 Observable과는 다르게 LiveData는 생명주기를 알고있다.**

> LiveData가 생명주기를 알고있다는 것은, 활성상태(STARTED 또는 RESUMED)일때만 데이터를 업데이트한다.

> Observavle이란?
>
> '관찰 할 수 있는' 이라는 의미를 가진 Observable은 **어떤 데이터를 Observer가 처리할 수 있도록 포장하는 작업**을 담당한다.
>
> Observer란?
>
> '관찰자' 의미에서 보면 **Observer는 Observable에서 관찰 할 수 있는 형태로 전달한 데이터를 받고 이에 대한 행동으로 취하는** 클래스다. (전달 받은 데이터를 가지고 UI 업데이트, 어떤 인자를 서버에 요청하는 등의 행동을 함)

또한 LiveData 객체는 Observer 객체와 함께 사용된다. **LiveData가 가지고 있는 데이터에 변화가 일어날 경우 Observer 객체에 변화를 알리고, Observer의 onChanged() 메소드가 실행**된다.



### LiveData는 어떻게 생명주기를 알까

**LifeCycleOwner라는 클래스가 생명주기를 알고 있다.** getLifeCycle()밖에 없는 **단일 메소드 인터페이스 클래스** 이고, Activity, Fragment에서 상속하고 있다.



### LiveData의 장점

* Data와 UI간의 동기화

  ​     Observer 객체를 사용하면 데이터의 상태와 UI를 일치시킬 수 있다.

* 메모리 누수가 없음

  ​     생명주기와 객체와 결합되어 있기 때문에 컴포넌트가 Destroy 될 경우 메모리상에서 스스로 해제된다.

* Stop 상태의 액티비티와 Crash가 발생하지 않음

  ​     Observer의 생명주기가 inactive일 경우, LiveData의 어떤 이벤도 수신하지 않는다.

* 생명주기에 대한 추가적인 handling을 하지 않아도 됨

  ​     UI 컴포넌트는 그저 관련 있는 데이터를 관찰하기만 하면 된다.

* 항상 최신 데이터를 유지

  ​     회면 구성이 변경되어도 데이터를 유지한다.

* 자원 공유 가능

  ​     LiveData를 상속하여 자신만의 LiveData 클래스를 구현할 수 있고, 싱글톤 패턴을 이용하여 앱 어디에서나 자원을           

  ​     공유할 수 있다.



## LiveData 사용법

```kotlin
class TestLiveDataViewModel : ViewModel() {
    //String 타입의 MutableLiveData 생성, by lazy로 초기화는 나중에
    private var liveText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
```

> ### MutableLiveData와 LiveData의 구분
>
> MutableLiveData : 값의 get/set 모두 가능
>
> LiveData : 값의 get()만 가능

**LiveData에 Observer를 결합하는 코드는 컴포넌트의 onCreate() 메소드 내에 위치하는 것이 바람직하다.**

```kotlin
class MainActivity : AppCompatActivity() {
    //전역 변수로 ViewModel lateinit 세팅
    private lateinit var model : TestLiveDataViewModel
    
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        
        //ViewModel을 가져온다.
        model = ViewModelProvider(this).get(TestLiveDataViewModel::class.java)
        
        //Observer를 생성한 뒤 UI에 업데이트 시켜준다.
        val testObserver = Observer<String> { liveText ->
       		(TextView id).text = liveText          
        }
        
        //LiveData를 Observer를 이용해 관찰하고, 현재 액티비티 및 Observer를 LifecycleOwner로 전달한다.
        model.liveText.observe(this, textObserver)
    }
}
```





## 참고한 자료

### MVVM

https://medium.com/@jsuch2362/android-%EC%97%90%EC%84%9C-mvvm-%EC%9C%BC%EB%A1%9C-%EA%B8%B4-%EC%97%AC%EC%A0%95%EC%9D%84-82494151f312

https://velog.io/@jojo_devstory/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%ED%8C%A8%ED%84%B4-MVVM%EC%9D%B4-%EB%AD%98%EA%B9%8C

### ACC ViewModel

https://wooooooak.github.io/android/2019/05/07/aac_viewmodel/

https://enant.tistory.com/49

### DataBinding

https://velog.io/@jojo_devstory/Android-Databinding%EC%9D%84-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90

### LiveData

https://thdev.tech/android/2021/02/01/LiveData-Intro/

https://velog.io/@jojo_devstory/Android-LiveData...%EB%84%8C-%EB%88%84%EA%B5%AC%EB%83%90

### Observable, Observer

https://selfish-developer.com/entry/RxJava-Observable-Observer