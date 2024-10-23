public class ClassTest {
    @Test(priority = 7)
    public void testMethod1() {
        System.out.println("test method 1 ");
    }

    @BeforeSuite
    public void testMethod2() {
        System.out.println("test method 2 ");
    }

    //@Test
    public void testMethod3() {
        System.out.println("test method 3 throws Exception");
        throw new RuntimeException("method 3 exception");
    }

    @Test(priority = 6)
    public void testMethod4() {
        System.out.println("test method 4 ");
    }

    public void testMethod5() {
        System.out.println("test method 5 ");
    }
}
