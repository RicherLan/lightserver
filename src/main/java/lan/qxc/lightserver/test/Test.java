package lan.qxc.lightserver.test;

import lan.qxc.lightserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.xml.soap.Text;

public class Test {

    static Test instance = new Test();
    int count = 0;
    void setCount(int n){
        count = n;
    }


    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.count);

        instance.setCount(1);
        System.out.println(test.count);
    }


}
