import org.example.tesco.threading.User;

public static class ThreadLocalEval1 {
    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();
}

static class Service1 {
    void process() {
        User user = new User(1);
        ThreadLocalEval1.threadLocal.set(user);
    }
}

static class Service2 {
    void process() {
        User user = ThreadLocalEval1.threadLocal.get();
        System.out.println(user);
    }
}

public static void main(String[] args) {

}