package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 1개만 생성.
    private static final SingletonService instance = new SingletonService();

    //메서드를 통해서만 허용.
    public static SingletonService getInstance() {
        return instance;
    }

    //new로 생성하지 못하도록 방어.
    private SingletonService() {

    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
