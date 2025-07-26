package org.example.builder;

public class Employee {
    private final int id;
    private final String name;


    public Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void print() {
        System.out.println(STR."The id of the employee is \{this.id}");
        System.out.println(STR."The name of the employee is \{this.name}");
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
