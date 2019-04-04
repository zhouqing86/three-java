package singleton;

public class Singleton2 {
    private static class ResourceHolder {
        public static Resource resource = new Resource();

        static {
            System.out.println("Resource Holder!!!");
        }
    }

    public static Resource getResource() {
        return ResourceHolder.resource;
    }

    private static class Resource {
        protected Resource() {
            System.out.println("Start init resource======");
        }
    }

    public static void main(String[] args) {
        Resource resource = getResource();
    }
}

