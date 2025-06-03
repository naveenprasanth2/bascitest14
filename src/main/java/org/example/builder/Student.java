package org.example.builder;

public class Student {
    private final int id;
    private final String name;

    private Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return STR."Student{id=\{id}, name='\{name}\{'\''}\{'}'}";
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

        public Student build() {
            return new Student(this);
        }
    }
}
