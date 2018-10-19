package tech.thdev.coroutines;

import org.junit.Test;

public class JavaTest {

    class TestData {
        public String name;

        public TestData(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() {
        TestData data = new TestData("name");
        data = Standard.apply(data, new Standard.Block<TestData>() {

            @Override
            public void block(TestData item) {
                item.name = "change name";
            }
        });
        System.out.println("data name " + data.name);
    }
}
