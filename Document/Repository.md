# Repository 패턴

> 데이터 출처(로컬 DB인지 API응답인지 등)와 관계 없이 동일 인터페이스로 데이터에 접속할 수 있도록 만드는 것을 Repository 패턴이라고 한다. 레포지토리는 데이터 소스에 액세스하는 데 필요한 논리를 캡슐화하는 클래스 또는 구성 요소이다.
>
> 출처: https://devvkkid.tistory.com/196 

**Repository패턴은 데이터를 캡슐화 한다.**



![Android Architecture Components. Android Architecture Components (AAC)… |  by Ihor Kucherenko | ProAndroidDev](https://miro.medium.com/max/1200/1*kEjRZjzQ4lxgMITbU8iidg.png)

View, ViewModel이나 Domain 레이어에서 Data 레이어(Repository, Model)에 접근할 때 **데이터 소스의 위치 (서버, Local DB)를 몰라도 일관성 있게 원하는 데이터를 취할 수 있도록 돕는 것**이다.

Repository라는 인터페이스만 ViewModel이 접근하게 되는데, 이때 ViewModel은 요청한 데이터가 Room에서 온건지 API 작업으로 온건지 알 필요가 없다. 이런 작업은 모두 Repository에서 관장하고, **결국 ViewModel은 UI를 업데이트하기 위한 데이터를 제공하는 일에만 집중할 수 있게 된다.**

