package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

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

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결메시지");
    }

    @Override
    public void destroy() throws Exception {
        disConnect();
    }

}
