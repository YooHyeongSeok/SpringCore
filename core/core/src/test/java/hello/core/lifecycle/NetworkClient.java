package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 url = " + url);

        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {

        System.out.println("connect : " + url);

    }

    public void call(String message) {
        System.out.println("call :: " + url + " message :::" + message);
    }

    public void disConnect() {
        System.out.println("close : " + url);
    }

    public void init() throws Exception {
        connect();
        call("초기화 연결메시지");
    }

    public void close() throws Exception {
        disConnect();
    }

}
