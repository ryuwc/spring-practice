package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingleTonTest {

    @Test
    void singletonBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleTonBean.class);

        SingleTonBean singleTonBean1 = ac.getBean(SingleTonBean.class);
        SingleTonBean singleTonBean2 = ac.getBean(SingleTonBean.class);
        System.out.println("singleTonBean1 = " + singleTonBean1);
        System.out.println("singleTonBean2 = " + singleTonBean2);

        ac.close();

    }

    @Scope("singleton")
    static class SingleTonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingleTonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingleTonBean.destroy");
        }
    }

}
