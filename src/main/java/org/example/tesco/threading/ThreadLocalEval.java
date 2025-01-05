import org.example.tesco.threading.User;

public static class ThreadLocalEval {
    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();
}

static class Service1 {
    void process() {
        User user = new User(1);
        ThreadLocalEval.threadLocal.set(user);
    }
}

static class Service2 {
    void process() {
        User user = ThreadLocalEval.threadLocal.get();
        System.out.println(user);
    }
}

public static void main(String[] args) {

}